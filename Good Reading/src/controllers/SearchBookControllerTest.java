package controllers;

import java.io.IOException;

import org.junit.Assert;

import client.Client;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import junit.framework.TestCase;

public class SearchBookControllerTest extends TestCase {
	public SearchBookController instance;
	public TextField title;
	public TextField author;
	public TextField lang;
	public TextField keyword;
	public ChoiceBox<String> subjectBox;
	public ChoiceBox<String> fieldBox;
	public ChoiceBox<String> optionBox;
	public AnchorPane scrollAnchor;

	public void setUp() throws Exception {
		System.out.println("set up");
		instance = new SearchBookController();
		AbstractController.instance = instance;
		try {
			new Client("localhost", 5555);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// instance.initialize();
		new JFXPanel();
		title = new TextField();
		author = new TextField();
		lang = new TextField();
		keyword = new TextField();
		subjectBox = new ChoiceBox<String>();
		fieldBox = new ChoiceBox<String>();
		optionBox = new ChoiceBox<String>();
		scrollAnchor = new AnchorPane();
	}

	public void setSearchQuery(String t, String a, String lan, String kw, String field, String sub) {
		instance.initOver=false;
		instance.searchOver=false;
		title.setText(t);
		author.setText(a);
		lang.setText(lan);
		keyword.setText(kw);
		instance.titleField = title;
		instance.authorField = author;
		instance.langField = lang;
		instance.keywordField = keyword;
		instance.subjectBox = subjectBox;
		instance.fieldBox = fieldBox;
		instance.optionBox = optionBox;
		instance.scrollAnchor = scrollAnchor;
		instance.initialize();
		while (!instance.initOver)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (!field.equals(""))
			instance.fieldBox.getSelectionModel().select(field);
		if (!sub.equals(""))
			instance.subjectBox.getSelectionModel().select(sub);
	}

	public void testSearchBook_TAF() {
		setSearchQuery("h", "jk rowli", "", "", "Literature & Fiction", "");
		instance.searchOnEnterPressed();
		while (!instance.searchOver) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String[] ActualResult = new String[instance.books.length];
		for (int i = 0; i < instance.books.length; i++)
			ActualResult[i] = instance.books[i].getTitle();
		String[] ExpectedResult = new String[2];
		// ExpectedResult[0] = "Fantastic Beasts";
		ExpectedResult[0] = "The Fault in Our Stars";
		ExpectedResult[1] = "Harry Potter";
		Assert.assertArrayEquals(ActualResult, ExpectedResult);
	}

	public void testSearchBook_AFS() {
		setSearchQuery("", "jk rowli", "", "", "Literature & Fiction", "Action & Adventure");
		instance.searchOnEnterPressed();
		while (!instance.searchOver) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String[] ActualResult = new String[instance.books.length];
		for (int i = 0; i < instance.books.length; i++)
			ActualResult[i] = instance.books[i].getTitle();
		String[] ExpectedResult = new String[2];
		ExpectedResult[0] = "Fantastic Beasts";
		ExpectedResult[1] = "The Fault in Our Stars";
		// ExpectedResult[2] = "Harry Potter";
		Assert.assertArrayEquals(ActualResult, ExpectedResult);
	}

	
	public void testSearchBook_ALF() {
		setSearchQuery("", "jk rowli", "Russian", "", "Literature & Fiction", "");
		instance.searchOnEnterPressed();
		while (!instance.searchOver) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String[] ActualResult = new String[instance.books.length];
		for (int i = 0; i < instance.books.length; i++)
			ActualResult[i] = instance.books[i].getTitle();
		String[] ExpectedResult = new String[1];
		//ExpectedResult[0] = "Fantastic Beasts";
		//ExpectedResult[1] = "The Fault in Our Stars";
		ExpectedResult[0] = "Harry Potter";
		Assert.assertArrayEquals(ActualResult, ExpectedResult);
	}
	
	public void testSearchBook_Lang() {
		setSearchQuery("", "", "Russian", "", "", "");
		instance.searchOnEnterPressed();
		while (!instance.searchOver) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String[] ActualResult = new String[instance.books.length];
		for (int i = 0; i < instance.books.length; i++)
			ActualResult[i] = instance.books[i].getTitle();
		String[] ExpectedResult = new String[2];
		ExpectedResult[0] = "Harry Potter";
		ExpectedResult[1] = "The Night Bird";
		Assert.assertArrayEquals(ActualResult, ExpectedResult);
	}
	
	
	public void testSearchBook_Sub() {
		setSearchQuery("", "", "", "", "", "Dramas & Plays");
		instance.searchOnEnterPressed();
		while (!instance.searchOver) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String[] ActualResult = new String[instance.books.length];
		for (int i = 0; i < instance.books.length; i++)
			ActualResult[i] = instance.books[i].getTitle();
		String[] ExpectedResult = new String[1];
		ExpectedResult[0] = "Small Great Things";
		//ExpectedResult[1] = "The Night Bird";
		Assert.assertArrayEquals(ActualResult, ExpectedResult);
	}
	
	public void testSearchBook_EmptyStrings() {
		setSearchQuery("", "", "", "", "", "");
		instance.searchOnEnterPressed();
		while (!instance.searchOver) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			assertTrue(instance.emptyQuery);
		}
	}
}