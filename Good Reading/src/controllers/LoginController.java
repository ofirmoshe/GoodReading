package controllers;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import i_book.Author;
import i_book.Book;
import i_book.GeneralUser;
import i_book.IBookIncPersistentManager;
import i_book.User;

import org.orm.*;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;

import java.io.IOException;
import java.io.Serializable;

import org.hibernate.*;

/**
 * Login controller is the controller of the first page the user sees. In this
 * page the user (Client and/or Manager) is logging into the system.
 * 
 * @author guyzi
 *
 */
public class LoginController extends AbstractController {

	final public static int DEFAULT_PORT = 5555;

	@FXML
	public TextField hostField;
	@FXML
	public ImageView loginButton;
	@FXML
	public TextField idField;
	@FXML
	public PasswordField passField;
	@FXML
	public ImageView wrongHost;
	@FXML
	public ImageView wrongUser;
	@FXML
	public ImageView wrongPass;
	@FXML
	public Label loginLabel;

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
	}

	public void loginOnHover() {
		loginButton.setImage(new Image(GraphicsImporter.class.getResource("button_hover.png").toString()));
	}

	public void loginOffHover() {
		loginButton.setImage(new Image(GraphicsImporter.class.getResource("button.png").toString()));
	}

	/** This method is called when the login button has been clicked.
	 * It creates a new client and transfers the input login data to the server to check validation.
	 * If the client can't connect it displays a red x sign besides the host field.
	 * @throws Exception
	 */
	public void loginOnClick() throws Exception {
		wrongHost.setVisible(false);
		wrongUser.setVisible(false);
		wrongPass.setVisible(false);
		try {
			Client client;
			String host = hostField.getText();
			String id = idField.getText();
			String pass = passField.getText();
			GeneralUser u = new GeneralUser();
			if (Client.instance != null) {
				Client.instance.closeConnection();
			}
			client = new Client(host, DEFAULT_PORT);
			u.setID(id);
			u.setPassword(pass);
			Message msg = null;
			msg = new Message("login", 1, u);
			Client.instance.sendToServer(msg);
		} catch (Exception e) {
			wrongHost.setVisible(true);
		}

	}

	/**
	 * This method overrides the method of abstract controller.
	 * @param msg	case 1: this message is an object array where the first index is a book array
	 * contains all the books in DB, and the second index is a message about the user that tries to connect.
	 * if the username and the password are correct the message will be an instance of this user. 
	 * if the user is not banned or online already it displays the matching homepage.
	 */
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.getFunc()) {
		case 1:
			Object[] a = (Object[]) msg.getMsg();
			UserHomepageController.books = (Book[]) a[0];
			if (a[1].equals("wrong username")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						wrongUser.setVisible(true);
					}
				});
				return;
			}
			if (a[1].equals("wrong password")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						wrongPass.setVisible(true);
					}
				});
				return;
			}
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					loginLabel.setText("loading");
					loginLabel.setTranslateX(-5);
				}
			});
			ClientUI.user = (GeneralUser) a[1];
			if (ClientUI.user instanceof User) {
				User u = (User) ClientUI.user;
				if (u.getStatus().equals("online") || u.getStatus().equals("banned")) {
					System.out.println(u.getStatus());
					return;
				}
			}
			ClientUI.setScene("UserHomepageGUI.fxml");
			break;
		}
	}

	@Override
	public String toString() {
		return "Login Controller";
	}

}
