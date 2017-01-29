package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Author;
import i_book.Book;
import i_book.PaymentRequest;
import i_book.User;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *  Book payment controller is the controller of the book payment.
 * 
 * @author Noy
 *
 */
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

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
		currBook = book;
		ByteArrayInputStream in = new ByteArrayInputStream(book.getImage());
		BufferedImage read = null;
		try {
			read = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img = SwingFXUtils.toFXImage(read, null);
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

	/**
	 * This function is called whenever "Proceed" button is pressed.
	 * This method get the payment info from the user and sends the info, the user id and book to the server.
	 */
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

	/**
	 * This method implements AbstractController's method. It handles message
	 * from server, and displays the user data.
	 * 
	 * @param msg
	 *            case 1: 
	 *             the method check the msg info and presents a proper popup, alert the inform the user. Such as "Please try again","We will try to accept it as soon as we can." ext.
	 */
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
