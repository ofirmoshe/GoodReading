package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import boundary.ClientUI;
import client.Client;
import common.Message;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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

public class AddBookController extends SystemController {

	private Author[] authors;
	public static Field[] fields;
	public static Subject[][] subjects;
	private int currSubCount;
	private boolean[] checkFields;
	private GridPane subjectGrid = null;

	@FXML
	private TextField titleField;
	@FXML
	private TextField langField;
	@FXML
	private TextField authorField;
	@FXML
	private AnchorPane fieldChecklist;
	@FXML
	private AnchorPane subjectChecklist;
	@FXML
	private AnchorPane authorChecklist;

	public void initialize() {
		super.initialize();
		Message msg = new Message("add book", 1);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		subjectChecklist.setPrefHeight(1);
	}

	public void addBookOnClick() {

	}

	@Override
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			Object[] ob = (Object[]) msg.getMsg();
			fields = (Field[]) ob[0];
			checkFields = new boolean[fields.length];
			subjects = (Subject[][]) ob[1];
			authors = (Author[]) ob[2];
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setCheckboxes(fields);
					setCheckboxes(authors);
				}
			});
		}
	}

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
				authorChecklist.getChildren().add(cb);
				fieldChecklist.setPrefHeight(21 * authors.length);
			}
		}
	}

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
					cb.setUserData(j);
					subjectGrid.add(cb, 0, currSubCount);
				}
			}
		}
		subjectChecklist.getChildren().add(subjectGrid);
		subjectChecklist.setPrefHeight(18 * currSubCount);
		currSubCount = 0;
	}

}
