package controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
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

public class BookInfoMenegmentController extends SystemController {

	public static Field[] fields;
	private boolean[] checkFields;
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

	public void initialize() {
		super.initialize();
		// field.setField("");
		Message msg = new Message("add book info", 1);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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
			Object[] o = new Object[4];
			o[0] = authorField.getText();
			o[1] = fieldField.getText();
			o[2] = subjectField.getText();
			o[3] = fields[cb.getSelectionModel().getSelectedIndex()-1];
			if (field != null)
				o[3] = field;
			if(o[0].equals("") && o[1].equals("") && o[2].equals(""))
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Add Book Information");
				alert.setContentText("You did not fill any field.");
				alert.showAndWait();
				return;
			}
			msg = new Message("book info menegment", 2, o);
			if (flag)
				msg.setFunc(3);

			try {
				Client.instance.sendToServer(msg);
				System.out.println("sent");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void subjectOnInput() {
		if (!(subjectField.getText().equals(""))) {
			subjectFlag = true;
			if(!flag)
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

					}
					else{
						flag=false;
						chooseFieldAnchor.setVisible(true);
					}
				}
			});
		}
		else {
			flag= false;
			subjectFlag=false;
			newFieldCheck.setVisible(false);
			newFieldCheck.setSelected(false);
			chooseFieldAnchor.setVisible(false);
		}

	}

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
		}
		else {
			flag= false;
			fieldFlag=false;
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

	@Override
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			Object[] ob = (Object[]) msg.getMsg();
			fields = (Field[]) ob[0];
			checkFields = new boolean[fields.length];
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setCheckboxes(fields);
				}
			});
			break;
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
		if (msg.getMsg().equals("fd")) {
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

	public void setCheckboxes(Object[] o) {

		ObservableList<String> olf = FXCollections.observableArrayList();
		olf.add("None");
		for (int i = 0; i < fields.length; i++) {
			olf.add(fields[i].getField());
		}
		cb.setItems(olf);
		cb.getSelectionModel().selectFirst();

	}
}
