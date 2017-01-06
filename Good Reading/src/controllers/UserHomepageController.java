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
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserHomepageController extends SystemController {

	class BookGrid {
		public Book book;
		public int x;
		public int y;

		public BookGrid(Book b, int x, int y) {
			book = b;
			this.x = x;
			this.y = y;
		}
	}

	private GridPane initGrid;
	private GridPane grid;
	public static Book[] books = null;
	public static BookGrid[] bookPos;

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
		System.out.println("init");
		super.initialize();
		initBookGrid();
		Task<Void> sleeper = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					Thread.sleep(9);
				} catch (InterruptedException e) {
				}
				return null;
			}
		};
		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				setBookGrid();
			}
		});
		new Thread(sleeper).start();
	}
	/*
	 * Platform.runLater(new Runnable() {
	 * 
	 * @Override public void run() { setBookGrid(); } });
	 */

	public void initBookGrid() {
		initGrid = new GridPane();
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 5; x++) {
				Image img = new Image(GraphicsImporter.class.getResource("loading_book.jpg").toString());
				ImageView iv = new ImageView(img);
				iv.setFitWidth(180);
				iv.setFitHeight(270);
				initGrid.add(iv, x, y);
			}
		}
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

	@Override
	public void handleMessage(Message msg) {
	}

}