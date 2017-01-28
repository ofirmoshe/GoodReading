package controllers;

import java.io.IOException;

import client.Client;
import common.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
/** 
 * Add User Controller. 
 * @author guyzi
 *
 */
public class AddUserController extends SystemController {

	@FXML
	private TextField fnameField;
	@FXML
	private TextField lnameField;
	@FXML
	private TextField idField;
	@FXML
	private TextField passField;
	@FXML
	private TextField payField;

	/**
	 * This method initializes the page.
	 */
	public void initialize() {
		super.initialize();
	}

	/**
	 * This method is called when the "add user" button is pressed.
	 * The method collects all the input the user entered and checks validation.
	 * if not valid the method displays a "check input" message to the user, if valid 
	 * the method sends all the data to the server to be added as a new user.
	 */
	public void addUserOnClick() {
		Object[] o = new Object[5];
		boolean mustFlag = false;
		if (fnameField.getText().equals(""))
			mustFlag = true;
		else
			o[0] = fnameField.getText();
		if (lnameField.getText().equals(""))
			mustFlag = true;
		else
			o[1] = lnameField.getText();
		if (idField.getText().equals(""))
			mustFlag = true;
		else
			o[2] = idField.getText();
		if (passField.getText().equals(""))
			mustFlag = true;
		else
			o[3] = passField.getText();
		o[4] = payField.getText();
		if (mustFlag) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("One or more required fields are empty.");
			alert.showAndWait();
			return;
		}
		Message msg = new Message("add user", 1, o);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method implements AbstractController's method. It handles message
	 * from server, and displays the book's data.
	 * 
	 * @param msg
	 *            	The message is a string, indicates a success ("s") or failure ("f") of adding book method.
	 *            	It shows an appropriate popup message for each of these options. Also indicates
	 *           	if the user already exists ("e").
	 *            	 
	 */
	@Override
	public void handleMessage(Message msg) { super.handleMessage(msg);
		switch (msg.getFunc()) {
		case 1:
			if (msg.getMsg().equals("s")) {
				Platform.runLater(new Runnable(){
					@Override
					public void run()
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setContentText("New user was added successfully."); 
						alert.showAndWait();
					}
				});
			}else if(msg.getMsg().equals("e")){
				Platform.runLater(new Runnable(){
					@Override
					public void run()
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Add User Failed");
						alert.setContentText("User with the same ID already exists."); 
						alert.showAndWait();
					}
				});
			}else{
				Platform.runLater(new Runnable(){
					@Override
					public void run()
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Add User Failed");
						alert.setContentText("Please try again."); 
						alert.showAndWait();
					}
				});
			}
		}
	}
}
