package boundary;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;

import org.orm.PersistentSession;

import client.Client;
import common.Message;
import controllers.AbstractController;
import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import i_book.GeneralUser;
import i_book.User;
import i_book.Author;
import i_book.Book;

public class ClientUI extends Application {

	/* this is the primary stage */
	public static Stage primaryStage;
	public static Parent lastRoot;
	public static GeneralUser user;
	public static Stack<Parent> pageStack = new Stack<Parent>();
	public static  ClientUI instance;

	@Override
	public void start(Stage primaryStage) throws IOException {
		instance = this;
		ClientUI.primaryStage = primaryStage;
		primaryStage.setTitle("I-Book - Good Reading");
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				if (Client.instance != null) {
					try {
						if (ClientUI.user instanceof User) {
							Message msg = new Message("system", 1, ClientUI.user);
							Client.instance.sendToServer(msg);
						}
						Client.instance.closeConnection();
						System.out.println("Connection closed.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Can't close connection.");
					}
				}
				System.exit(0);
			}
		});
		Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
		lastRoot = root;
		Scene scene = new Scene(root, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public static void setScene(String fxml) {
		try {
			pageStack.push(primaryStage.getScene().getRoot());
			Parent root = FXMLLoader.load(ClientUI.class.getResource(fxml));
			primaryStage.getScene().setRoot(root);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void goBack() {
		primaryStage.getScene().setRoot(pageStack.pop());

	}
	
	public static Author[] getAuthors(Book book){
		return book.author.toArray();
	}
}
