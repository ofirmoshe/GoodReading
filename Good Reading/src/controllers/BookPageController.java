package controllers;

import java.io.IOException;

import org.orm.PersistentException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Author;
import i_book.Book;
import i_book.Field;
import i_book.Subject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Book page controller is the controller of all the book pages. The controller
 * match the page to display the data of the book to be shown (according to a
 * static book variable).
 * 
 * @author guyzi
 *
 */
public class BookPageController extends SystemController {

	public static Book book;
	private Author[] authors;
	private Field[] fields;
	private Subject[] subjects;
	private Book currBook;
	
	@FXML
	private ImageView bookImage;
	@FXML
	Label bookNameLabel;
	@FXML
	Label priceLabel;
	@FXML
	TextArea summaryText;
	@FXML
	Label authorLabel;
	@FXML
	Label fieldLabel;
	@FXML
	Label subjectLabel;
	@FXML
	AnchorPane mainAnchor;

	/**
	 * This method initializes the controller, sends a message to server to get
	 * the authors, fields and subjects.
	 */
	public void initialize() {
		super.initialize();
		Message msg = new Message("book page", 1, book.getID());
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currBook = book;
		Image img = new Image(book.getImage());
		bookImage.setImage(img);
		bookImage.setFitWidth(180);
		bookImage.setFitHeight(270);
		bookNameLabel.setText(book.getTitle());
		String s = new String();
		float f = book.getPrice();
		if (f == (long) f)
			s = String.format("%d", (long) f);
		else
			s = String.format("%s", f);
		priceLabel.setText(s + "$");
		summaryText.setText(book.getSummary());

	}

	/**
	 * This method is called when "Get this book" button is clicked.
	 */
	public void getBookOnClick() {
		BookPaymentController.book=currBook;
		BookPaymentController.authors = authors;
		ClientUI.setScene("BookPaymentGUI.fxml");
	}

	/**
	 * This method implements AbstractController's method.
	 * It handles message from server, and displays the book's data. 
	 * @param msg	case 1: the message is an object array. 
	 * 						index 0- contains an Author array
	 * 						index 1- contains a Field array 
	 *						index 2- contains a Subject array 
	 */
	@Override
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			Object[] m = (Object[]) msg.getMsg();
			authors = (Author[]) m[0];
			fields = (Field[]) m[1];
			subjects = (Subject[]) m[2];
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					String s = "by " + authors[0].getName();
					for (int i = 1; i < authors.length; i++)
						s = s + ", " + authors[i].getName();
					authorLabel.setText(s);
					s = fields[0].getField();
					for (int i = 1; i < fields.length; i++)
						s = s + ", " + fields[i].getField();
					fieldLabel.setText(s);
					if (subjects.length != 0) {
						s = subjects[0].getSub();
						for (int i = 1; i < subjects.length; i++)
							s = s + ", " + subjects[i].getSub();
						subjectLabel.setText(s);
					} else
						subjectLabel.setText("");
				}

			});
		}
	}

}
