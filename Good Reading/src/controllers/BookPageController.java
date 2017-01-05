package controllers;

import common.Message;
import i_book.Book;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BookPageController extends SystemController{
	
	public static Book book;
	
	@FXML
	private ImageView bookImage;
	
	public void initialize(){
		super.initialize();
		Image img = new Image(book.getImage());
		bookImage.setImage(img);
		bookImage.setFitWidth(180);
		bookImage.setFitHeight(270);
	}
	
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
