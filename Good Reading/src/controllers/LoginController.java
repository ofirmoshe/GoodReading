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
import i_book.Book;
import i_book.GeneralUser;
import i_book.IBookIncPersistentManager;
import org.orm.*;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;

import java.io.IOException;
import java.io.Serializable;

import org.hibernate.*;

public class LoginController extends AbstractController {

	final public static int DEFAULT_PORT = 5555;
	public static Book[] books = null;

	@FXML
	public TextField hostField;
	@FXML
	public ImageView loginButton;
	@FXML
	public TextField idField;
	@FXML
	public PasswordField passField;
	@FXML
	public ImageView idx;
	@FXML
	public ImageView passx;
	@FXML
	public Label loginLabel;

	public void loginOnHover() {
		loginButton.setImage(new Image(GraphicsImporter.class.getResource("button_hover.png").toString()));
	}

	public void loginOffHover() {
		loginButton.setImage(new Image(GraphicsImporter.class.getResource("button.png").toString()));
	}

	public void loginOnClick() throws Exception {
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
			Client.instance = client;
			u.setID(id);
			u.setPassword(pass);
			Message msg = new Message("login", 1, u);
			AbstractController.instance = this;
			Client.instance.sendToServer(msg);
		} catch (Exception e) {
			System.out.println("Can't connect to host.");
		}
		
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		if (msg.getFunc() == 1) {
			if (msg.getMsg().equals("wrong username")) {
			} else if (msg.getMsg().equals("wrong password")) {
			} else {
				ClientUI.user = (GeneralUser) msg.getMsg();
				ClientUI.setScene("HomepageGUI.fxml");

			}
		}
	}

	@Override
	public String toString() {
		return "Login Controller";
	}

}
