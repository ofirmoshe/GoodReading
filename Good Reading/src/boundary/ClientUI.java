package boundary;

import java.io.IOException;

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

public class ClientUI extends Application {
	
	/*this is the primary stage*/
	public static Stage primaryStage;
	public static Parent lastRoot;
	public static GeneralUser user;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage= primaryStage;
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
			lastRoot = primaryStage.getScene().getRoot();
			Parent root = FXMLLoader.load(ClientUI.class.getResource(fxml));
			primaryStage.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't load fxml.");
		}
	}
	
	public static void goBack(){
		primaryStage.getScene().setRoot(lastRoot);
	}
}
