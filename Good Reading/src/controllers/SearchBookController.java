package controllers;

import java.io.IOException;
import java.util.ArrayList;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Author;
import i_book.Book;
import i_book.Field;
import i_book.Subject;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Search book controller is the controller of the search book page.
 * 
 * @author guyzi
 *
 */
public class SearchBookController extends SystemController {

	private String[] query = new String[5];
	private Book[] books;
	private GridPane grid = null;
	private Author[][] authors;
	private Field[][] fields;
	private Subject[][] subjects;

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

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
		optionBox.setItems(FXCollections.observableArrayList("AND", "OR"));
		optionBox.getSelectionModel().selectFirst();
	}

	/**
	 * This method is called when the search button is clicked, or when Enter
	 * key is pressed. Sends the input query to the server.
	 */
	public void searchOnEnterPressed() {
		query[0] = optionBox.getSelectionModel().getSelectedItem();
		query[1] = "" + UserHomepageController.books.length; //The amount of books in DB.
		query[2] = titleField.getText();
		query[3] = langField.getText();
		query[4] = authorField.getText();
		Message msg = new Message("search book", 1, query);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method implements the abstract controller method.
	 * 
	 * @param msg
	 *            case 1: The message is an object array. index 0 - Book array.
	 *            		index 1 - Author matrix, each index is an array of authors of
	 *            				  the matching book in the book array. index 2 - Fields matrix.
	 *         		    index 3 - Subject matrix. If the book array is empty, no
	 *         				      result message is displayed, else the book grid is set
	 *                            according to this data.
	 */
	@Override
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			Object[] o = (Object[]) msg.getMsg();
			books = (Book[]) o[0];
			authors = (Author[][]) o[1];
			fields = (Field[][]) o[2];
			subjects = (Subject[][]) o[3];
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
						return;
					}
					setBookGrid();
				}
			});
		}
	}

	/**
	 * This method sets the book grid with books, and event handlers for each
	 * book. The book grid is added to the scroll anchor.
	 */
	public void setBookGrid() {
		grid = new GridPane();
		for (int y = 0; y < books.length; y++) {
			AnchorPane ap = new AnchorPane();
			ap.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					int y = (int) ap.getUserData();
					BookPageController.book = books[y];
					ClientUI.setScene("BookPageGUI.fxml");
					event.consume();
				}
			});
			ap.setCursor(Cursor.HAND);
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
			Image img = new Image(books[y].getImage());
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
			String s = "by " + authors[y][0].getName();
			for (int i = 1; i < authors[y].length; i++)
				s = s + ", " + authors[y][i].getName();
			Label authors = new Label(s);
			authors.setTextFill(Color.BLACK);
			authors.setFont(Font.font("System", 13));
			ap.getChildren().add(authors);
			authors.setLayoutX(120);
			authors.setLayoutY(33);
			s = fields[y][0].getField();
			for (int i = 1; i < fields[y].length; i++)
				s = s + ", " + fields[y][i].getField();
			Label fields = new Label(s);
			fields.setTextFill(Color.GREY);
			fields.setFont(Font.font("System", 12));
			ap.getChildren().add(fields);
			fields.setLayoutX(120);
			fields.setLayoutY(55);
			if (subjects[y].length != 0) {
				s = subjects[y][0].getSub();
				for (int i = 1; i < subjects[y].length; i++)
					s = s + ", " + subjects[y][i].getSub();
				Label subjects = new Label(s);
				subjects.setTextFill(Color.GREY);
				subjects.setFont(Font.font("System", 12));
				ap.getChildren().add(subjects);
				subjects.setLayoutX(120);
				subjects.setLayoutY(72);
			}
			grid.add(ap, 0, y);
		}
		scrollAnchor.setPrefHeight(171 * books.length);
		scrollAnchor.getChildren().add(grid);
	}

}
