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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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

	private String[] query = new String[8];
	private Book[] books;
	private GridPane grid = null;
	private Author[][] book_authors;
	private Field[][] book_fields;
	private Subject[][] book_subjects;
	public static Field[] fields;
	public static Subject[][] subjects;

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
	@FXML
	private TextField keywordField;
	@FXML
	private ChoiceBox<String> fieldBox;
	@FXML
	private ChoiceBox<String> subjectBox;

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
		if (fields == null) {
			Message msg = new Message("search book", 2);
			try {
				Client.instance.sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			setFieldBox();
		scrollAnchor.setPrefHeight(200);
		optionBox.setItems(FXCollections.observableArrayList("AND", "OR"));
		optionBox.getSelectionModel().selectFirst();
		fieldBox.setItems(FXCollections.observableArrayList("None"));
		fieldBox.getSelectionModel().selectFirst();
		subjectBox.setItems(FXCollections.observableArrayList("None"));
		subjectBox.getSelectionModel().selectFirst();
	}

	/**
	 * This method is called when the search button is clicked, or when Enter
	 * key is pressed. Sends the input query to the server.
	 */
	public void searchOnEnterPressed() {
		query[0] = optionBox.getSelectionModel().getSelectedItem();
		// The amount of books in DB.
		Book[] b=UserHomepageController.books;
		query[1] = "" + b[b.length-1].getID();
		query[2] = titleField.getText();
		query[3] = langField.getText();
		query[4] = authorField.getText();
		query[5] = keywordField.getText();
		query[6] = "" + fieldBox.getSelectionModel().getSelectedIndex();
		query[7] = subjectBox.getSelectionModel().getSelectedItem();
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
	 *            index 1 - Author matrix, each index is an array of
	 *            book_authors of the matching book in the book array. index 2 -
	 *            book_fields matrix. index 3 - Subject matrix. If the book
	 *            array is empty, no result message is displayed, else the book
	 *            grid is set according to this data.
	 */
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

		case 2:
			Object[] ob = (Object[]) msg.getMsg();
			fields = (Field[]) ob[0];
			subjects = (Subject[][]) ob[1];
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setFieldBox();
				}
			});
		}
	}

	public void setFieldBox() {
		ObservableList<String> olf = FXCollections.observableArrayList();
		olf.add("None");
		for (int i = 0; i < fields.length; i++) {
			olf.add(fields[i].getField());
		}
		fieldBox.setItems(olf);
		fieldBox.getSelectionModel().selectFirst();
		fieldBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				ObservableList<String> ols = FXCollections.observableArrayList();
				ols.add("None");
				if (new_value.intValue() > 0) {
					for (int i = 0; i < subjects[new_value.intValue() - 1].length; i++) {
						ols.add(subjects[new_value.intValue() - 1][i].getSub());
					}
					subjectBox.setItems(ols);
					subjectBox.getSelectionModel().selectFirst();
				}
			}
		});
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
				String s = "by " + book_authors[y][0].getName();
				for (int i = 1; i < book_authors[y].length; i++)
					s = s + ", " + book_authors[y][i].getName();
				Label book_authors = new Label(s);
				book_authors.setTextFill(Color.BLACK);
				book_authors.setFont(Font.font("System", 13));
				ap.getChildren().add(book_authors);
				book_authors.setLayoutX(120);
				book_authors.setLayoutY(33);
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
				;
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
