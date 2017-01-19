package controllers;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Employee;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LibrarianHomepageController extends SystemController {

	@FXML
	private Button addUserButton;
	@FXML
	private Label posLabel;
	@FXML
	private TextField editBookField;

	public void initialize() {
		super.initialize();
		Client.refresh();
	}

	public void addBookOnClick() {
		ClientUI.setScene("AddNewBookGUI.fxml");
	}

	public void inventoryOnClick() {
		ClientUI.setScene("InventoryManagementGUI.fxml");
	}

	public void manageReviewOnClick() {
		ClientUI.setScene("ManageReviewGUI.fxml");
	}

	public void editBookOnClick() {
		SearchBookController.what="edit book";
		ClientUI.setScene("SearchBookGUI.fxml");
	}

	public void addUserOnClick() {
		ClientUI.setScene("AddNewUserGUI.fxml");
	}

	@Override
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			if (msg.getMsg().equals("s")) {
				EditBookController.book_id = Integer.parseInt(editBookField.getText());
				ClientUI.setScene("EditBookGUI.fxml");
			} else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Edit Book");
						alert.setContentText("Non valid book id, try again.");
						alert.showAndWait();
					}
				});
			}
			break;
		}
	}

}
