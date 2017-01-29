package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;

import org.controlsfx.control.CheckComboBox;

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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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

/**
 * Inventory management controller is the controller of the inventory management
 * (such as authors, fields and subjects)page.
 * 
 * @author Noy
 *
 */
public class InventoryManagementController extends SystemController {

	private Field[] fields;
	private Field[] f;
	private boolean[] checkFields;
	private Subject[] subjects;
	private boolean[] checkSubjects;
	private Author[] authors;
	private boolean[] checkAuthors;
	private boolean fieldFlag = false;
	private boolean flag = false;
	private boolean subjectFlag = false;
	private Field field = null;

	@FXML
	private TextField authorField;
	@FXML
	private TextField fieldField;
	@FXML
	private TextField subjectField;
	@FXML
	private AnchorPane fieldChecklist;
	@FXML
	private ImageView saveButtonImage;
	@FXML
	private CheckBox newFieldCheck;
	@FXML
	private ChoiceBox<String> cb;
	@FXML
	private AnchorPane chooseFieldAnchor;
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

	/**
	 * This method initializes the controller.
	 */
	public void initialize() {
		super.initialize();
		// field.setField("");
		Message msg = new Message("add book", 1);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This function is called whenever "Save" button is pressed. This method
	 * first checks if user inserted a new subject but didn't chose a proper
	 * field. In case it happens, it displays a popup message, alerts the issue.
	 * Else, it fills up an Object array with the new inventory data and sends
	 * it to the server, requesting to add that new inventory to the database.
	 */
	public void saveBookInfoOnClick() {

		Message msg;

		if (subjectFlag && !flag && cb.getSelectionModel().getSelectedItem().equals("None")) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Add Book Information");
					alert.setContentText("You must Choose a Field for the new subject.");
					alert.showAndWait();
				}
			});
		} else {
			Object[] o = new Object[7];
			o[0] = authorField.getText();
			o[1] = fieldField.getText();
			o[2] = subjectField.getText();
			o[3] = fields[cb.getSelectionModel().getSelectedIndex()];
			if (field != null)
				o[3] = field;
			if (o[0].equals("") && o[1].equals("") && o[2].equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Add Book Information");
				alert.setContentText("You did not fill any field.");
				alert.showAndWait();
				return;
			}
			ObservableList<String> sf = fieldBox.getCheckModel().getCheckedItems();
			Field[] selectedFields = new Field[sf.size()];

			int index = 0;
			for (int i = 0; i < f.length; i++) {
				if (sf.contains(f[i].getField())) {
					selectedFields[index] = f[i];
					index++;
				}
			}
			o[4] = selectedFields;

			ObservableList<String> ss = subjectBox.getCheckModel().getCheckedItems();
			Subject[] selectedSubjects = new Subject[ss.size()];
			int index1 = 0;
			for (int i = 0; i < subjects.length; i++) {
				if (ss.contains(subjects[i].getSub())) {
					selectedSubjects[index] = subjects[i];
					index1++;
				}
			}

			o[5] = selectedSubjects;
			ObservableList<String> sa = authorBox.getCheckModel().getCheckedItems();
			Author[] selectedAuthors = new Author[sa.size()];
			index = 0;
			for (int i = 0; i < authors.length; i++) {
				if (sa.contains(authors[i].getName())) {
					selectedAuthors[index] = authors[i];
					index++;
				}
			}
			o[6] = selectedAuthors;

			msg = new Message("inventory management", 2, o);
			if (flag)
				msg.setFunc(3);

			try {
				Client.instance.sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is called whenever the user insert a new subject. It checks
	 * if user's input is an empty string. If so, it disappears fields checkbox
	 * list and "newField" checkbox- which asks to connect between new subject
	 * and new field. If not, it turns on a subject flag- which means that the
	 * user inserted a new subject.
	 */
	public void subjectOnInput() {
		if (!(subjectField.getText().equals(""))) {
			subjectFlag = true;
			if (!flag)
				chooseFieldAnchor.setVisible(true);
			if (fieldFlag)
				newFieldCheck.setVisible(true);
			newFieldCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
				public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
					if (new_val) {
						field = new Field();
						field.setField(fieldField.toString());
						flag = true;
						chooseFieldAnchor.setVisible(false);

					} else {
						flag = false;
						chooseFieldAnchor.setVisible(true);
					}
				}
			});
		} else {
			flag = false;
			subjectFlag = false;
			newFieldCheck.setVisible(false);
			newFieldCheck.setSelected(false);
			chooseFieldAnchor.setVisible(false);
		}

	}

	/**
	 * This method is called whenever the user insert a new field. It checks if
	 * user's input is an empty string. If so, it disappears "newField"
	 * checkbox- which asks to connect between new subject and new field. If
	 * not, it turns on a field flag- which means that the user inserted a new
	 * field.
	 */
	public void fieldOnInput() {
		if (!(fieldField.getText().equals(""))) {
			fieldFlag = true;
			if (subjectFlag)
				newFieldCheck.setVisible(true);
			newFieldCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
				public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
					if (new_val) {
						field = new Field();
						field.setField(fieldField.toString());
						flag = true;

					}
				}
			});
		} else {
			flag = false;
			fieldFlag = false;
			newFieldCheck.setVisible(false);
			newFieldCheck.setSelected(false);
		}

	}

	public void saveOnHover() {
		saveButtonImage.setImage(new Image(GraphicsImporter.class.getResource("button_hover.png").toString()));
	}

	public void saveOffHover() {
		saveButtonImage.setImage(new Image(GraphicsImporter.class.getResource("button.png").toString()));
	}

	/**
	 * This method implements AbstractController's method. It handles message
	 * from server, and displays the inventory data.
	 * 
	 * @param msg
	 *            case 1: The message is an field array. It sets the list of all
	 *            fields in DB (which could be related to the new subject). the
	 *            method check the msg info and presents a proper popup, alert
	 *            the inform the user. Such as "Author already exists",
	 *            "Info has been successfully updated" ext.
	 */
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch (msg.getFunc()) {
		case 1:
			Object[] ob = (Object[]) msg.getMsg();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					f = (Field[]) ob[0];
					checkFields = new boolean[f.length];
					authors = (Author[]) ob[2];
					checkAuthors = new boolean[authors.length];
					subjects = (Subject[]) ob[3];
					checkSubjects = new boolean[subjects.length];
					setFieldBox();
					setAuthorBox();
					setSubjectBox();
				}
			});
			
		}

		if (msg.getMsg().equals("ad")) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Add Book Information");
					alert.setContentText("This author already exists.");
					alert.showAndWait();
				}
			});
		}
		if (msg.getMsg().equals("sd")) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Add Book Information");
					alert.setContentText("This subject already exists.");
					alert.showAndWait();
				}
			});
		}
		if (msg.getMsg().equals("fd"))

		{
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Add Book Information");
					alert.setContentText("This field already exists.");
					alert.showAndWait();
				}
			});
		}
		if (msg.getMsg().equals("s")) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Add Book Information");
					alert.setContentText("Book information has been updated successfully");
					alert.showAndWait();
				}
			});
		}

	}

	/**
	 * This function is called after the page is initializes - the server sends
	 * the list of existing fields in DB. This method sets the list of fields
	 * that exists in DB.
	 * 
	 * @param o
	 *            is a field array that was sent from server. It contains all
	 *            existing fields in DB.
	 */
	public void setChoiseboxes(Object[] o) {

		ObservableList<String> olf = FXCollections.observableArrayList();
		olf.add("None");
		for (int i = 0; i < fields.length; i++) {
			olf.add(fields[i].getField());
		}
		cb.setItems(olf);
		cb.getSelectionModel().selectFirst();

	}

	/**
	 * This method sets the field check combo box with all the fields in DB.
	 */
	public void setFieldBox() {
		ObservableList<String> olf = FXCollections.observableArrayList();
		for (int i = 0; i < f.length; i++) {
			olf.add(f[i].getField());
		}
		fieldBox = new CheckComboBox<String>(olf);
		for (int i = 0; i < f.length; i++) {
			if (checkFields[i])
				fieldBox.getCheckModel().check(i);
		}

		fieldBox.setPrefSize(fieldComboBox.getPrefWidth(), fieldComboBox.getPrefHeight());
		fieldBox.setLayoutX(fieldComboBox.getLayoutX());
		fieldBox.setLayoutY(fieldComboBox.getLayoutY());
		fieldComboBox.setVisible(false);
		scrollAnchor.getChildren().add(fieldBox);
	}

	/**
	 * This method sets the author check combo box with all the authors in DB.
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
	 * This method sets the subject check combo box with all the subjects in DB.
	 */
	public void setSubjectBox() {
		ObservableList<String> ols = FXCollections.observableArrayList();
		for (int j = 0; j < subjects.length; j++)
			ols.add(subjects[j].getSub());
		subjectBox = new CheckComboBox<String>(ols);

		for (int j = 0; j < subjects.length; j++)
			if (checkSubjects[j])
				subjectBox.getCheckModel().check(j);

		subjectBox.setPrefSize(subjectComboBox.getPrefWidth(), subjectComboBox.getPrefHeight());
		subjectBox.setLayoutX(subjectComboBox.getLayoutX());
		subjectBox.setLayoutY(subjectComboBox.getLayoutY());
		subjectComboBox.setVisible(false);
		scrollAnchor.getChildren().add(subjectBox);

	}

}
