package controllers;

import java.io.IOException;
import java.util.Date;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Author;
import i_book.Book;
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

public class BookPaymentController extends SystemController {

	public static Book book;
	private Book currBook;
	public static Author[] authors;

	@FXML
	private ImageView bookImage;
	@FXML
	private Label bookNameLabel;
	@FXML
	private Label authorLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private TextField paymentInfoField;

	public void initialize() {
		super.initialize();
		currBook = book;
		Image img = new Image(book.getImage());
		bookImage.setImage(img);
		bookNameLabel.setText(book.getTitle());
		String s = new String();
		float f = book.getPrice();
		if (f == (long) f)
			s = String.format("%d", (long) f);
		else
			s = String.format("%s", f);
		priceLabel.setText(s + "$");
		s = "by " + authors[0].getName();
		for (int i = 1; i < authors.length; i++)
			s = s + ", " + authors[i].getName();
		authorLabel.setText(s);
		User u = (User) ClientUI.user;
		paymentInfoField.setText(u.getPaymentInfo());
	}

	public void paymentOnClick() {
		Object[] o = new Object[4];
		o[0] = ClientUI.user.getID();
		o[1] = currBook.getID();
		o[2] = paymentInfoField.getText();
		Message msg = new Message("book payment", 1, o);
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
						alert.setContentText("You have already requested to pay for this book.");
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
