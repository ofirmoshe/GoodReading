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

public class ManagerHomepageController extends SystemController {

	public void initialize() {
		super.initialize();
	}

	public void bookReportOnClick(){
		SearchBookController.what  = "book report";
		ClientUI.setScene("SearchBookGUI.fxml");
	}
	public void userReportOnClick(){
		ClientUI.setScene("SearchUserGUI.fxml");
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}
}
