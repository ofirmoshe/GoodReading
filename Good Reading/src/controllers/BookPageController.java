package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.orm.PersistentException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Author;
import i_book.Book;
import i_book.Field;
import i_book.Review;
import i_book.Subject;
import i_book.User;
import i_book.User_Book;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
	private GridPane reviewGrid;
	private Review[] reviews;
	private String[] usernames;
	private String canReview;

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
	@FXML
	AnchorPane reviewAnchor;
	@FXML
	TextArea addReviewText;
	@FXML
	Label reviewSentLabel;
	@FXML
	Label addReviewLabel;
	@FXML
	Button addReviewButton;
	@FXML
	AnchorPane priceAnchor;
	@FXML
	AnchorPane formatAnchor;
	@FXML
	ChoiceBox<String> formatBox;
	@FXML
	TextArea tableText;

	/**
	 * This method initializes the controller, sends a message to server to get
	 * the authors, fields and subjects.
	 */
	public void initialize() {
		super.initialize();
		try{
		Object[] o = new Object[2];
		o[0] = book.getID();
		o[1] = ClientUI.user.getID();
		Message msg = new Message("book page", 1, o);
		Client.instance.sendToServer(msg);
		currBook = book;
		ByteArrayInputStream in = new ByteArrayInputStream(book.getImage());
		BufferedImage read;
		read = ImageIO.read(in);
		Image img = SwingFXUtils.toFXImage(read, null);
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
		tableText.setText(book.getTable_of_contents());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method is called when "Get this book" button is clicked.
	 */
	public void getBookOnClick() {
		//if user owns the book
		if(formatAnchor.isVisible()){
			String f = (String) formatBox.getSelectionModel().getSelectedItem();
			switch(f){
			case "pdf":
				ClientUI.instance.getHostServices().showDocument(currBook.getPdf());
				break;
			case "doc":
				ClientUI.instance.getHostServices().showDocument(currBook.getDoc());
				break;
			case "fb2":
				ClientUI.instance.getHostServices().showDocument(currBook.getFb2());
				break;
			}
			return;
		}
		BookPaymentController.book = currBook;
		BookPaymentController.authors = authors;
		ClientUI.setScene("BookPaymentGUI.fxml");
	}

	public void addReview() {
		if (!addReviewText.getText().equals("")) {
			Object[] o = new Object[3];
			o[0] = currBook.getID();
			o[1] = ClientUI.user.getID();
			o[2] = addReviewText.getText();
			Message msg = new Message("book page", 2, o);
			try {
				Client.instance.sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setReviewGrid() {
		reviewGrid = new GridPane();
		for (int y = 0; y < reviews.length; y++) {
			AnchorPane ap = new AnchorPane();
			ap.setPrefHeight(60);
			ap.setPrefWidth(900);
			Rectangle rect = new Rectangle();
			rect.setHeight(60);
			rect.setWidth(900);
			rect.setFill(Color.WHITE);
			rect.setStrokeWidth(1);
			rect.setStroke(Color.BURLYWOOD);
			ap.getChildren().add(rect);
			Label username = new Label(usernames[y]);
			username.setTextFill(Color.DARKCYAN);
			username.setFont(Font.font("System", FontWeight.BOLD, 14));
			ap.getChildren().add(username);
			username.setLayoutX(10);
			username.setLayoutY(10);
			Label rev = new Label(reviews[y].getText());
			rev.setFont(Font.font("System", 12));
			rev.setLayoutX(10);
			rev.setLayoutY(30);
			rev.setWrapText(true);
			ap.getChildren().add(rev);
			reviewGrid.add(ap, 0, y);
		}
		reviewAnchor.setPrefHeight(60 * reviews.length);
		reviewAnchor.getChildren().add(reviewGrid);
	}

	/**
	 * This method implements AbstractController's method. It handles message
	 * from server, and displays the book's data.
	 * 
	 * @param msg
	 *            case 1: the message is an object array. index 0- contains an
	 *            Author array index 1- contains a Field array index 2- contains
	 *            a Subject array
	 */
	@Override
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			Object[] m = (Object[]) msg.getMsg();
			authors = (Author[]) m[0];
			fields = (Field[]) m[1];
			subjects = (Subject[]) m[2];
			reviews = (Review[]) m[3];
			usernames = (String[]) m[4];
			canReview = (String) m[5];
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
					if (canReview.equals("no") || canReview.equals("waiting") || canReview.equals("approved")) {
						scrollAnchor.setPrefHeight(438);
						addReviewText.setVisible(false);
						addReviewLabel.setVisible(false);
						addReviewButton.setVisible(false);
						if (canReview.equals("waiting")) {
							reviewSentLabel.setText("Your review is waiting to be approved.");
							reviewSentLabel.setVisible(true);
						}
					}
					if (reviews.length != 0)
						setReviewGrid();
					if(!canReview.equals("no")){
						priceAnchor.setVisible(false);
						ObservableList<String> formats = FXCollections.observableArrayList();
						if(currBook.getPdf()!=null)
							formats.add("pdf");
						if(currBook.getDoc()!=null)
							formats.add("doc");
						if(currBook.getFb2()!=null)
							formats.add("fb2");
						formatBox.setItems(formats);
						formatBox.getSelectionModel().selectFirst();
						formatAnchor.setVisible(true);
					}
				}

			});
			break;

		case 2:
			if (msg.getMsg().equals("s")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						addReviewText.setVisible(false);
						addReviewLabel.setVisible(false);
						reviewSentLabel.setText("Thank you for the review!");
						reviewSentLabel.setVisible(true);
						addReviewButton.setVisible(false);
					}
				});
			}
		}
	}

}
