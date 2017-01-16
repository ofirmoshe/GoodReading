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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

/**
 * Search book controller is the controller of the search book page.
 * 
 * @author guyzi
 *
 */
public class ManageReviewController extends SystemController {

	private GridPane grid = null;
	private Review[] rev;
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
		try {
			Client.instance.sendToServer(new Message("manage review", 1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is called when the search button is clicked, or when Enter
	 * key is pressed. Sends the input query to the server.
	 */

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
				Object[] o = (Object[]) msg.getMsg();
				rev = (Review[]) o[0];
				unames = (String[]) o[1];
				titles = (String[]) o[2];
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
		case 3:
			if (msg.getMsg().equals("s")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							Client.instance.sendToServer(new Message("manage review", 1));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Manage Reviews");
						alert.setContentText("The review has been approved successfully.");
						alert.showAndWait();
					}
				});
			} else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Manage Reviews");
						alert.setContentText("Failed to approve the review.");
						alert.showAndWait();
					}
				});
			}
			break;
		case 4:
			if (msg.getMsg().equals("s")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							Client.instance.sendToServer(new Message("manage review", 1));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Manage Reviews");
						alert.setContentText("The review has been deleted successfully.");
						alert.showAndWait();
					}
				});
			} else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Manage Reviews");
						alert.setContentText("Failed to delete the review.");
						alert.showAndWait();
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
			ap.setPrefHeight(100);
			ap.setPrefWidth(900);
			Rectangle rect = new Rectangle();
			rect.setHeight(100);
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
			Label title = new Label("in " + titles[y]);
			title.setFont(Font.font("System", 12));
			title.setLayoutX(10);
			title.setLayoutY(30);
			ap.getChildren().add(title);
			TextArea r = new TextArea(rev[y].getText());
			r.setEditable(false);
			Button be = new Button("Edit");
			be.setLayoutX(700);
			be.setLayoutY(35);
			be.setUserData(y);
			be.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (be.getText().equals("Edit")) {
						r.setEditable(true);
						be.setText("Save");

					} else {
						if (!r.getText().equals("")) {
							be.setText("Edit");
							Object[] msg = new Object[2];
							msg[0] = r.getText();
							msg[1] = rev[(int) be.getUserData()].getID();
							try {
								Client.instance.sendToServer(new Message("manage review", 2, msg));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						else{
							Platform.runLater(new Runnable() {
							@Override
							public void run() {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setContentText("Empty review.");
								alert.showAndWait();
							}
						});
							
						}
					}
				
				}
			});
			Button ba = new Button("Approve");
			ba.setLayoutX(745);
			ba.setLayoutY(35);
			ba.setUserData(y);
			ba.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					Object msg = new Object();
					msg = rev[(int) be.getUserData()].getID();
					try {
						Client.instance.sendToServer(new Message("manage review", 3, msg));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			Button bd = new Button("Deny");
			bd.setLayoutX(810);
			bd.setLayoutY(35);
			bd.setUserData(y);
			bd.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					Object msg = new Object();
					msg = rev[(int) be.getUserData()].getID();
					try {
						Client.instance.sendToServer(new Message("manage review", 4, msg));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			r.setFont(Font.font("System", 12));
			r.setLayoutX(10);
			r.setLayoutY(50);
			r.setPrefWidth(250);
			r.setPrefHeight(15);
			r.setWrapText(true);
			ap.getChildren().add(be);
			ap.getChildren().add(ba);
			ap.getChildren().add(bd);
			ap.getChildren().add(r);
			grid.add(ap, 0, y);
		}
		scrollAnchor.setPrefHeight(101 * rev.length);
		scrollAnchor.getChildren().add(grid);
	}

}
