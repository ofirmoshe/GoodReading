package controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.controlsfx.control.CheckComboBox;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;
import i_book.Author;
import i_book.Book;
import i_book.Employee;
import i_book.Field;
import i_book.Keyword;
import i_book.Subject;

/**
 * Edit book controller is the controller of the edit book page, where librarians
 * can edit all the book's data, and library manager can hide the book.
 * @author guyzi
 *
 */
public class EditBookController extends SystemController {

	public static int book_id;
	private Book book;
	private byte[] bimg;
	private byte[] pdfData;
	private String pdfPath;
	private byte[] docData;
	private String docPath;
	private byte[] fb2Data;
	private String fb2Path;
	private Field[] fields;
	private boolean[] checkFields;
	private Subject[][] subjects;
	private boolean[][] checkSubjects;
	private Author[] authors;
	private boolean[] checkAuthors;
	public boolean removeOver = false;
	public boolean removeSuccess = false;
	public boolean removeFailed = false;
	@FXML
	private TextField titleField;
	@FXML
	private TextField priceField;
	@FXML
	private TextArea summaryText;
	@FXML
	private TextArea tableText;
	@FXML
	private ComboBox authorComboBox;
	@FXML
	private CheckComboBox<String> authorBox;
	@FXML
	private ComboBox fieldComboBox;
	@FXML
	private CheckComboBox<String> fieldBox;
	@FXML
	private ComboBox subjectComboBox;
	@FXML
	private CheckComboBox<String> subjectBox;
	@FXML
	private ChoiceBox<String> formatBox;
	@FXML
	private TextField formatField;
	@FXML
	private ImageView bookImage;
	@FXML
	private TextField langField;
	@FXML
	private TextField keywordField;
	@FXML
	private AnchorPane hideButton;
	@FXML
	private Label hideLabel;

	/**
	 * This method initializes the page, and sends a message to the server to get all the 
	 * book's data. If the the employee is library manager it would display the 'hide book'
	 * button.
	 * 
	 */
	public void initialize() {
		super.initialize();
		formatField.setEditable(false);
		formatField.deselect();
		Message msg = new Message("edit book", 1, book_id);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Employee emp = (Employee) ClientUI.user;
		if (emp.getPosition().equals("Library Manager"))
			hideButton.setVisible(true);
	}

