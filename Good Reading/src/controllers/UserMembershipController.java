package controllers;

import java.io.IOException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Membership;
import i_book.User_Membership;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * User membership controller is the controller of the user memberships.
 * @author Noy
 *
 */
public class UserMembershipController extends SystemController {

	@FXML
	private Label nameLabel;
	@FXML
	private Label startDateLabel;
	@FXML
	private Label endDateLabel;

	/**
	 * This method initializes the controller (display the user's membership info)
	 */
	public void initialize() {
		super.initialize();
		nameLabel.setText(ClientUI.user.getFname() + " " + ClientUI.user.getLname() + ",");
		startDateLabel.setText("Since: " + ClientUI.member.getS_date().toString());
		endDateLabel.setText("Until: " + ClientUI.member.getE_date().toString());
	}

	@Override
	public void handleMessage(Message msg) {
	}

}
