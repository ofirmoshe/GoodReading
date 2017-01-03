package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import i_book.GeneralUser;
import i_book.IBookIncPersistentManager;
import org.orm.*;
import org.hibernate.*;

public class LoginController {
	
	@FXML private Button loginButton;
	@FXML private TextField idField;
	@FXML private PasswordField passField;

	
	public void loginOnClick(ActionEvent event) throws Exception{
		String id = idField.getText();
		String pass = passField.getText();
		PersistentSession session = IBookIncPersistentManager.instance().getSession();
		GeneralUser u =GeneralUser.loadGeneralUserByORMID(session,id);
		if(u.getPassword().equals(pass)){
			loginButton.setText("hi "+u.getFname());
		}

		session.close();
	}

}