	/**
	 * This method is called when the change image button is click.
	 * The method opens a file chooser window for the user to choose an image to
	 * replace the current image of the book.
	 */
	public void changeImageOnClick() {
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
				bookImage.setImage(img);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is called when the upload button near the format field is pressed.
	 * The method opens a file chooser window for the user to choose a file to upload
	 * according to the format he has chosen in the format choice box.
	 */
	public void uploadFormatOnClick() {
		Path path;
		String f = formatBox.getSelectionModel().getSelectedItem();
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("E-Book Files", "*.doc", "*.pdf", "*.fb2"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(ClientUI.primaryStage);
			if (selectedFile != null) {
				path = selectedFile.toPath();
				switch (f) {
				case "pdf":
					pdfData = Files.readAllBytes(path);
					pdfPath = path.toString();
					break;
				case "doc":
					docData = Files.readAllBytes(path);
					docPath = path.toString();
					break;
				case "fb2":
					fb2Data = Files.readAllBytes(path);
					fb2Path = path.toString();
					break;
				}
				formatField.setPromptText(path.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is called when the remove button near the format field is pressed.
	 * The method removes the chosen format according to the selection in the format choice box.
	 */
	public void removeFormatOnClick() {
		String f = formatBox.getSelectionModel().getSelectedItem();
		switch (f) {
		case "pdf":
			pdfData = null;
			pdfPath="pdf format doesn't exist";
			formatField.setPromptText(pdfPath);
			break;
		case "doc":
			docData = null;
			docPath="doc format doesn't exist";
			formatField.setPromptText(docPath);
			break;
		case "fb2":
			fb2Data = null;
			fb2Path="fb2 format doesn't exist";
			formatField.setPromptText(fb2Path);
			break;
		}
	}

	/**
	 * This method is called when the "Remove This Book" button is pressed.
	 * The method displays a confirmation message to the user and according to his choice
	 * stops or proceeds to notify the server to remove the specified book. 
	 */
	public void removeBookOnClick() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Edit Book");
		alert.setContentText("Are you sure you want to remove this book?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... user chose OK
			removeBook(book_id);
		} else {
			// ... user chose CANCEL or closed the dialog
			return;
		}
	}
	
	/**
	 * 
	 * test function
	 */
	public void removeBook(int id){
		try{
			Message msg = new Message("edit book", 3, id);
			Client.instance.sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * This method is called when the "hide book" button is pressed.
	 * The method notify the server to hide or to show the book (by changing is status)
	 * according to the book's current status.
	 */
	public void hideBookOnClick() {
		Message msg = new Message("edit book", 4, book_id);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (hideLabel.getText().equals("Hide Book"))
			hideLabel.setText("Show Book");
		else
			hideLabel.setText("Hide Book");
	}

	/**
	 * This method is called when the "Save All Changes" button is pressed.
	 * The method collects all the input the user entered, checks validation of 
	 * the input, and if valid sends to the server all the new data to replace the old
	 * data with.
	 */
	public void editBookOnClick() {
		Object[] o = new Object[14];
		boolean mustFlag = false;
		o[13] = book_id;
		if (!titleField.getText().equals(""))
			o[0] = titleField.getText();
		else
			mustFlag = true;
		if (!langField.getText().equals(""))
			o[1] = langField.getText();
		else
			mustFlag = true;
		ObservableList<String> sf = fieldBox.getCheckModel().getCheckedItems();
		Field[] selectedFields = new Field[sf.size()];
		if (sf.size() == 0)
			mustFlag = true;
		else {
			int index = 0;
			for (int i = 0; i < fields.length; i++) {
				if (sf.contains(fields[i].getField())) {
					selectedFields[index] = fields[i];
					index++;
				}
			}
		}
		o[2] = selectedFields;

		ObservableList<String> ss = subjectBox.getCheckModel().getCheckedItems();
		Subject[] selectedSubjects = new Subject[ss.size()];
		int index = 0;
		for (int i = 0; i < subjects.length; i++) {
			for (int j = 0; j < subjects[i].length; j++) {
				if (ss.contains(subjects[i][j].getSub())) {
					selectedSubjects[index] = subjects[i][j];
					index++;
				}
			}
		}

		o[3] = selectedSubjects;
		ObservableList<String> sa = authorBox.getCheckModel().getCheckedItems();
		Author[] selectedAuthors = new Author[sa.size()];
		if (sa.size() == 0)
			mustFlag = true;
		else {
			index = 0;
			for (int i = 0; i < authors.length; i++) {
				if (sa.contains(authors[i].getName())) {
					selectedAuthors[index] = authors[i];
					index++;
				}
			}
		}
		o[4] = selectedAuthors;
		o[5] = keywordField.getText();
		o[6] = bimg;
		o[7] = summaryText.getText();
		o[8] = tableText.getText();
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
		if (InvalidPrice) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Edit Book Failed");
			alert.setHeaderText("Invalid Price");
			alert.setContentText("Please check you entered a valid price and try again.");
			alert.showAndWait();
			return;
		}
		if (pdfData == null && docData == null && fb2Data == null)
			mustFlag = true;
		else {
			o[10] = pdfData;
			o[11] = docData;
			o[12] = fb2Data;
		}
		if (mustFlag) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Edit Book Failed");
			alert.setHeaderText("Can not edit book");
			alert.setContentText("One or more required fields are empty.");
			alert.showAndWait();
			return;
		}
		Message msg = new Message("edit book", 2, o);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * This method implements AbstractController's method. It handles messages
	 * from server.
	 * 
	 * @param msg
	 *            case 1: 
	 *              The message is an object array. 
	 *              index 0- field array.
	 *              index 1- subject matrix (organized by fields).
	 *              index 2- author array.
	 *              index 3- contains the book's field array.
	 *              index 4 -  contains the book's subject matrix (organized by fields)
	 *           	index 5 - the book's author array.
	 *           	index 6- the book (contains all the book's 'dry' data).
	 *           	The method displays all this data to the user.
	 *           case 2:
	 *           	The message is a string. if it's "s" the method displays an 
	 *           	"edit successful" message to the user, else it displays an
	 *           	"edit failed"  message to the user.
	 *           case 3:
	 *           	The message is a string. if it's "s" the method displays an 
	 *           	"remove successful" message to the user, else it displays an
	 *           	"remove failed"  message to the user.
	 *           
	 * 
	 */
	@Override
	public void handleMessage(Message msg) { super.handleMessage(msg);
		switch (msg.getFunc()) {
		case 1:
			Object[] ob = (Object[]) msg.getMsg();
			book = (Book) ob[6];
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					titleField.setText(book.getTitle());
					langField.setText(book.getLanguage());
					if (book.getStatus().equals("hidden"))
						hideLabel.setText("Show Book");
					Keyword[] kw = (Keyword[]) ob[7];
					String s = "";
					if (kw.length != 0)
						s = kw[0].getWord();
					for (int i = 1; i < kw.length; i++)
						s = s + ", " + kw[i].getWord();
					keywordField.setText(s);
					Image img;
					if (book.getImage() != null) {
						bimg = book.getImage();
						ByteArrayInputStream in = new ByteArrayInputStream(book.getImage());
						BufferedImage read = null;
						try {
							read = ImageIO.read(in);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						img = SwingFXUtils.toFXImage(read, null);
					} else
						img = new Image(GraphicsImporter.class.getResource("loading_book.jpg").toString());
					bookImage.setImage(img);
					bookImage.setFitWidth(180);
					bookImage.setFitHeight(270);
					float f = book.getPrice();
					if (f == (long) f)
						s = String.format("%d", (long) f);
					else
						s = String.format("%s", f);
					priceField.setText(s);
					summaryText.setText(book.getSummary());
					tableText.setText(book.getTable_of_contents());
					ObservableList<String> formats = FXCollections.observableArrayList();
					formats.add("pdf");
					formats.add("doc");
					formats.add("fb2");
					formatBox.setItems(formats);
					formatBox.getSelectionModel().selectFirst();
					if (book.getPdf() != null) {
						pdfData = book.getPdf();
						pdfPath = "pdf format exists";
						formatField.setPromptText(pdfPath);
					} else {
						pdfPath = "pdf format doesn't exist";
						formatField.setPromptText(pdfPath);
					}
					if (book.getDoc() != null) {
						docData = book.getDoc();
						docPath = "doc format exists";
					} else
						docPath = "doc format doesn't exist";
					if (book.getFb2() != null) {
						fb2Data = book.getFb2();
						fb2Path = "fb2 format exists";
					} else
						fb2Path = "fb2 format doesn't exist";

					formatBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
						public void changed(ObservableValue ov, Number value, Number new_value) {
							switch (new_value.intValue()) {
							case 0:
								formatField.setPromptText(pdfPath);
								break;
							case 1:
								formatField.setPromptText(docPath);
								break;
							case 2:
								formatField.setPromptText(fb2Path);
								break;
							}
						}
					});
					fields = (Field[]) ob[0];
					checkFields = new boolean[fields.length];
					subjects = (Subject[][]) ob[1];
					checkSubjects = new boolean[fields.length][];
					for (int i = 0; i < fields.length; i++) {
						checkSubjects[i] = new boolean[subjects[i].length];
					}
					authors = (Author[]) ob[2];
					checkAuthors = new boolean[authors.length];
					Field[] bf = (Field[]) ob[3];
					for (int i = 0; i < bf.length; i++) {
						for (int j = 0; j < fields.length; j++)
							if (bf[i].equals(fields[j]))
								checkFields[j] = true;
					}
					Subject[] bs = (Subject[]) ob[4];
					for (int i = 0; i < bs.length; i++) {
						for (int j = 0; j < subjects.length; j++)
							for (int k = 0; k < subjects[j].length; k++)
								if (bs[i].equals(subjects[j][k]))
									checkSubjects[j][k] = true;
					}
					Author[] ba = (Author[]) ob[5];
					for (int i = 0; i < ba.length; i++) {
						for (int j = 0; j < authors.length; j++)
							if (ba[i].equals(authors[j]))
								checkAuthors[j] = true;
					}
					setFieldBox();
					setAuthorBox();
					setSubjectBox();
				}
			});
			break;
		case 2:
			if (msg.getMsg().equals("s")) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Edit Book Succeed");
						alert.setContentText("The book was edited successfully.");
						alert.showAndWait();
					}
				});
				ClientUI.setScene("LibrarianHomepageGUI.fxml");
			} else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Edit Book Failed");
						alert.setContentText("Please try again.");
						alert.showAndWait();
					}
				});

			}
			break;
		case 3:
			removeOver=true;
			if (msg.getMsg().equals("s")) {
				removeSuccess=true;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Edit Book Succeed");
						alert.setContentText("The book was removed successfully.");
						alert.showAndWait();
						ClientUI.setScene("LibrarianHomepageGUI.fxml");
					}

				});
			} else {
				removeFailed=true;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Edit Book Failed");
						alert.setContentText("Please try again.");
						alert.showAndWait();
					}

				});
			}
			break;
		}
	}
	/**
	 * This method sets the field check combo box with all the fields in DB, 
	 * and checks the book's fields.
	 */
	public void setFieldBox() {
		ObservableList<String> olf = FXCollections.observableArrayList();
		for (int i = 0; i < fields.length; i++) {
			olf.add(fields[i].getField());
		}
		fieldBox = new CheckComboBox<String>(olf);
		for (int i = 0; i < fields.length; i++) {
			if (checkFields[i])
				fieldBox.getCheckModel().check(i);
		}
		fieldBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends String> change) {
				setSubjectBox();
			}
		});
		fieldBox.setPrefSize(fieldComboBox.getPrefWidth(), fieldComboBox.getPrefHeight());
		fieldBox.setLayoutX(fieldComboBox.getLayoutX());
		fieldBox.setLayoutY(fieldComboBox.getLayoutY());
		fieldComboBox.setVisible(false);
		scrollAnchor.getChildren().add(fieldBox);
	}

	/**
	 * This method sets the author check combo box with all the authors in DB, 
	 * and checks the book's authors. 
	 */
	public void setAuthorBox() {
		ObservableList<String> ola = FXCollections.observableArrayList();
		for (int i = 0; i < authors.length; i++) {
			ola.add(authors[i].getName());
		}
		authorBox = new CheckComboBox<String>(ola);
		for (int i = 0; i < authors.length; i++) {
			if (checkAuthors[i])
				authorBox.getCheckModel().check(i);
		}
		authorBox.setPrefSize(authorComboBox.getPrefWidth(), authorComboBox.getPrefHeight());
		authorBox.setLayoutX(authorComboBox.getLayoutX());
		authorBox.setLayoutY(authorComboBox.getLayoutY());
		authorComboBox.setVisible(false);
		scrollAnchor.getChildren().add(authorBox);
	}

	/**
	 * This method sets the subject check combo box with all the subjects of the 
	 * chosen field, and checks the book's subjects.
	 */
	public void setSubjectBox() {
		ObservableList<Integer> cf = fieldBox.getCheckModel().getCheckedIndices();
		ObservableList<String> ols = FXCollections.observableArrayList();
		for (int i = 0; i < cf.size(); i++) {
			for (int j = 0; j < subjects[cf.get(i)].length; j++)
				ols.add(subjects[cf.get(i)][j].getSub());
		}
		subjectBox = new CheckComboBox<String>(ols);
		for (int i = 0; i < cf.size(); i++) {
			for (int j = 0; j < subjects[cf.get(i)].length; j++)
				if (checkSubjects[cf.get(i)][j])
					subjectBox.getCheckModel().check(subjects[cf.get(i)][j].getSub());
		}
		subjectBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends String> change) {
				ObservableList<String> cs = subjectBox.getCheckModel().getCheckedItems();
				for (int i = 0; i < subjects.length; i++) {
					for (int j = 0; j < subjects[i].length; j++)
						checkSubjects[i][j] = cs.contains(subjects[i][j].getSub());
				}
			}
		});
		subjectBox.setPrefSize(subjectComboBox.getPrefWidth(), subjectComboBox.getPrefHeight());
		subjectBox.setLayoutX(subjectComboBox.getLayoutX());
		subjectBox.setLayoutY(subjectComboBox.getLayoutY());
		subjectComboBox.setVisible(false);
		scrollAnchor.getChildren().add(subjectBox);

	}

}
