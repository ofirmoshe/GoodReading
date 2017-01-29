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
import i_book.GeneralUser;
import i_book.Membership;
import i_book.PaymentRequest;
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
public class LibraryEmployeeHomepageController extends SystemController {

	private GridPane grid = null;
	private PaymentRequest[] payments;
	private GeneralUser[] users;
	private Book[] books;
	private Membership[] memberships;
	@FXML
	private AnchorPane scrollAnchor;

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
		scrollAnchor.setPrefHeight(200);
		try {
			Client.instance.sendToServer(new Message("employee homepage", 1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  This method implements AbstractController's method. It handles message
	 * from server, and displays the inventory data.
	 * 
	 * @param msg
	 *            case 1: 
	 *              The message includes: a payment request array, the users and books. It sets the list of all payment request in DB.
	 * in case that there are no payment request the method presents a proper message that inform the user- "There are not awaiting payment requests at this moment."
	 */
	public void handleMessage(Message msg) { super.handleMessage(msg);
		switch (msg.getFunc()) {
		case 1:
			if (msg.getMsg() != null) {
				Object[] o = (Object[]) msg.getMsg();
				payments = (PaymentRequest[]) o[0];
				users = (GeneralUser[]) o[1];
				books = (Book[]) o[2];
				memberships = (Membership[]) o[3];

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (grid != null)
							scrollAnchor.getChildren().remove(grid);
						if (payments.length == 0) {
							StackPane sp = new StackPane();
							sp.setPrefHeight(200);
							sp.setPrefWidth(900);
							Label noRes = new Label("There are not awaiting payment requests at this moment.");
							noRes.setTextFill(Color.DARKSLATEGRAY);
							noRes.setFont(Font.font("System", 20));
							sp.getChildren().add(noRes);
							StackPane.setAlignment(noRes, Pos.CENTER);
							scrollAnchor.getChildren().add(sp);
							scrollAnchor.setPrefHeight(200);
							return;
						}
						setPaymentsGrid();
					}
				});
			}
			break;
		}
	}

	/**
	 * This function is called whenever the "User Management" is pressed.
	 * This method shows the employee the "search user" page.
	 */
	public void userManagementOnClick()
	{
		ClientUI.setScene("SearchUserGUI.fxml");
	}
	
	/**
	 * This function is called whenever the "Add New User" is pressed.
	 * This method shows the employee the "Add User" page.
	 */
	public void registerOnClick() {
		ClientUI.setScene("AddNewUserGUI.fxml");
	}

	/**
	 * This method sets the payment request grid with payment requests, and event handlers for each
	 * request ("Deny" or "Approve" buttons).
	 * in case "Approve" was pressed - send msg to server with msg.func = 2
	 * in case "Dent" was pressed - send msg to server with msg.func = 3
	 * The payment request grid is added to the scroll anchor.
	 */
	public void setPaymentsGrid() {
		grid = new GridPane();
		for (int y = 0; y < payments.length; y++) {
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
			Label username = new Label(users[y].getFname() + " " + users[y].getLname());
			username.setTextFill(Color.DARKCYAN);
			username.setFont(Font.font("System", FontWeight.BOLD, 14));
			ap.getChildren().add(username);
			username.setLayoutX(10);
			username.setLayoutY(10);
			Label paymentinfoLabel = new Label("Using payment info: " + payments[y].getPaymentInfo());
			paymentinfoLabel.setFont(Font.font("System", 12));
			paymentinfoLabel.setLayoutX(10);
			paymentinfoLabel.setLayoutY(50);
			ap.getChildren().add(paymentinfoLabel);
			if (books[y] != null) {
				Label bookLabel = new Label(books[y].getPrice() + "$ for " + books[y].getTitle());
				bookLabel.setFont(Font.font("System", 12));
				bookLabel.setLayoutX(10);
				bookLabel.setLayoutY(30);
				ap.getChildren().add(bookLabel);
			} else {
				Label membershipLabel = new Label(
						memberships[y].getPrice() + " for " + memberships[y].getType() + " membership");
				membershipLabel.setFont(Font.font("System", 12));
				membershipLabel.setLayoutX(10);
				membershipLabel.setLayoutY(30);
				ap.getChildren().add(membershipLabel);
			}
			Label dateLabel = new Label(payments[y].getDate().toString());
			dateLabel.setFont(Font.font("System", 12));
			dateLabel.setLayoutX(10);
			dateLabel.setLayoutY(70);
			ap.getChildren().add(dateLabel);
			Button ba = new Button("Approve");
			ba.setLayoutX(745);
			ba.setLayoutY(35);
			ba.setUserData(y);
			ba.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					int pid = payments[(int) ba.getUserData()].getID();
					Message msg = new Message("employee homepage", 2, pid);
					try {
						Client.instance.sendToServer(msg);
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
					int pid = payments[(int) ba.getUserData()].getID();
					Message msg = new Message("employee homepage", 3, pid);
					try {
						Client.instance.sendToServer(msg);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			ap.getChildren().add(ba);
			ap.getChildren().add(bd);
			grid.add(ap, 0, y);
		}
		scrollAnchor.setPrefHeight(101 * payments.length);
		scrollAnchor.getChildren().add(grid);
	}

}
