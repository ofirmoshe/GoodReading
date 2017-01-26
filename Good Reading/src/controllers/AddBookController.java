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
	private byte[][] formats=new byte[3][];

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
	@FXML
	private TextField priceField;
	@FXML
	private TextField pdfField;
	@FXML
	private TextField docField;
	@FXML
	private TextField fb2Field;
	@FXML
	private TextField newAuthorField;

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
		Image img = new Image(GraphicsImporter.class.getResource("no_image.jpg").toString());
		BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, "jpg", s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bimg = s.toByteArray();
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
	public void addBookOnClick() {
		// ClientUI.instance.getHostServices().showDocument(docField.getText());
		Object[] o = new Object[14];
		boolean mustFlag = false;
		if (!titleField.getText().equals(""))
			o[0] = titleField.getText();
		else
			mustFlag = true;
		if (!langField.getText().equals(""))
			o[1] = langField.getText();
		else
			mustFlag = true;
		int count = 0;
		for (int i = 0; i < checkFields.length; i++)
			if (checkFields[i])
				count++;
		if (count == 0)
			mustFlag = true;
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
		if (count == 0)
			mustFlag = true;
		Author[] selectedAuthors = new Author[count];
		index = 0;
		for (int i = 0; i < checkAuthors.length; i++) {
			if (checkAuthors[i]) {
				selectedAuthors[index] = authors[i];
				index++;
			}
		}
		o[4] = selectedAuthors;
		o[5] = keywordField.getText();
		o[6] = bimg;
		o[7] = summaryField.getText();
		o[8] = tableField.getText();
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
			alert.setTitle("Add Book Failed");
			alert.setHeaderText("Invalid Price");
			alert.setContentText("Please check you entered a valid price and try again.");
			alert.showAndWait();
			return;
		}
		if (formats[0]==null && formats[1]==null && formats[2]==null)
			mustFlag = true;
		else {
			o[10] = formats[0];
			o[11] = formats[1];
			o[12] = formats[2];
			o[13]=newAuthorField.getText();
		}
		if (mustFlag) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Add Book Failed");
			alert.setHeaderText("Can not add book");
			alert.setContentText("One or more required fields are empty.");
			alert.showAndWait();
			return;
		}
		Message msg = new Message("add book", 2, o);
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
			checkSubjects = new boolean[fields.length][];
			for (int i = 0; i < fields.length; i++) {
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
			break;
		case 2:
			if(msg.getMsg().equals("s")){
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
