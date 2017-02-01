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
	SearchBookController instance;
	public TextField title;
	public TextField author;
	public TextField lang;
	public TextField keyword;
	public ChoiceBox<String> subjectBox;
	public ChoiceBox<String> fieldBox;
	public ChoiceBox<String> optionBox;
	public AnchorPane scrollAnchor;

	public SearchBookControllerTest(String name) {
		super(name);
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

	public void setSearchQuery(String t,String a,String lan,String kw,String field,String sub){
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
		instance.initialize();
		while(!instance.initOver)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(!field.equals(""))
			instance.fieldBox.getSelectionModel().select(field);
		if(!sub.equals(""))
			instance.subjectBox.getSelectionModel().select(sub);
	}

	public void testSearchBook_false() {
		setSearchQuery("","jk rowli","","","Literature & Fiction","");
		instance.searchOnEnterPressed();
		while (!instance.searchOver) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String[] ActualResult = new String[instance.books.length];
		for(int i=0; i<instance.books.length; i++)
			ActualResult[i]=instance.books[i].getTitle();
		String[] ExpectedResult = new String[3];
		ExpectedResult[0] = "Fantastic Beasts";
		ExpectedResult[1] = "The Fault in Our Stars";
		ExpectedResult[2] = "Harry Potter";
		Assert.assertArrayEquals(ActualResult, ExpectedResult);
	}

}
