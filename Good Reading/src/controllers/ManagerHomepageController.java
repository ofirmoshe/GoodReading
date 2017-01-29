package controllers;

import java.io.IOException;

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
 * manager homepage controller is the controller for manager homepage.
 * @author Noy
 *
 */
public class ManagerHomepageController extends SystemController {

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
	public void userReportOnClick(){
		ClientUI.setScene("SearchUserGUI.fxml");
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}
}
