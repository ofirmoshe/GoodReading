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

public class LibraryManagerHomepageController extends LibrarianHomepageController {

	@FXML
	private TextField userReportField;

	public void initialize() {
		super.initialize();
	}

	public void getUserReportOnClick() {
		if (!userReportField.getText().equals("")) {
			UserReportController.userID = userReportField.getText();
			ClientUI.setScene("UserReportGUI.fxml");
		} else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Empty Field.");
					alert.showAndWait();
				}
			});

		}
	}
	
	public void bookReportOnClick(){
		SearchBookController.what  = "book report";
		ClientUI.setScene("SearchBookGUI.fxml");
	}
}
