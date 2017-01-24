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
import i_book.Employee;
import i_book.Field;
import i_book.Review;
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
public class SearchUserController extends SystemController {

	private String[] query = new String[3];
	private GridPane grid = null;
	private User[] users;
	private ArrayList<User> usersByQuery = new ArrayList<User>();

	@FXML
	private TextField idField;
	@FXML
	private TextField firstNameField;
	@FXML
	private AnchorPane scrollAnchor;
	@FXML
	private TextField lastNameField;

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
		Message msg = new Message("search user", 1);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		scrollAnchor.setPrefHeight(200);
	}

	/**
	 * This method is called when the search button is clicked, or when Enter
	 * key is pressed. Sends the input query to the server.
	 */
	public void searchOnEnterPressed() {
		usersByQuery.clear();
		scrollAnchor.getChildren().remove(grid);
		int queryCounter = 0;
		query[0] = idField.getText();
		query[1] = firstNameField.getText();
		query[2] = lastNameField.getText();
		if (!query[0].equals(""))
			queryCounter++;
		if (!query[1].equals(""))
			queryCounter++;
		if (!query[2].equals(""))
			queryCounter++;
		if (users.length != 0) {

			int[] counter = new int[users.length];
			for (int i = 0; i < users.length; i++) {
				if (!query[0].equals("")) {
					// Search By ID
					if (users[i].getID().contains(query[0]))
						counter[i]++;
				}
				if (!query[1].equals("")) {
					// Search By First Name
					if (users[i].getFname().toLowerCase().contains(query[1].toLowerCase()))
						counter[i]++;
				}
				if (!query[2].equals("")) {
					// Search By First Name
					if (users[i].getLname().toLowerCase().contains(query[2].toLowerCase()))
						counter[i]++;
				}
			}
			for (int i = 0; i < counter.length; i++)
				if (counter[i] == queryCounter)
					usersByQuery.add(users[i]);

			setUsersGrid();
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
				users = (User[]) msg.getMsg();
				for (int i = 0; i < users.length; i++)
					usersByQuery.add(users[i]);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (grid != null)
							scrollAnchor.getChildren().remove(grid);
						if (users.length == 0) {
							StackPane sp = new StackPane();
							sp.setPrefHeight(200);
							sp.setPrefWidth(900);
							Label noRes = new Label("No users avaliable.");
							noRes.setTextFill(Color.DARKSLATEGRAY);
							noRes.setFont(Font.font("System", 20));
							sp.getChildren().add(noRes);
							StackPane.setAlignment(noRes, Pos.CENTER);
							scrollAnchor.getChildren().add(sp);
							scrollAnchor.setPrefHeight(200);
							return;
						}
						setUsersGrid();
					}
				});
			}
			break;
		case 2:
			if (msg.getMsg() != null) {
				users = (User[]) msg.getMsg();
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						if (grid != null)
							scrollAnchor.getChildren().remove(grid);
						if (users.length == 0) {
							StackPane sp = new StackPane();
							sp.setPrefHeight(200);
							sp.setPrefWidth(900);
							Label noRes = new Label("No users avaliable.");
							noRes.setTextFill(Color.DARKSLATEGRAY);
							noRes.setFont(Font.font("System", 20));
							sp.getChildren().add(noRes);
							StackPane.setAlignment(noRes, Pos.CENTER);
							scrollAnchor.getChildren().add(sp);
							scrollAnchor.setPrefHeight(200);
							return;
						}
						setUsersGrid();
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
	public void setUsersGrid() {
		grid = new GridPane();
		for (int y = 0; y < usersByQuery.size(); y++) {
			AnchorPane ap = new AnchorPane();
			ap.setPrefHeight(80);
			ap.setPrefWidth(900);
			ap.setUserData(y);
			ap.setCursor(Cursor.HAND);
			ap.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					int y = (int) ap.getUserData();
					if (ClientUI.user instanceof Employee){
						if (!((Employee) ClientUI.user).getPosition().equals("Manager")) {
							ManageUserController.user = usersByQuery.get(y);
							ClientUI.setScene("ManageUserGUI.fxml");
						} else {
							UserReportController.userID = usersByQuery.get(y).getID();
							ClientUI.setScene("UserReportGUI.fxml");
						}
					}
					event.consume();
				}
			});
			Rectangle rect = new Rectangle();
			rect.setHeight(80);
			rect.setWidth(900);
			rect.setFill(Color.WHITE);
			rect.setStrokeWidth(1);
			rect.setStroke(Color.BURLYWOOD);
			ap.getChildren().add(rect);
			Label username = new Label(usersByQuery.get(y).getFname() + " " + usersByQuery.get(y).getLname());
			username.setTextFill(Color.DARKCYAN);
			username.setFont(Font.font("System", FontWeight.BOLD, 14));
			ap.getChildren().add(username);
			username.setLayoutX(10);
			username.setLayoutY(10);
			Label id = new Label("ID: " + usersByQuery.get(y).getID());
			id.setFont(Font.font("System", 12));
			id.setLayoutX(10);
			id.setLayoutY(30);
			ap.getChildren().add(id);
			Label s = new Label("Status: " + usersByQuery.get(y).getStatus());
			s.setFont(Font.font("System", 12));
			s.setLayoutX(10);
			s.setLayoutY(50);
			s.setWrapText(true);
			ap.getChildren().add(s);
			grid.add(ap, 0, y);
		}
		scrollAnchor.setPrefHeight(80 * usersByQuery.size());
		scrollAnchor.getChildren().add(grid);
	}

}
