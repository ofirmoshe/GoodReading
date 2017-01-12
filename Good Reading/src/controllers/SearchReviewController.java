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
import i_book.Review;
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
public class SearchReviewController extends SystemController {

	private String[] query = new String[4];
	private GridPane grid = null;
	private String[] rev;
	private String[] titles;
	private String[] unames;

	@FXML
	private TextField titleField;
	@FXML
	private TextField authorField;
	@FXML
	private ChoiceBox<String> optionBox;
	@FXML
	private AnchorPane scrollAnchor;
	@FXML
	private TextField keywordField;

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
		scrollAnchor.setPrefHeight(200);
		optionBox.setItems(FXCollections.observableArrayList("AND", "OR"));
		optionBox.getSelectionModel().selectFirst();
	}

	/**
	 * This method is called when the search button is clicked, or when Enter
	 * key is pressed. Sends the input query to the server.
	 */
	public void searchOnEnterPressed() {
		query[0] = optionBox.getSelectionModel().getSelectedItem();
		query[1] = titleField.getText();
		query[2] = authorField.getText();
		query[3] = keywordField.getText();
		Message msg = new Message("search review", 1, query);
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
			if (msg.getMsg() != null) {
				String[][] s = (String[][]) msg.getMsg();
				rev = (String[]) s[0];
				titles = (String[]) s[1];
				unames = (String[]) s[2];
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (grid != null)
							scrollAnchor.getChildren().remove(grid);
						if (rev.length == 0) {
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
						setReviewGrid();
					}
				});
			}
			break;
		}
	}

	/**
	 * This method sets the book grid with books, and event handlers for each
	 * book. The book grid is added to the scroll anchor.
	 */
	public void setReviewGrid() {
		grid = new GridPane();
		for (int y = 0; y < rev.length; y++) {
			AnchorPane ap = new AnchorPane();
			ap.setPrefHeight(80);
			ap.setPrefWidth(900);
			Rectangle rect = new Rectangle();
			rect.setHeight(80);
			rect.setWidth(900);
			rect.setFill(Color.WHITE);
			rect.setStrokeWidth(1);
			rect.setStroke(Color.BURLYWOOD);
			ap.getChildren().add(rect);
			Label username = new Label(unames[y]);
			username.setTextFill(Color.DARKCYAN);
			username.setFont(Font.font("System", FontWeight.BOLD, 14));
			ap.getChildren().add(username);
			username.setLayoutX(10);
			username.setLayoutY(10);
			Label title = new Label("in "+titles[y]);
			title.setFont(Font.font("System", 12));
			title.setLayoutX(10);
			title.setLayoutY(30);
			ap.getChildren().add(title);
			Label r= new Label(rev[y]);
			r.setFont(Font.font("System", 12));
			r.setLayoutX(10);
			r.setLayoutY(50);
			r.setWrapText(true);
			ap.getChildren().add(r);
			grid.add(ap, 0, y);
		}
		scrollAnchor.setPrefHeight(80 * rev.length);
		scrollAnchor.getChildren().add(grid);
	}


}
