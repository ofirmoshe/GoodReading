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
import i_book.User;

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

	public void initialize() {
		super.initialize();
	}

	public void loginOnHover() {
		loginButton.setImage(new Image(GraphicsImporter.class.getResource("button_hover.png").toString()));
	}

	public void loginOffHover() {
		loginButton.setImage(new Image(GraphicsImporter.class.getResource("button.png").toString()));
	}

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
			Message msg=null;
			if (UserHomepageController.books == null) 
				msg = new Message("login", 1, u);
			else 
				msg = new Message("login", 2, u);
			Client.instance.sendToServer(msg);
		} catch (Exception e) {
			wrongHost.setVisible(true);
		}

	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		Object[] a = (Object[]) msg.getMsg();
		if(UserHomepageController.books==null)
			UserHomepageController.books = (Book[]) a[0];
		switch (msg.getFunc()) {
		case 1:
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
					loginLabel.setTranslateX(loginLabel.getTranslateX() - 6);
				}
			});
			ClientUI.user = (GeneralUser) a[1];
			if(ClientUI.user instanceof User){
				System.out.println("ins");
				User u = (User)ClientUI.user;
				if(u.getStatus().equals("online")|| u.getStatus().equals("banned")){
					System.out.println(u.getStatus());
					return;
				}
			}
			ClientUI.setScene("HomepageGUI.fxml");
			break;
		}
	}

	@Override
	public String toString() {
		return "Login Controller";
	}

}
