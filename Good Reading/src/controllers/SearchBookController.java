package controllers;

import java.io.IOException;
import java.util.ArrayList;

import boundary.ClientUI;
import client.Client;
import common.Message;
import controllers.UserHomepageController.BookGrid;
import i_book.Book;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SearchBookController extends SystemController{
	
	class BookGrid {
		public Book book;
		public int x;
		public int y;

		public BookGrid(Book b,  int x, int y) {
			book = b;
			this.x = x;
			this.y = y;
		}
	}
	
	public static String[] query = new String[5];
	public static Book[] books;
	public static BookGrid[] bookPos;
	private GridPane grid;
	
	@FXML
	private TextField titleField;
	@FXML
	private TextField langField;
	@FXML
	private TextField authorField;
	@FXML 
	private ChoiceBox<String> optionBox;
	@FXML
	private AnchorPane scrollAnchor;
	
	public void initialize(){
		super.initialize();
		optionBox.setItems(FXCollections.observableArrayList("AND", "OR"));
		optionBox.getSelectionModel().selectFirst();
	}
	
	public void searchOnEnterPressed(){
		query[0]=optionBox.getSelectionModel().getSelectedItem();
		query[1]=""+UserHomepageController.books.length;
		query[2]=titleField.getText();
		query[3]=langField.getText();
		query[4]=authorField.getText();
		Message msg = new Message("search book",1,query);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handleMessage(Message msg) {
		switch(msg.getFunc()){
		case 1:
			ArrayList<Book> b = (ArrayList<Book>) msg.getMsg();
			System.out.println("hi");
			books = new Book[b.size()];
			for(int i=0; i<b.size(); i++){
				books[i]=b.get(i);
			}
			System.out.println("dies");
			for(int i=0; i<b.size(); i++) System.out.println(b.get(i).getTitle());
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setBookGrid();
				}
			});
		}
	}
	
	public void setBookGrid() {
		grid = new GridPane();
		bookPos = new BookGrid[books.length];
		int width = 5;
		int length = books.length / 5 + 1;
		if (books.length < 5) {
			length = 1;
			width = books.length;
		}
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				bookPos[y * 5 + x] = new BookGrid(books[y * 5 + x], x, y);
				StackPane ap = new StackPane();
				ap.setUserData(bookPos[y * 5 + x]);
				ap.setCursor(Cursor.HAND);
				ap.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						BookGrid pos = (BookGrid) ap.getUserData();
						System.out.println(pos.book.getTitle());
						BookPageController.book = pos.book;
						ClientUI.setScene("BookPageGUI.fxml");
						event.consume();
					}
				});
				ap.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						ColorAdjust ca = new ColorAdjust();
						ca.setBrightness(0.8);
						ImageView iv = (ImageView) ap.getChildren().get(0);
						iv.setEffect(ca);
						BookGrid pos = (BookGrid) ap.getUserData();
						Label title = new Label(pos.book.getTitle());
						title.setTextFill(Color.BLACK);
						title.setFont(Font.font("System", FontWeight.BOLD, 14));
						ap.getChildren().add(title);
						StackPane.setAlignment(title, Pos.CENTER);
						String s = new String();
						float f = pos.book.getPrice();
						if (f == (long) f)
							s = String.format("%d", (long) f);
						else
							s = String.format("%s", f);
						Label price = new Label(s + "$");
						price.setTextFill(Color.RED);
						price.setFont(Font.font("System", 20));
						ap.getChildren().add(price);
						StackPane.setAlignment(price, Pos.CENTER);
						price.setTranslateY(25);
						event.consume();
					}
				});
				ap.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						ColorAdjust ca = new ColorAdjust();
						ca.setBrightness(0);
						ImageView iv = (ImageView) ap.getChildren().get(0);
						iv.setEffect(ca);
						ap.getChildren().remove(ap.lookup(".label"));
						ap.getChildren().remove(ap.lookup(".label"));
						event.consume();
					}
				});
				Image img = new Image(books[y * 5 + x].getImage());
				ImageView iv = new ImageView();
				iv.setImage(img);
				iv.setFitWidth(180);
				iv.setFitHeight(270);
				InnerShadow innerShadow = new InnerShadow();
				innerShadow.setColor(Color.BLACK);
				iv.setEffect(innerShadow);
				ap.getChildren().add(iv);
				grid.add(ap, x, y);
			}
			if (books.length % 5 != 0 && y == length - 2) {
				width = books.length % 5;
			}
		}

		scrollAnchor.setPrefHeight(280 * length);
		scrollAnchor.getChildren().add(grid);
	}

}
