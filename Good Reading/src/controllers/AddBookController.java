package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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
import javafx.embed.swing.SwingFXUtils;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddBookController extends SystemController {

	private Author[] authors;
	public static Field[] fields;
	public static Subject[][] subjects;
	private int currSubCount;
	private boolean[] checkFields;
	private GridPane subjectGrid = null;
	private boolean[][] checkSubjects;
	private boolean[] checkAuthors;
	private byte[] bimg = null;

	@FXML
	private TextField titleField;
	@FXML
	private TextField langField;
	@FXML
	private AnchorPane fieldChecklist;
	@FXML
	private AnchorPane subjectChecklist;
	@FXML
	private AnchorPane authorChecklist;
	@FXML
	private TextField imageField;
	@FXML
	private TextField keywordField;
	@FXML
	private TextArea summaryField;
	@FXML
	private TextArea tableField;

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

	public void addBookOnClick() {
		Object[] o = new Object[9];
		o[0] = titleField.getText();
		o[1] = langField.getText();
		int count = 0;
		for (int i = 0; i < checkFields.length; i++)
			if (checkFields[i])
				count++;
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
		Author[] selectedAuthors = new Author[count];
		index = 0;
		for (int i = 0; i < checkAuthors.length; i++) {
			if (checkAuthors[i]) {
				selectedAuthors[index] = authors[i];
				index++;
			}
		}
		o[4] = selectedAuthors;
		o[5]=keywordField.getText();
		o[6]=bimg;
		o[7]=summaryField.getText();
		o[8]=tableField.getText();
		Message msg = new Message("add book",2,o);
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			Object[] ob = (Object[]) msg.getMsg();
			fields = (Field[]) ob[0];
			checkFields = new boolean[fields.length];
			subjects = (Subject[][]) ob[1];
			checkSubjects =new boolean[fields.length][];
			for(int i=0; i<fields.length; i++){
				checkSubjects[i] = new boolean[subjects[i].length];
			}
			authors = (Author[]) ob[2];
			checkAuthors = new boolean[authors.length];
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
							for(int i=0; i<checkSubjects[sin].length; i++){
								checkSubjects[sin][i]=false;
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
					if(checkSubjects[i][j])
						cb.setSelected(true);
					int[] in = {i,j};
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
