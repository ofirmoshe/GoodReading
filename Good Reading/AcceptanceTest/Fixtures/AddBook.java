package Fixtures;

import java.io.IOException;
import java.util.Arrays;
import java.util.IllegalFormatException;

import client.Client;
import controllers.AbstractController;
import controllers.AddBookController;
import controllers.SearchBookController;
import controllers.SearchBookControllerTest;
import fit.ActionFixture;
import i_book.Book;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddBook extends ActionFixture {

	public TextField titleField;
	public TextField langField;
	public AnchorPane fieldChecklist;
	public AnchorPane subjectChecklist;
	public AnchorPane authorChecklist;
	public TextField imageField;
	public TextField keywordField;
	public TextArea summaryField;
	public TextArea tableField;
	public TextField priceField;
	public TextField pdfField;
	public TextField docField;
	public TextField fb2Field;
	public TextField newAuthorField;
	public byte[][] formats = new byte[3][];
	public AddBookController instance;
	SearchBookControllerTest sb;

	public void startBook() {

		instance = new AddBookController();
		instance.formats[0] = new byte[5];
		Arrays.fill(instance.formats[0], (byte) 1);
		sb = new SearchBookControllerTest();
		AbstractController.instance = instance;
		try {
			sb.setUp();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			new Client("localhost", 5555);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new JFXPanel();
		titleField = new TextField();
		langField = new TextField();
		fieldChecklist = new AnchorPane();
		subjectChecklist = new AnchorPane();
		authorChecklist = new AnchorPane();
		imageField = new TextField();
		keywordField = new TextField();
		summaryField = new TextArea();
		tableField = new TextArea();
		priceField = new TextField();
		pdfField = new TextField();
		docField = new TextField();
		fb2Field = new TextField();
		newAuthorField = new TextField();
		instance.subjectChecklist = subjectChecklist;
		instance.fieldChecklist = fieldChecklist;
		instance.authorChecklist = authorChecklist;
		instance.titleField = titleField;
		instance.langField = langField;
		instance.keywordField = keywordField;
		instance.summaryField = summaryField;
		instance.tableField = tableField;
		instance.priceField = priceField;
		instance.newAuthorField = newAuthorField;
		instance.initialize();
		while (!instance.initOver)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void setTitle(String title) {
		titleField.setText(title);
		instance.titleField = titleField;
	}

	public void setLang(String lang) {
		langField.setText(lang);
		instance.langField = langField;
	}

	public void setKeyword(String kw) {
		keywordField.setText(kw);
		instance.keywordField = keywordField;
	}

	public void setSummary(String summary) {
		summaryField.setText(summary);
		instance.summaryField = summaryField;
	}

	public void setTable(String table) {
		tableField.setText(table);
		instance.tableField = tableField;
	}

	public void setPrice(String price) {
		priceField.setText(price);
		instance.priceField = priceField;
	}

	public void setNewAuthor(String author) {
		newAuthorField.setText(author);
		instance.newAuthorField = newAuthorField;
	}

	public void addField(String field) {
		for (int i = 0; i < instance.fields.length; i++)
			if (instance.fields[i].getField().equals(field)) {
				instance.checkFields[i] = true;
				break;
			}
	}

	public void addSubject(String sub) {
		for (int i = 0; i < instance.checkFields.length; i++) {
			if (instance.checkFields[i]) {
				for (int j = 0; j < instance.subjects[i].length; j++) {
					if (instance.subjects[i][j].getSub().equals(sub)) {
						instance.checkSubjects[i][j] = true;
						break;
					}
				}
			}
		}
	}

	public void addAuthor(String author) {
		for (int i = 0; i < instance.authors.length; i++) {
			if (instance.authors[i].getName().equals(author)) {
				instance.checkAuthors[i] = true;
				break;
			}
		}
	}

	public boolean addBook() {
		AbstractController.instance= instance;
		instance.initOver = false;
		instance.addBookOver = false;
		System.out.println("add book");
		instance.addBookOnClick();
		while (!instance.addBookOver)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println("add book over");
		sb.setSearchQuery(instance.titleField.getText(), "", instance.langField.getText(), "", "", "");
		sb.instance.searchOver = false;
		sb.instance.searchOnEnterPressed();
		while (sb.instance.searchOver == false)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		if(sb.instance.books.length!=0)
			return(sb.instance.books[0].getTitle().equals(instance.titleField.getText()));
		else
			return false;
	}
}
