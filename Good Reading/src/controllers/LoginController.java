package controllers;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import i_book.Author;
import i_book.Book;
import i_book.Employee;
import i_book.GeneralUser;
import i_book.IBookIncPersistentManager;
import i_book.User;
import i_book.User_Membership;

import org.orm.*;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

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
	@FXML
	private AnchorPane mainAnchor;
	@FXML
	private ProgressBar loadingBar;

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

	/**
	 * This method is called when the login button has been clicked. It creates
	 * a new client and transfers the input login data to the server to check
	 * validation. If the client can't connect it displays a red x sign besides
	 * the host field.
	 * 
	 * @throws Exception
	 */
	public void loginOnClick() throws Exception {
		wrongHost.setVisible(false);
		wrongUser.setVisible(false);
		wrongPass.setVisible(false);
		try {
			String host = hostField.getText();
			String id = idField.getText();
			String pass = passField.getText();
			GeneralUser u = new GeneralUser();
			if (Client.instance != null) {
				Client.instance.closeConnection();
			}
			new Client(host, DEFAULT_PORT);
			u.setID(id);
			u.setPassword(pass);
			Message msg = null;
			msg = new Message("login", 1, u);
			Client.instance.sendToServer(msg);
		} catch (Exception e) {
			wrongHost.setVisible(true);
		}

	}

	public void loading() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				loadingBar.setVisible(true);
				Task<Void> sleeper = new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
						}
						return null;
					}
				};
				sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
					@Override
					public void handle(WorkerStateEvent event) {
						loadingBar.setProgress(loadingBar.getProgress() + 0.01);
						if (loadingBar.getProgress() < 1)
							loading();
					}
				});
				new Thread(sleeper).start();
			}
		});
	}

	/**
	 * This method overrides the method of abstract controller.
	 * 
	 * @param msg
	 *            case 1: this message is an object array where the first index
	 *            is a book array contains all the books in DB, and the second
	 *            index is a message about the user that tries to connect. if
	 *            the username and the password are correct the message will be
	 *            an instance of this user. if the user is not banned or online
	 *            already it displays the matching homepage.
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
			ClientUI.user = (GeneralUser) a[1];
			if (ClientUI.user instanceof User) {
				User u = (User) ClientUI.user;
				if (u.getStatus().equals("online")) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Alert alert = new Alert(AlertType.WARNING);
							alert.setContentText("You are already ONLINE.");
							alert.showAndWait();

						}
					});
					return;
				} else if (u.getStatus().equals("banned")) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Alert alert = new Alert(AlertType.WARNING);
							alert.setContentText("You are BANNED!!");
							alert.showAndWait();

						}
					});

					return;
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						loading();
					}
				});

				ClientUI.member = (User_Membership) a[2];
				ClientUI.setScene("UserHomepageGUI.fxml");
			}
			if (ClientUI.user instanceof Employee) {
				Employee emp = (Employee) ClientUI.user;
				switch (emp.getPosition()) {
				case "Librarian":
					ClientUI.setScene("LibrarianHomepageGUI.fxml");
					break;
				case "Library Manager":
					ClientUI.setScene("LibraryManagerHomepageGUI.fxml");
					break;
				case "Library Employee":
					ClientUI.setScene("LibraryEmployeeHomepageGUI.fxml");
					break;
				case "Editor":
					ClientUI.setScene("ManageReviewGUI.fxml");
				case "Manager":
					ClientUI.setScene("ManagerHomepageGUI.fxml");
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Login Controller";
	}

}

class MyTimeTask extends TimerTask {

	public void run() {
		// write your code here
	}
}