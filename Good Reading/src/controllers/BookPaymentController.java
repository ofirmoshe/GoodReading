package controllers;

import boundary.ClientUI;
import common.Message;
import i_book.Author;
import i_book.Book;
import i_book.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BookPaymentController extends SystemController {
	
	public static Book book;
	private Book currBook;
	public static Author[] authors;
	
	@FXML
	 ImageView bookImage;
	@FXML
	private Label bookNameLabel;
	@FXML
	private Label authorLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private TextField paymentInfoField;
	
	public void initialize(){
		super.initialize();
		currBook=book;
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
		User u = (User)ClientUI.user;
		paymentInfoField.setText(u.getPaymentInfo());
	}
	
	

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
