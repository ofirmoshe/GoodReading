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
 * Librarian homepage controller is the controller of the librarian homepage.
 * @author guyzi
 *
 */
public class LibrarianHomepageController extends SystemController {

	@FXML
	private Button addUserButton;
	@FXML
	private Label posLabel;
	@FXML
	private TextField editBookField;

	/**
	 * This method initializes the controller, and refreshes the client
	 * (so the updated data in DB will be displayed).
	 */
	public void initialize() {
		super.initialize();
		Client.refresh();
	}

	/**
	 * This method is called when the "Add Book" button is pressed.
	 * The method redirect the user to add book page.
	 */
	public void addBookOnClick() {
		ClientUI.setScene("AddNewBookGUI.fxml");
	}
	
	/**
	 * This method is called when the "User Management" button is pressed.
	 * The method redirect the user to the user management page.
	 */
	public void manageUserOnClick() {
		ClientUI.setScene("SearchUserGUI.fxml");
	}

	/**
	 * This method is called when the "Inventory Management" button is pressed.
	 * The method redirect the user to the inventory management page.
	 */
	public void inventoryOnClick() {
		ClientUI.setScene("InventoryManagementGUI.fxml");
	}

	/**
	 * This method is called when the "Review Management" button is pressed.
	 * The method redirect the user to the review management page.
	 */
	public void manageReviewOnClick() {
		ClientUI.setScene("ManageReviewGUI.fxml");
	}

	/**
	 * This method is called when the "Search & Edit Book" button is pressed.
	 * The method tells the search book controller to what page the search results
	 * should redirect the user, and then redirects the user to the search book page.
	 */
	public void editBookOnClick() {
		SearchBookController.what="edit book";
		ClientUI.setScene("SearchBookGUI.fxml");
	}

	/**
	 * This method is called when the "Register User" button is pressed.
	 * The method redirect the user to the add user page. 
	 */
	public void addUserOnClick() {
		ClientUI.setScene("AddNewUserGUI.fxml");
	}

}
