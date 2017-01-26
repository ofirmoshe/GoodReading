package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Author;
import i_book.Book;
import i_book.Field;
import i_book.Subject;
import i_book.User;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserReportController extends SystemController {

	private Book[] books;
	private GridPane grid = null;
	private Author[][] book_authors;
	private Field[][] book_fields;
	private Subject[][] book_subjects;

	public static String userID;

	@FXML
	private AnchorPane scrollAnchor;

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
		Message msg = new Message("user report", 1, userID);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void histogramOnClick()
	{
		ClientUI.setScene("HistogramReportGUI.fxml");
	}
	@Override
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			Object[] o = (Object[]) msg.getMsg();
			books = (Book[]) o[0];
			book_authors = (Author[][]) o[1];
			book_fields = (Field[][]) o[2];
			book_subjects = (Subject[][]) o[3];
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					if (grid != null)
						scrollAnchor.getChildren().remove(grid);
					if (books.length == 0) {
						StackPane sp = new StackPane();
						sp.setPrefHeight(200);
						sp.setPrefWidth(900);
						Label noRes = new Label("Sorry, no results.");
						noRes.setTextFill(Color.DARKSLATEGRAY);
						noRes.setFont(Font.font("System", 20));
						sp.getChildren().add(noRes);
						StackPane.setAlignment(noRes, Pos.CENTER);
						scrollAnchor.getChildren().add(sp);
						scrollAnchor.setPrefHeight(200);
						return;
					}
					setBookGrid();
				}
			});
			break;
		}
	}

	/**
	 * This method sets the book grid with books, and event handlers for each
	 * book. The book grid is added to the scroll anchor.
	 */
	public void setBookGrid() {
		try {
			grid = new GridPane();
			for (int y = 0; y < books.length; y++) {
				AnchorPane ap = new AnchorPane();
				ap.setUserData(y);
				ap.setPrefHeight(170);
				ap.setPrefWidth(900);
				Rectangle rect = new Rectangle();
				rect.setHeight(170);
				rect.setWidth(900);
				rect.setFill(Color.WHITE);
				rect.setStrokeWidth(1);
				rect.setStroke(Color.BURLYWOOD);
				ap.getChildren().add(rect);
				ByteArrayInputStream in = new ByteArrayInputStream(books[y].getImage());
				BufferedImage read;
				read = ImageIO.read(in);
				Image img = SwingFXUtils.toFXImage(read, null);
				ImageView iv = new ImageView();
				iv.setImage(img);
				iv.setFitWidth(100);
				iv.setFitHeight(150);
				InnerShadow innerShadow = new InnerShadow();
				innerShadow.setColor(Color.BLACK);
				iv.setEffect(innerShadow);
				ap.getChildren().add(iv);
				iv.setLayoutX(10);
				iv.setLayoutY(10);
				Label title = new Label(books[y].getTitle());
				title.setTextFill(Color.BLACK);
				title.setFont(Font.font("System", FontWeight.BOLD, 18));
				ap.getChildren().add(title);
				title.setLayoutX(120);
				title.setLayoutY(10);
				String s = "";
				if (book_authors[y].length != 0) {
					s = "by " + book_authors[y][0].getName();
					for (int i = 1; i < book_authors[y].length; i++)
						s = s + ", " + book_authors[y][i].getName();
					Label book_authors = new Label(s);
					book_authors.setTextFill(Color.BLACK);
					book_authors.setFont(Font.font("System", 13));
					ap.getChildren().add(book_authors);
					book_authors.setLayoutX(120);
					book_authors.setLayoutY(33);
				}else
					System.out.println("hi");
				s = book_fields[y][0].getField();
				for (int i = 1; i < book_fields[y].length; i++)
					s = s + ", " + book_fields[y][i].getField();
				Label book_fields = new Label(s);
				book_fields.setTextFill(Color.GREY);
				book_fields.setFont(Font.font("System", 12));
				ap.getChildren().add(book_fields);
				book_fields.setLayoutX(120);
				book_fields.setLayoutY(55);
				if (book_subjects[y].length != 0) {
					s = book_subjects[y][0].getSub();
					for (int i = 1; i < book_subjects[y].length; i++)
						s = s + ", " + book_subjects[y][i].getSub();
					Label book_subjects = new Label(s);
					book_subjects.setTextFill(Color.GREY);
					book_subjects.setFont(Font.font("System", 12));
					ap.getChildren().add(book_subjects);
					book_subjects.setLayoutX(120);
					book_subjects.setLayoutY(72);
				}
				Label sum = new Label(books[y].getSummary());
				sum.setPrefSize(600, 40);
				sum.setLayoutX(120);
				sum.setLayoutY(90);
				sum.setWrapText(true);
				ap.getChildren().add(sum);
				grid.add(ap, 0, y);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scrollAnchor.setPrefHeight(171 * books.length);
		scrollAnchor.getChildren().add(grid);
	}

}
