package Fixtures;

import java.io.IOException;
import java.util.IllegalFormatException;

import client.Client;
import controllers.AddBookController;
import controllers.BookPageController;
import fit.ActionFixture;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AddReview extends ActionFixture{

	public BookPageController instance;
	public TextArea reviewText;
	public Label priceLabel;
	public Label langLabel;
	public TextArea summary;
	public TextArea table;
	public Label author;
	public ImageView bookImage;
	public Label bookName;
	public AnchorPane mainAnchor;
	String userID="15";
	int bookID=1;
	
	public void startReview(){
		instance = new BookPageController();
		try {
			new Client("localhost", 5555);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new JFXPanel();
		reviewText=new TextArea();
		priceLabel= new Label();
		langLabel= new Label();
		summary=new TextArea();
		table=new TextArea();
		author = new Label();
		bookImage = new ImageView();
		bookName= new Label();
		mainAnchor = new AnchorPane();
		TextArea reviewText = new TextArea();
		instance.userID=userID;
		instance.bookID=bookID;
		instance.priceLabel=priceLabel;
		instance.bookImage=bookImage;
		instance.summaryText=summary;
		instance.tableText=table;
		instance.authorLabel=author;
		instance.bookNameLabel=bookName;
		instance.mainAnchor=mainAnchor;
		instance.initialize();
		instance.initOver=false;
		instance.reviewable=false;
		instance.reviewOver=false;
		while(!instance.initOver)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public void setReview(String text)
	{
		reviewText.setText(text);
		instance.addReviewText=reviewText;
	}
	
	public void setUser(String ID){
		userID=ID;
		instance.userID=userID;
	}
	public void setBook(int ID){
		bookID=ID;
		instance.bookID = bookID;
	}
	
	public boolean addReview()
	{
		instance.addReview();
		while(!instance.reviewOver)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return instance.reviewable;
	}
	
}
