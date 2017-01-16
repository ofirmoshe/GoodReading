package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.orm.PersistentException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;
import i_book.Author;
import i_book.Book;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
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

/**
 * User Homepage Controller is the controller of the user homepage.
 * 
 * @author guyzi
 *
 */
public class UserHomepageController extends SystemController {

	private GridPane initGrid;
	private GridPane grid;
	public static Book[] books = null;

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

	/**
	 * This method initializes the controller and the book grid to the initial
	 * grid (loading).
	 */
	public void initialize() {
		super.initialize();
		// initBookGrid();
		setBookGrid();
		Client.refresh();
		Message msg = new Message("user homepage",1);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is called when the mouse enter the main anchor of the page,
	 * and sets the book grid.
	 */
	public void mainAnchorOnEnter() {
		// setBookGrid();
	}

	/**
	 * This method initializes the book grid and adds it to the scroll anchor.
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

	/**
	 * This method fills the book grid with books from DB, and sets event
	 * handlers for each one.
	 */
	public void setBookGrid() {
		grid = new GridPane();
		int width = 5;
		int length = books.length / 5 + 1;
		if (books.length < 5) {
			length = 1;
			width = books.length;
		}else if(books.length%5==0){
			length= books.length/5;
		}
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				try {
					StackPane ap = new StackPane();
					ap.setUserData(y * 5 + x);
					ap.setCursor(Cursor.HAND);
					ap.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							int i = (int) ap.getUserData();
							BookPageController.book = books[i];
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
							int i = (int) ap.getUserData();
							Label title = new Label(books[i].getTitle());
							title.setTextFill(Color.BLACK);
							title.setFont(Font.font("System", FontWeight.BOLD, 14));
							ap.getChildren().add(title);
							StackPane.setAlignment(title, Pos.CENTER);
							String s = new String();
							float f = books[i].getPrice();
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
					Image img = new Image(GraphicsImporter.class.getResource("loading_book.jpg").toString());
					if (books[y * 5 + x].getImage() != null) {
						ByteArrayInputStream in = new ByteArrayInputStream(books[y * 5 + x].getImage());
						BufferedImage read;
						read = ImageIO.read(in);
						img = SwingFXUtils.toFXImage(read, null);
					}
					ImageView iv = new ImageView(img);
					iv.setFitWidth(180);
					iv.setFitHeight(270);
					InnerShadow innerShadow = new InnerShadow();
					innerShadow.setColor(Color.BLACK);
					iv.setEffect(innerShadow);
					ap.getChildren().add(iv);
					grid.add(ap, x, y);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		switch(msg.getFunc()){
		case 1:
			books = (Book[])msg.getMsg();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setBookGrid();
				}
			});
		}
	}

}
