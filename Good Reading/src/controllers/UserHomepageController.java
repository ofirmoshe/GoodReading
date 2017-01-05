package controllers;

import java.io.IOException;
import java.util.Random;

import org.orm.PersistentException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;
import i_book.Book;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserHomepageController extends SystemController {

	private GridPane initGrid;
	private GridPane grid;
	public static Book[] books = null;
	public static BookGrid[] bookPos;
	public static int gridx;
	public static int gridy;

	@FXML
	private AnchorPane searchBookButton;
	@FXML
	private AnchorPane searchReviewButton;
	@FXML
	private AnchorPane membershipButton;
	@FXML
	private Label userLabel;
	@FXML
	private AnchorPane searchBookClick;
	@FXML
	private AnchorPane searchReviewClick;
	@FXML
	private AnchorPane membershipClick;
	@FXML
	private AnchorPane scrollAnchor;
	@FXML
	private ScrollPane scrollPane;

	public void initialize() {
		super.initialize();
		if (books == null) {
			Message msg = new Message("user homepage", 1);
			AbstractController.instance = this;
			try {
				Client.instance.sendToServer(msg);
			} catch (IOException e1) {
				System.out.println("Can't send to server.");
			}

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					while (books == null) {
						System.out.print("");
					}
					setBookGrid();
				}
			});
		} else
			setBookGrid();

	}

	

	@Override
	public void handleMessage(Message msg) {
		if (msg.getFunc() == 1) {
			books = (Book[]) msg.getMsg();
		}
	}

	public void initBooksGrid(int length, int width) {
		initGrid = new GridPane();
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				Image img = new Image(GraphicsImporter.class.getResource("loading_book.jpg").toString());
				ImageView iv = new ImageView();
				iv.setImage(img);
				iv.setFitWidth(180);
				iv.setFitHeight(270);
				initGrid.add(iv, x, y);
			}
		}
		scrollAnchor.setPrefHeight(280 * length);
		scrollAnchor.getChildren().add(initGrid);

	}

	public void setBookGrid() {
		grid = new GridPane();
		bookPos = new BookGrid[books.length];
		int width = 5;
		int length = books.length / 5 + 1;
		if (length == 0) {
			length = 1;
			width = books.length;
		}
		for (gridy = 0; gridy < length; gridy++) {
			for (gridx = 0; gridx < width; gridx++) {
				bookPos[gridy * 5 + gridx]= new BookGrid(books[gridy * 5 + gridx],gridx,gridy);
				Image img = new Image(books[gridy * 5 + gridx].getImage());
				ImageView iv = new ImageView();
				iv.setImage(img);
				iv.setFitWidth(180);
				iv.setFitHeight(270);
				iv.setUserData(bookPos[gridy * 5 + gridx]);
				iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						BookGrid pos = (BookGrid)iv.getUserData();
						System.out.println(pos.book.getTitle());
						BookPageController.book = pos.book;
						ClientUI.setScene("BookPageGUI.fxml");
						event.consume();
					}
				});
				iv.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						ColorAdjust ca = new ColorAdjust();
						ca.setBrightness(0.7);
						iv.setEffect(ca);
						BookGrid pos = (BookGrid)iv.getUserData();
						Label l = new Label(pos.book.getTitle());
						l.setTextFill(Color.BLACK);
						grid.add(l, pos.x, pos.y);
						l.setTranslateX(30);
						l.setFont(Font.font ("System",FontWeight.BOLD, 14));
						event.consume();
					}
				});
				iv.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						ColorAdjust ca = new ColorAdjust();
						ca.setBrightness(0);
						iv.setEffect(ca);
						grid.getChildren().remove(grid.lookup(".label"));
						event.consume();
					}
				});
				iv.setCursor(Cursor.HAND);
				InnerShadow innerShadow = new InnerShadow();
				innerShadow.setColor(Color.BLACK);
				iv.setEffect(innerShadow);
				grid.add(iv, gridx, gridy);
			}
			if (books.length % 5 != 0 && gridy == length - 2) {
				width = books.length % 5;
			}
		}

		scrollAnchor.setPrefHeight(280 * length);
		scrollAnchor.getChildren().addAll(grid);
	}

}

class BookGrid{
	public Book book;
	public int x;
	public int y;
	public BookGrid(Book b, int x, int y){
		book=b;
		this.x=x;
		this.y=y;
	}
}

