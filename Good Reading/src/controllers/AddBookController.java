package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;
import i_book.Author;
import i_book.Book;
import i_book.Field;
import i_book.Subject;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * Add Book Controller is the controller of the add book page.
 * 
 * @author ofirmoshe
 *
 */
public class AddBookController extends SystemController {

	public Author[] authors; 
	public static Field[] fields;
	public static Subject[][] subjects;
	private int currSubCount;
	public boolean[] checkFields;
	private GridPane subjectGrid = null;
	public boolean[][] checkSubjects;
	public boolean[] checkAuthors;
	private byte[] bimg = null;
	public byte[][] formats=new byte[3][];
	public boolean initOver=false;
	public boolean addBookOver=false;

	@FXML
	public TextField titleField;
	@FXML
	public TextField langField;
	@FXML
	public AnchorPane fieldChecklist;
	@FXML
	public AnchorPane subjectChecklist;
	@FXML
	public AnchorPane authorChecklist;
	@FXML
	public TextField imageField;
	@FXML
	public TextField keywordField;
	@FXML
	public TextArea summaryField;
	@FXML
	public TextArea tableField;
	@FXML
	public TextField priceField;
	@FXML
	public TextField pdfField;
	@FXML
	public TextField docField;
	@FXML
	public TextField fb2Field;
	@FXML
	public TextField newAuthorField;
	/**
	 * Initializing the add book page.
	 * At first, it sends a message to the server, in order to receive the authors, fields and subjects available on database.
	 * After that being done, it sets a default "no-image" image for a book on @param bimg parameter.
	 */
	public void initialize() {
		super.initialize();
		Message msg = new Message("add book", 1);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		subjectChecklist.setPrefHeight(1);
		Image img = new Image(GraphicsImporter.class.getResource("no_image.jpg").toString());
		BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, "jpg", s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bimg = s.toByteArray();
	}
	/** 
	 * This event is being called whenever the 'Upload Image' button is clicked.
	 * It displays an open dialog, allowing the user to choose an image from its PC.
	 * Then, we save the input image at bimg parameter.
	 */
	public void uploadImageOnClick() {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(ClientUI.primaryStage);
			if (selectedFile != null) {
				Image img = new Image(selectedFile.toURI().toString());
				BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
				ByteArrayOutputStream s = new ByteArrayOutputStream();
				ImageIO.write(bImage, "jpg", s);
				bimg = s.toByteArray();
				imageField.setText(selectedFile.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This event is being called whenever the 'Upload' button is pressed.
	 * It displays an open dialog, allowing the user to choose a PDF file from its PC.
	 * Then, we save the input file at formats[0] parameter and update path TextField to the file's path.
	 */
	public void uploadPDFOnClick() {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("E-Book Files", "*.doc", "*.pdf", "*.fb2"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(ClientUI.primaryStage);
			if (selectedFile != null) {
				Path path=selectedFile.toPath();
				formats[0] = Files.readAllBytes(path);
				pdfField.setText(selectedFile.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * This event is being called whenever the 'Upload' button is pressed.
	 * It displays an open dialog, allowing the user to choose a DOC file from its PC.
	 * Then, we save the input file at formats[1] parameter and update path TextField to the file's path.
	 */
	public void uploadDOCOnClick() {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("E-Book Files", "*.doc", "*.pdf", "*.fb2"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(ClientUI.primaryStage);
			if (selectedFile != null) {
				Path path=selectedFile.toPath();
				formats[1] = Files.readAllBytes(path);
				docField.setText(selectedFile.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * This event is being called whenever the 'Upload' button is pressed.
	 * It displays an open dialog, allowing the user to choose a FB2 file from its PC.
	 * Then, we save the input file at formats[2] parameter and update path TextField to the file's path.
	 */
	public void uploadFB2OnClick() {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("E-Book Files", "*.doc", "*.pdf", "*.fb2"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(ClientUI.primaryStage);
			if (selectedFile != null) {
				Path path=selectedFile.toPath();
				formats[2] = Files.readAllBytes(path);
				docField.setText(selectedFile.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * This event is being called whenever the 'Add Book' button is pressed.
	 * The method immediately checks that the required input fields (title, language, fields, author, price) are not empty using the mustFlag boolean parameter.
	 * In case that one or more of the fields are empty, it displays a popup message, alerts the issue. 
	 * Else, it fills up an Object array with the new book data and sends it to the server, requesting to add that new book to the database.
	 */
	
	public void addBookOnClick() {
		addBookOver=false;
		System.out.println("on click");
		Object[] o = new Object[14];
		boolean mustFlag = false;
		if (!titleField.getText().equals(""))
			o[0] = titleField.getText();
		
		else
			mustFlag = true;
		System.out.println("title: "+titleField.getText());
		if (!langField.getText().equals(""))
			o[1] = langField.getText();
		else
			mustFlag = true;
		System.out.println("lang: "+langField.getText());
		int count = 0;
		for (int i = 0; i < checkFields.length; i++)
			if (checkFields[i])
				count++;
		if (count == 0)
			mustFlag = true;
		System.out.println("#fields selected: "+checkFields.length);
		Field[] selectedFields = new Field[count];
		int index = 0;
		for (int i = 0; i < checkFields.length; i++) {
			if (checkFields[i]) {
				selectedFields[index] = fields[i];
				index++;
			}
		}
		o[2] = selectedFields;

		count = 0;
		for (int i = 0; i < checkSubjects.length; i++) {
			for (int j = 0; j < checkSubjects[i].length; j++) {
				if (checkSubjects[i][j])
					count++;
			}
		}
		System.out.println("#subject selected: "+count);
		Subject[] selectedSubjects = new Subject[count];
		index = 0;
		for (int i = 0; i < checkSubjects.length; i++) {
			for (int j = 0; j < checkSubjects[i].length; j++) {
				if (checkSubjects[i][j]) {
					selectedSubjects[index] = subjects[i][j];
					index++;
				}
			}
		}
		o[3] = selectedSubjects;

		count = 0;
		for (int i = 0; i < checkAuthors.length; i++)
			if (checkAuthors[i])
				count++;
		System.out.println("#author selected: "+checkAuthors.length);
		if (count == 0 && newAuthorField.getText().equals(""))
			mustFlag = true;
		System.out.println("new author: "+newAuthorField.getText());
		Author[] selectedAuthors = new Author[count];
		index = 0;
		for (int i = 0; i < checkAuthors.length; i++) {
			if (checkAuthors[i]) {
				selectedAuthors[index] = authors[i];
				index++;
			}
		}
		o[4] = selectedAuthors;
		System.out.println(selectedAuthors.length);
		o[5] = keywordField.getText();
		System.out.println(o[5].toString());
		o[6] = bimg;
		o[7] = summaryField.getText();
		System.out.println(o[7].toString());
		o[8] = tableField.getText();
		System.out.println(o[8].toString());
		boolean InvalidPrice = false;
		if (!priceField.getText().equals("")) {
			try {
				o[9] = Float.parseFloat(priceField.getText());
				if ((float) o[9] < 0) {
					InvalidPrice = true;
				}
			} catch (Exception e) {
				InvalidPrice = true;
			}
		} else
			mustFlag = true;
		System.out.println("price: "+priceField.getText());
		if (InvalidPrice) {
			addBookOver=true;
			/*Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Add Book Failed");
			alert.setHeaderText("Invalid Price");
			alert.setContentText("Please check you entered a valid price and try again.");
			alert.showAndWait();*/
			return;
		}
		System.out.println("format " +formats[0].length);
		if (formats[0]==null && formats[1]==null && formats[2]==null)
			mustFlag = true;
		else {
			o[10] = formats[0];
			System.out.println("format " +formats[0].length);
			o[11] = formats[1];
			o[12] = formats[2];
			o[13]=newAuthorField.getText();
			System.out.println("new author to object[]: "+newAuthorField.getText());
		}
		if (mustFlag) {
			System.out.println("error");
			addBookOver=true;
			/*Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Add Book Failed");
			alert.setHeaderText("Can not add book");
			alert.setContentText("One or more required fields are empty.");
			alert.showAndWait();*/
			return;
		}
		System.out.println("all cool");
		Message msg = new Message("add book", 2, o);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("sent");
	}

	/**
	 * This method implements AbstractController's method. It handles message
	 * from server, and displays the book's data.
	 * 
	 * @param msg
	 *            case 1: 
	 *              The message is an object array. index 0- contains a
	 *            	fields array index 1- contains a subjects array index 2- contains
	 *            	an authors array.
	 *            case 2:
	 *            	The message is a string, indicates a success ("s") or failure ("f") of adding book method.
	 *            	It shows an appropriate popup message for each of these options.
	 *            	 */
	@Override
	public void handleMessage(Message msg) {
		System.out.println("handle");
		super.handleMessage(msg);
		switch (msg.getFunc()) {
		case 1:
			Object[] ob = (Object[]) msg.getMsg();
			fields = (Field[]) ob[0];
			checkFields = new boolean[fields.length];
			subjects = (Subject[][]) ob[1];
			checkSubjects = new boolean[fields.length][];
			for (int i = 0; i < fields.length; i++) {
				checkSubjects[i] = new boolean[subjects[i].length];
			}
			authors = (Author[]) ob[2];
			checkAuthors = new boolean[authors.length];
			initOver=true;
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setCheckboxes(fields);
					setCheckboxes(authors);
				}
			});
			break;
		case 2:
			if(msg.getMsg().equals("s")){
				addBookOver=true;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Add Book Succeed");
						alert.setContentText("New book was added to Good Reading");
						alert.showAndWait();
						logoOnClick();
					}
				});
			}
		}
	}
	/**
	 * This method initializes the fields and authors checkboxes.
	 * @param o
	 * 	Array of fields or of authors.
	 */
	public void setCheckboxes(Object[] o) {
		for (int i = 0; i < o.length; i++) {
			CheckBox cb = new CheckBox();
			Field f;
			Author a;
			if (o[i] instanceof Field) {
				f = (Field) o[i];
				cb.setText(f.getField());
				cb.setUserData(i);
				cb.setLayoutY(i * 20);
				cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
					public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
						if (new_val) {
							int sin = (int) cb.getUserData();
							checkFields[sin] = true;
							setSubjects();
						} else {
							int sin = (int) cb.getUserData();
							checkFields[sin] = false;
							for (int i = 0; i < checkSubjects[sin].length; i++) {
								checkSubjects[sin][i] = false;
							}
							setSubjects();
						}
					}

				});
				fieldChecklist.getChildren().add(cb);
				fieldChecklist.setPrefHeight(21 * fields.length);
			}

			else if (o[i] instanceof Author) {
				a = (Author) o[i];
				cb.setText(a.getName());
				cb.setUserData(i);
				cb.setLayoutY(i * 20);
				cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
					public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
						if (new_val) {
							int sin = (int) cb.getUserData();
							checkAuthors[sin] = true;
						} else {
							int sin = (int) cb.getUserData();
							checkAuthors[sin] = false;
						}
					}

				});
				authorChecklist.getChildren().add(cb);
				authorChecklist.setPrefHeight(21 * authors.length);
			}
		}
	}
	/**
	 * This method initializes the subjects checkbox, based on the checked fields.
	 */
	public void setSubjects() {
		if (subjectGrid != null)
			subjectChecklist.getChildren().remove(subjectGrid);
		subjectGrid = new GridPane();
		for (int i = 0; i < checkFields.length; i++) {
			if (checkFields[i]) {
				Subject[] s = subjects[i];
				for (int j = 0; j < s.length; j++, currSubCount++) {
					CheckBox cb = new CheckBox();
					cb.setText(s[j].getSub());
					if (checkSubjects[i][j])
						cb.setSelected(true);
					int[] in = { i, j };
					cb.setUserData(in);
					cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
						public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
							if (new_val) {
								int[] in = (int[]) cb.getUserData();
								checkSubjects[in[0]][in[1]] = true;
							} else {
								int[] in = (int[]) cb.getUserData();
								checkSubjects[in[0]][in[1]] = false;
							}
						}
					});
					subjectGrid.add(cb, 0, currSubCount);
				}
			}
		}
		subjectChecklist.getChildren().add(subjectGrid);
		subjectChecklist.setPrefHeight(18 * currSubCount);
		currSubCount = 0;
	}

}
