package controllers;

import java.io.IOException;


import java.util.Date;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Employee;
import i_book.Membership;
import i_book.User;
import i_book.User_Membership;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;

/**
 * @author Ofir
 * Manage User Controller; Associated to the ManageUserGUI.fxml file. 
 * Basically, allows edit, update and delete user's information.
 * This page displays different functionalities to different positions (eg. librarian can not block user).
 */

public class ManageUserController extends SystemController {
	public static User user;
	@FXML
	private TextField fnameField;
	@FXML
	private TextField lnameField;
	@FXML
	private TextField idField;
	@FXML
	private PasswordField passField;
	@FXML
	private TextField payField;
	@FXML
	private CheckBox blockedCheckbox;
	@FXML
	private CheckBox memberCheckbox;
	private User_Membership[] um;
	
	/** 
	 * 
	 *  This method initializes the controller, it uses a public static variable named user,
	 *  which initialized right after a specific user gets picked on the user-search list.
	 *  Using that user variable, we fill up the fields with the current user's information (first+last name, id (uneditable), password (hidden), payment info and status).
	 *  Next, the method requests to get the user's membership information, if exists. It will get the information at handleMessage function.
	 *  
	 */
	
	public void initialize() {
		super.initialize();
		fnameField.setText(user.getFname());
		lnameField.setText(user.getLname());
		idField.setText(user.getID());
		idField.setEditable(false);
		passField.setText(user.getPassword());
		payField.setText(user.getPaymentInfo());
		Message msg = new Message("manage user", 2, idField.getText());
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (user.getStatus().equals("banned"))
			blockedCheckbox.setSelected(true);
	}
	/** 
	 * 
	 * This method called right after the 'Edit User' button is pressed.
	 * First, The method immediately checks that the required input fields (first name, last name, id, password) are not empty using the mustFlag boolean parameter.
	 * In case that one or more of the fields are empty, it displays a popup message, alerts the issue. 
	 * Else, it fills up an Object array with the user data and sends it to the server, requesting to update that user in database.
	 * 
	 */
	public void editUserOnClick() {
		Object[] o = new Object[7];
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
		if(memberCheckbox.isVisible()) o[5] = memberCheckbox.isSelected();
		if(blockedCheckbox.isVisible())o[6] = blockedCheckbox.isSelected();
		if (mustFlag == true) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("One or more required fields are empty.");
			alert.showAndWait();
			return;
		}
		Message msg = new Message("manage user", 1, o);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method implements AbstractController's method. It handles message
	 * from server.
	 * 
	 * @param msg
	 *            case 1: 
	 *				The message is string, tells if the user data changes were saved successfully.
	 *				if it was, it displays a popup message informing the success.
	 *				else, it shows an error message, asking the user to try again.
	 *            case 2: 
	 *            	The message is an array of user's memberships. 
	 *            	In case that there is an active membership to the user,
	 *            	it displays a checkbox to the screen, including the membership start and end date.
	 *            	It will be displayed to the employee if his position is librarian or librarian employee.
	 */
	
	@Override
	public void handleMessage(Message msg) { super.handleMessage(msg);
		switch (msg.getFunc()) {
		case 1:
			if (msg.getMsg().equals("s")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setContentText("Changes has been saved successfully.");
						alert.showAndWait();
					}
				});
			} else if (msg.getMsg().equals("f")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setContentText("Edit User Info Failed");
						alert.showAndWait();
					}
				});
			}

			break;
		case 2:
			um = (User_Membership[]) msg.getMsg();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					if (um.length != 0) {
						if (um[um.length - 1].getE_date().after(new Date())) {
							// Membership is blocked
							memberCheckbox.setVisible(true);
						
							memberCheckbox
									.setText("Owns membership until: " + um[um.length - 1].getE_date().toString());
							if (um[um.length - 1].getStatus().equals("blocked"))
								memberCheckbox.setSelected(false);
							
						}
					}
					;
					if (ClientUI.user instanceof Employee)
						if (((Employee) ClientUI.user).getPosition().equals("Librarian")
								|| ((Employee) ClientUI.user).getPosition().equals("Library Employee")) {
							memberCheckbox.setVisible(false);
							blockedCheckbox.setVisible(false);
						}
				}
			});

			break;
		}
	}
}
