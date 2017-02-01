package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.orm.PersistentException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Book page controller is the controller of all the book pages. The controller
 * matches the page to display the data of the book to be shown (according to a
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
	private String canDownload;

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
	Label langLabel;
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
	@FXML
	AnchorPane addReviewAnchor;

	/**
	 * This method initializes the controller, sends a message to server to 
	 * get the book data from DB, and also to get the user's permissions.
	 * Also the method displays all the book's 'dry' data (like title, image, price, etc.)
	 */
	public void initialize() {
		super.initialize();
		try {
			mainAnchor.setCursor(Cursor.WAIT);
			Object[] o = new Object[2];
			o[0] = book.getID();
			o[1] = ClientUI.user.getID();
			Message msg = new Message("book page", 1, o);
			Client.instance.sendToServer(msg);
			currBook = book;
			Image img;
			if (book.getImage() != null) {
				ByteArrayInputStream in = new ByteArrayInputStream(book.getImage());
				BufferedImage read;
				read = ImageIO.read(in);
				img = SwingFXUtils.toFXImage(read, null);
			} else
				img = new Image(GraphicsImporter.class.getResource("loading_book.jpg").toString());
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
			langLabel.setText(book.getLanguage());
			summaryText.setText(book.getSummary());
			tableText.setText(book.getTable_of_contents());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is called when "Get this book" button is clicked. 
	 * The method executes different code for different cases.
	 * if the user owns the book, the method opens download stream based on
	 * the chosen format.  else, the method redirect the user to the
	 * book payment request page. If the user downloads the book for the first time,
	 * the method notify the server to update the user-book status.
	 */
	public void getBookOnClick() {
		// if user owns the book
		if (formatAnchor.isVisible()) {
			String f = (String) formatBox.getSelectionModel().getSelectedItem();
			FileChooser fileChooser = new FileChooser();
			File file = null;
			switch (f) {
			case "pdf":
				fileChooser.setTitle("Save Book");
				fileChooser.setInitialFileName(currBook.getTitle() + " downloaded by GoodReading");
			//	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("GoodReading Book", "*.pdf"));
				file = fileChooser.showSaveDialog(ClientUI.primaryStage);
				if (file != null) {
					try {
						OutputStream out = new FileOutputStream(file);
						out.write(currBook.getPdf());
						out.close();
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					}
				}

				break;
			case "doc":
				fileChooser.setTitle("Save Book");
				fileChooser.setInitialFileName(currBook.getTitle() + " downloaded by GoodReading");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("GoodReading Book", "*.doc"));
				file = fileChooser.showSaveDialog(ClientUI.primaryStage);
				if (file != null) {
					try {
						OutputStream out = new FileOutputStream(file);
						out.write(currBook.getDoc());
						out.close();
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					}
				}
				break;
			case "fb2":
				fileChooser.setTitle("Save Book");
				fileChooser.setInitialFileName(currBook.getTitle() + " downloaded by GoodReading");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("GoodReading Book", "*.fb2"));
				file = fileChooser.showSaveDialog(ClientUI.primaryStage);
				if (file != null) {
					try {
						OutputStream out = new FileOutputStream(file);
						out.write(currBook.getFb2());
						out.close();
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					}
				}
				break;
			}
			// if it's a first download, change status in user_book table.
			Object[] m = new Object[2];
			m[0] = currBook.getID();
			m[1] = ClientUI.user.getID();
			Message msg = new Message("book page", 3, m);
			try {
				Client.instance.sendToServer(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;

		}
		// user does not own the book -> move to payment request
		BookPaymentController.book = currBook;
		BookPaymentController.authors = authors;
		ClientUI.setScene("BookPaymentGUI.fxml");
	}

	/**
	 * This method is called when the "send" button in the add review section is clicked.
	 * The method sends to the server the review, the user who wrote it and the book it 
	 * was written about.
	 */
	public void addReview() {
		if (!addReviewText.getText().equals("")) {
			Object[] o = new Object[3];
			o[0] = currBook.getID();
			o[1] = ClientUI.user.getID();
			o[2] = addReviewText.getText();
			Message msg = new Message("book page", 10, o);
			try {
				Client.instance.sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method sets the review panel with all this book's reviews. 
	 */
	public void setReviewGrid() {
		reviewGrid = new GridPane();
		double gridHeight = 0;
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
			Text rev = new Text(reviews[y].getText());
			rev.setFont(Font.font("System", 12));
			TextFlow tf = new TextFlow(rev);
			tf.setLayoutX(10);
			tf.setLayoutY(30);
			tf.setPrefWidth(540);
			ap.getChildren().add(tf);
			reviewGrid.add(ap, 0, y);
			double revHeight = rev.getText().length() / 4.5 + 50;
			if (tf.getBoundsInLocal().getHeight() + 50 > revHeight)
				revHeight = tf.getBoundsInLocal().getHeight() * 1.15 + 50;
			rect.setHeight(revHeight);
			ap.setPrefHeight(revHeight);
			gridHeight = gridHeight + revHeight;
		}
		reviewAnchor.setPrefHeight(gridHeight);
		reviewAnchor.getChildren().add(reviewGrid);
	}

	/**
	 * This method implements AbstractController's method. It handles message
	 * from server, and displays the book's data.
	 * 
	 * @param msg
	 *            case 1: the message is an object array. index 0- contains an
	 *            Author array index 1- contains a Field array index 2- contains
	 *            a Subject array. index 3 - review array. index 4 - string array, contains the 
	 *            first names and last names of the users that wrote a review about the book.
	 *            index 5 - string, the user's book-review status. index 6- string,
	 *            tells if the user is permitted to download this book.
	 *            case 2: The message is string, tells if the review was added successfully.
	 *            if it was the method displays a "thank you for the review" label to the user.
	 */
	@Override
	public void handleMessage(Message msg) {
		if (msg.getCont().equals("book page")) {
			switch (msg.getFunc()) {
			case 1:
				Object[] m = (Object[]) msg.getMsg();
				authors = (Author[]) m[0];
				fields = (Field[]) m[1];
				subjects = (Subject[]) m[2];
				reviews = (Review[]) m[3];
				usernames = (String[]) m[4];
				canReview = (String) m[5];
				canDownload = (String) m[6];
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						mainAnchor.setCursor(Cursor.DEFAULT);
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
						if (!canReview.equals("yes")) {
							scrollAnchor.setPrefHeight(438);
							addReviewAnchor.setVisible(false);
							if (canReview.equals("waiting")) {
								reviewSentLabel.setText("Your review is waiting to be approved.");
								reviewSentLabel.setVisible(true);
							}
						}
						else{
							addReviewAnchor.setVisible(true);
						}
						if (reviews.length != 0)
							setReviewGrid();
						if (canDownload.equals("yes")) {
							priceAnchor.setVisible(false);
							ObservableList<String> formats = FXCollections.observableArrayList();
							if (currBook.getPdf() != null)
								formats.add("pdf");
							if (currBook.getDoc() != null)
								formats.add("doc");
							if (currBook.getFb2() != null)
								formats.add("fb2");
							formatBox.setItems(formats);
							formatBox.getSelectionModel().selectFirst();
							formatAnchor.setVisible(true);
						}else{
							priceAnchor.setVisible(true);
						}
					}

				});
				break;

			case 10:
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
}
