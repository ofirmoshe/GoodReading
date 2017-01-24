package controllers;

import java.io.IOException;
import java.util.Date;

import client.Client;
import common.Message;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user.getStatus().equals("blocked"))
			blockedCheckbox.setSelected(true);

	}

	public void editUserOnClick() {
		Object[] o = new Object[7];
		boolean mustFlag = false;
		if (fnameField.equals(""))
			mustFlag = true;
		else
			o[0] = fnameField.getText();
		if (lnameField.equals(""))
			mustFlag = true;
		else
			o[1] = lnameField.getText();
		if (idField.equals(""))
			mustFlag = true;
		else
			o[2] = idField.getText();
		if (passField.equals(""))
			mustFlag = true;
		else
			o[3] = passField.getText();
		o[4] = payField.getText();
		o[5] = memberCheckbox.isSelected();
		o[6] = blockedCheckbox.isSelected();
		if (mustFlag) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("One or more required fields are empty.");
			alert.showAndWait();
			return;
		}
		Message msg = new Message("manage user", 1, o);
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
			 um = (User_Membership[])msg.getMsg();
			 Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (um.length != 0) {
							if (um[um.length - 1].getE_date().after(new Date())) {
								// Membership is blocked
								memberCheckbox.setVisible(true);
								memberCheckbox.setText("Owns membership until: " + um[um.length - 1].getE_date().toString());
								if (um[um.length - 1].getStatus().equals("blocked"))
									memberCheckbox.setSelected(false);
							}
						};
					}
				});
				
		}
	}
}
