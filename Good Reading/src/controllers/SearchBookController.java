package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Author;
import i_book.Book;
import i_book.Employee;
import i_book.Field;
import i_book.Subject;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * Search book controller is the controller of the search book page.
 * 
 * @author guyzi
 *
 */
public class SearchBookController extends SystemController {

	private String[] query = new String[9];
	public Book[] books;
	private GridPane grid = null;
	private Author[][] book_authors;
	private Field[][] book_fields;
	private Subject[][] book_subjects;
	public static Field[] fields;
	public static Subject[][] subjects;
	private String isManager = "no";
	public static String what;
	private int[] ranks;
	private int[][] fieldRanks;
	public boolean searchOver=false;

	@FXML
	private TextField titleField;
	@FXML
	private TextField langField;
	@FXML
	private TextField authorField;
	@FXML
	private ChoiceBox<String> optionBox;
	@FXML
	private AnchorPane scrollAnchor;
	@FXML
	private TextField keywordField;
	@FXML
	private ChoiceBox<String> fieldBox;
	@FXML
	private ChoiceBox<String> subjectBox;

	/**
	 * This method initializes the page and sends a message to the server
	 * to get all the fields, subjects and ranks.
	 */
	public void initialize() {
		super.initialize();
		Message msg = new Message("search book", 2);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ClientUI.user instanceof Employee){
			menuAnchor.setVisible(false);
		}
		scrollAnchor.setPrefHeight(200);
		optionBox.setItems(FXCollections.observableArrayList("AND", "OR"));
		optionBox.getSelectionModel().selectFirst();
		fieldBox.setItems(FXCollections.observableArrayList("None"));
		fieldBox.getSelectionModel().selectFirst();
		subjectBox.setItems(FXCollections.observableArrayList("None"));
		subjectBox.getSelectionModel().selectFirst();
	}

	/**
	 * This method is called when the search button is clicked, or when Enter
	 * key is pressed. The method collects all the input entered by the user,
	 * composing a query (string array) and sends it to the server.
	 */
	public void searchOnEnterPressed() {
		query[0] = optionBox.getSelectionModel().getSelectedItem();
		query[2] = titleField.getText();
		query[3] = langField.getText();
		query[4] = authorField.getText();
		query[5] = keywordField.getText();
		if(fieldBox.getSelectionModel().getSelectedIndex()!=0)
			query[6] = "" + fields[fieldBox.getSelectionModel().getSelectedIndex()-1].getID();
		else
			query[6]=""+0;
		query[7] = subjectBox.getSelectionModel().getSelectedItem();
		if (ClientUI.user instanceof Employee)
			isManager = "yes";
		query[8] = isManager;
		if(query[2].equals("")&& query[3].equals("")&&query[4].equals("")&&query[5].equals("")
				&&query[6].equals("0")&&query[7].equals("None")){
			noResults();
			return;
		}
		Message msg = new Message("search book", 1, query);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchBook(String title,String lang ,String author ,String kw, String field, String subject){
		/*titleField.setText(title);
		langField.setText(lang);
		authorField.setText(author);
		keywordField.setText(kw);
		if(!field.equals(""))
			fieldBox.getSelectionModel().select(field);
		if(!subject.equals(""))
			subjectBox.getSelectionModel().select(subject);
		searchOnEnterPressed();*/
		try {
			new Client("localhost", 5555);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		query[0] = "AND";
		query[2]=title;
		query[3]=lang;
		query[4]=author;
		query[5]=kw;
		query[6]="0";
		query[7]="None";
		query[8] = "no";
		AbstractController.instance=this;
		Message msg = new Message("search book", 1, query);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method implements the abstract controller method.
	 * 
	 * @param msg
	 *            case 1: The message is an object array. index 0 - Book array.
	 *            index 1 - Author matrix, each index is an array of
	 *            book_authors of the matching book in the book array. index 2 -
	 *            book_fields matrix. index 3 - book_subjects matrix. If the book
	 *            array is empty, no result message is displayed, else the book
	 *            grid is set according to this data.
	 *            case 2: The message is an object array. index 0 - fields array.
	 *            index 1 - subject array. index 2 - ranks array (absolute ranks organized
	 *            by book id). index 3 - ranks in fields, for each field there is an array
	 *            of it's books ranks organized by book id.
	 */
	@Override
	public void handleMessage(Message msg) { super.handleMessage(msg);
		switch (msg.getFunc()) {
		case 1:
			Object[] o = (Object[]) msg.getMsg();
			books = (Book[]) o[0];
			System.out.println("over");
			searchOver=true;
			book_authors = (Author[][]) o[1];
			book_fields = (Field[][]) o[2];
			book_subjects = (Subject[][]) o[3];
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					if (grid != null)
						scrollAnchor.getChildren().remove(grid);
					if (books.length == 0) {
						noResults();
						return;
					}
					setBookGrid();
				}
			});
			break;

		case 2:
			Object[] ob = (Object[]) msg.getMsg();
			fields = (Field[]) ob[0];
			subjects = (Subject[][]) ob[1];
			ranks = (int[])ob[2];
			fieldRanks = (int[][])ob[3];
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setFieldBox();
				}
			});
		}
	}

	public void noResults(){
		StackPane sp = new StackPane();
		sp.setPrefHeight(200);
		sp.setPrefWidth(900);
		Label noRes = new Label("Sorry, no results.");
		noRes.setTextFill(Color.DARKSLATEGRAY);
		noRes.setFont(Font.font("System", 20));
		sp.getChildren().add(noRes);
		StackPane.setAlignment(noRes, Pos.CENTER);
		scrollAnchor.getChildren().add(sp);
		scrollAnchor.setPrefHeight(200);
	}
	
	/**
	 * This method sets the field choice box with all the fields in DB, and an
	 * Change Listener that sets the subject choice box with the subject
	 * of the chosen field.
	 */
	public void setFieldBox() {
		ObservableList<String> olf = FXCollections.observableArrayList();
		olf.add("None");
		for (int i = 0; i < fields.length; i++) {
			olf.add(fields[i].getField());
		}
		fieldBox.setItems(olf);
		fieldBox.getSelectionModel().selectFirst();
		fieldBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				ObservableList<String> ols = FXCollections.observableArrayList();
				ols.add("None");
				if (new_value.intValue() > 0) {
					for (int i = 0; i < subjects[new_value.intValue() - 1].length; i++) {
						ols.add(subjects[new_value.intValue() - 1][i].getSub());
					}
					subjectBox.setItems(ols);
					subjectBox.getSelectionModel().selectFirst();
				}
			}
		});
	}

	/**
	 * This method sets the book grid with books, and event handlers for each
	 * book according to a string variable that indicates for what use the user
	 * searches books. For example if what="book report" each result would display the book
	 * ranks and a button that would redirect to the book report page.
	 *  The book grid is added to the scroll anchor.
	 */
	public void setBookGrid() {
		try {
			grid = new GridPane();
			for (int y = 0; y < books.length; y++) {
				AnchorPane ap = new AnchorPane();
				ap.setUserData(y);
				ap.setPrefHeight(170);
				ap.setPrefWidth(900);
				Rectangle rect = new Rectangle();
				rect.setHeight(170);
				rect.setWidth(900);
				rect.setFill(Color.WHITE);
				rect.setStrokeWidth(1);
				rect.setStroke(Color.BURLYWOOD);
				ap.getChildren().add(rect);
				ByteArrayInputStream in = new ByteArrayInputStream(books[y].getImage());
				BufferedImage read;
				read = ImageIO.read(in);
				Image img = SwingFXUtils.toFXImage(read, null);
				ImageView iv = new ImageView();
				iv.setImage(img);
				iv.setFitWidth(100);
				iv.setFitHeight(150);
				InnerShadow innerShadow = new InnerShadow();
				innerShadow.setColor(Color.BLACK);
				iv.setEffect(innerShadow);
				ap.getChildren().add(iv);
				iv.setLayoutX(10);
				iv.setLayoutY(10);
				Label title = new Label(books[y].getTitle());
				title.setTextFill(Color.BLACK);
				title.setFont(Font.font("System", FontWeight.BOLD, 18));
				ap.getChildren().add(title);
				title.setLayoutX(120);
				title.setLayoutY(10);
				String s = "by " + book_authors[y][0].getName();
				for (int i = 1; i < book_authors[y].length; i++)
					s = s + ", " + book_authors[y][i].getName();
				Label book_authors = new Label(s);
				book_authors.setTextFill(Color.BLACK);
				book_authors.setFont(Font.font("System", 13));
				ap.getChildren().add(book_authors);
				book_authors.setLayoutX(120);
				book_authors.setLayoutY(33);
				s = book_fields[y][0].getField();
				for (int i = 1; i < book_fields[y].length; i++)
					s = s + ", " + book_fields[y][i].getField();
				Label bf = new Label(s);
				bf.setTextFill(Color.GREY);
				bf.setFont(Font.font("System", 12));
				ap.getChildren().add(bf);
				bf.setLayoutX(120);
				bf.setLayoutY(55);
				if (book_subjects[y].length != 0) {
					s = book_subjects[y][0].getSub();
					for (int i = 1; i < book_subjects[y].length; i++)
						s = s + ", " + book_subjects[y][i].getSub();
					Label book_subjects = new Label(s);
					book_subjects.setTextFill(Color.GREY);
					book_subjects.setFont(Font.font("System", 12));
					ap.getChildren().add(book_subjects);
					book_subjects.setLayoutX(120);
					book_subjects.setLayoutY(72);
				}
				Label sum = new Label(books[y].getSummary());
				sum.setPrefSize(600, 40);
				sum.setLayoutX(120);
				sum.setLayoutY(90);
				sum.setWrapText(true);
				ap.getChildren().add(sum);
				if (what.equals("book report")) {
					Button searchButton = new Button("Get Report");
					searchButton.setLayoutX(740);
					searchButton.setLayoutY(20);
					searchButton.setPrefWidth(120);
					searchButton.setTextAlignment(TextAlignment.CENTER);
					searchButton.setUserData(y);
					searchButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
						HistogramReportController.book=books[(int)searchButton.getUserData()];
						ClientUI.setScene("HistogramReportGUI.fxml");
					}
						});
					ap.getChildren().add(searchButton);
					Label pop = new Label("Popularity Ranks:");
					pop.setFont(Font.font("System",FontWeight.BOLD, 14));
					pop.setLayoutY(140);
					pop.setLayoutX(120);
					pop.setTextFill(Color.CRIMSON);
					ap.getChildren().add(pop);
					String r = "Absolute- #"+ranks[books[y].getID()]+" of "+ranks[0];
					for (int i = 0; i < book_fields[y].length; i++)
						r = r + ", In " + book_fields[y][i].getField()+"- #"
								+fieldRanks[book_fields[y][i].getID()-1][books[y].getID()]
										+" of "+fieldRanks[book_fields[y][i].getID()-1][0];
					Label rankLabel = new Label(r);
					rankLabel.setFont(Font.font("System", 12));
					rankLabel.setLayoutY(142);
					rankLabel.setLayoutX(242);
					ap.getChildren().add(rankLabel);
				}
				else {
					ap.setCursor(Cursor.HAND);
					ap.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							int y = (int) ap.getUserData();
							if (what.equals("edit book")) {
								EditBookController.book_id = books[y].getID();
								ClientUI.setScene("EditBookGUI.fxml");
							} else {
								BookPageController.book = books[y];
								ClientUI.setScene("BookPageGUI.fxml");
							}
							event.consume();
						}
					});
				}
				grid.add(ap, 0, y);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scrollAnchor.setPrefHeight(171 * books.length);
		scrollAnchor.getChildren().add(grid);
	}

}
