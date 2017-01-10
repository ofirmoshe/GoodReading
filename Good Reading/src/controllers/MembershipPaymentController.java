package controllers;

import java.io.IOException;
import java.util.Date;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Author;
import i_book.Book;
import i_book.Membership;
import i_book.PaymentRequest;
import i_book.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MembershipPaymentController extends SystemController {

	public static Membership membership;
	private Membership currMembership;

	@FXML
	private Label membershipNameLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private TextField paymentInfoField;

	public void initialize() {
		super.initialize();
		currMembership = membership;
		membershipNameLabel.setText(membership.getType()+" Memberhip");
		String s = new String();
		float f = membership.getPrice();
		if (f == (long) f)
			s = String.format("%d", (long) f);
		else
			s = String.format("%s", f);
		priceLabel.setText(s + "$");
		User u = (User) ClientUI.user;
		paymentInfoField.setText(u.getPaymentInfo());
	}

	public void paymentOnClick() {
		Object[] o = new Object[4];
		o[0] = ClientUI.user.getID();
		o[1] = currMembership.getID();
		o[2] = paymentInfoField.getText();
		Message msg = new Message("membership payment", 1, o);
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
			String s = (String) msg.getMsg();
			if (s.equals("s")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Payment Request");
						alert.setHeaderText("Payment Request Received");
						alert.setContentText("We will try to accept it as soon as we can.");
						alert.showAndWait();
					}
				});
			} else if (s.equals("d")) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Payment Request");
						alert.setHeaderText("Payment Request Denied");
						alert.setContentText("You already have an active membership payment request.");
						alert.showAndWait();
					}
				});
			} else if (s.equals("f")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Payment Request");
						alert.setHeaderText("Payment Request Failed");
						alert.setContentText("Please try again.");
						alert.showAndWait();
					}
				});
			}
		}

	}

}
