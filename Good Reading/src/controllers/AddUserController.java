package controllers;

import java.io.IOException;

import client.Client;
import common.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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

	public void initialize() {
		super.initialize();
	}

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

	@Override
	public void handleMessage(Message msg) {
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
