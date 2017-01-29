package controllers;

import java.io.IOException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Membership;
import javafx.fxml.FXML;

/**
 * Membership controller is the controller of the memberships
 * @author Noy
 *
 */
public class MembershipController extends SystemController {

	private Membership[] memberships;

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
		Message msg = new Message("membership", 1);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This function is called whenever "Monthly" option is pressed.
	 * This method check for monthly membership in the list from the server and displays the membership payment page for monthly membership.
	 */
	public void monthlyOnClick() {
		Membership m = null;
		for (int i = 0; i < memberships.length; i++) {
			if (memberships[i].getType().equals("Monthly")) {
				m = memberships[i];
				break;
			}
		}
		MembershipPaymentController.membership = m;
		ClientUI.setScene("MembershipPaymentGUI.fxml");
	}

	/**
	 * This function is called whenever "Yearly" option is pressed.
	 * This method check for yearly membership in the list from the server and displays the membership payment page for yearly membership.
	 */
	public void yearlyOnClick() {
		Membership m = null;
		for (int i = 0; i < memberships.length; i++) {
			if (memberships[i].getType().equals("Yearly")) {
				m = memberships[i];
				break;
			}
		}
		MembershipPaymentController.membership = m;
		ClientUI.setScene("MembershipPaymentGUI.fxml");
	}

	/**
	 * This method implements AbstractController's method. It handles message
	 * from server, and displays the user data.
	 * 
	 * @param msg
	 * The method gets the membership list from the server.
	 */
	@Override
	public void handleMessage(Message msg) {
		memberships = (Membership[]) msg.getMsg();

	}

}
