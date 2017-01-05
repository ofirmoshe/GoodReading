package boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import org.orm.PersistentSession;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import i_book.GeneralUser;
import i_book.Book;
public class ClientUI extends Application {
	
	/*this is the primary stage*/
	public static Stage primaryStage;
	public static Parent lastRoot;
	public static GeneralUser user;
	public static Stack<Parent> pageStack = new Stack<Parent>();

	@Override
	public void start(Stage primaryStage) throws IOException {
		ClientUI.primaryStage= primaryStage;
		primaryStage.setTitle("I-Book - Good Reading");
		Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
		lastRoot=root;
		Scene scene = new Scene(root,900,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
	public static void setScene(String fxml){
		try {
			pageStack.push(primaryStage.getScene().getRoot());
			Parent root = FXMLLoader.load(ClientUI.class.getResource(fxml));
			primaryStage.getScene().setRoot(root);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void goBack(){
		primaryStage.getScene().setRoot(pageStack.pop());
		
	}
}
