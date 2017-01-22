package controllers;

import java.io.IOException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Membership;
import javafx.fxml.FXML;

public class MembershipController extends SystemController {

	private Membership[] memberships;

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

	@Override
	public void handleMessage(Message msg) {
		memberships = (Membership[]) msg.getMsg();

	}

}
