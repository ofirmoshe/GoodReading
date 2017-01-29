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

/**
 * Library manager homepage controller is the controller of the library manager homepage.
 * @author Noy
 *
 */
public class LibraryManagerHomepageController extends LibrarianHomepageController {

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
	}
	
	/**
	 * This function is called whenever "Book Report" button is pressed.
	 * This method shows manager "search book" page.
	 */
	public void bookReportOnClick(){
		SearchBookController.what  = "book report";
		ClientUI.setScene("SearchBookGUI.fxml");
	}
	
	/**
	 * This function is called whenever "User Report" button is pressed.
	 * This method shows manager "search user" page.
	 */
	public void manageUserOnClick(){
		ClientUI.setScene("SearchUserGUI.fxml");
	}
}
