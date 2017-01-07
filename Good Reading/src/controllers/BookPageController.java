package controllers;

import java.io.IOException;

import org.orm.PersistentException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Author;
import i_book.Book;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BookPageController extends SystemController{
	
	public static Book book;
	public static Author[] authors;
	
	@FXML
	private ImageView bookImage;
	@FXML Label bookNameLabel;
	@FXML Label priceLabel;
	@FXML TextArea summaryText;
	@FXML Label authorLabel;
	
	
	public void initialize(){
		super.initialize();
		Message msg = new Message("book page",1,book.getID());
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		priceLabel.setText(s+"$");
		
	}
	
	@Override
	public void handleMessage(Message msg) {
		switch(msg.getFunc()){
		case 1:
			authors = (Author[])msg.getMsg();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					String s = "by "+authors[0].getName();
					for(int i=1; i<authors.length; i++)
						s = s+", "+authors[i];
					authorLabel.setText(s);
				}
			});
		}
	}

}
