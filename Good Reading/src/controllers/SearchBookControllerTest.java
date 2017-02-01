package controllers;

import junit.framework.TestCase;


public class SearchBookControllerTest extends TestCase {
	SearchBookController instance;
	public SearchBookControllerTest(String name) {
		super(name);
		instance=new SearchBookController();
		//instance.initialize();
	}

	/**
	 * Run the void searchBook(String, String, String, String, String, String)
	 * method test
	 */
	public void testSearchBook() {
		String title=new String("harry potter");
		System.out.println("before search");
		instance.searchBook(title,"" ,"" ,"", "0", "None");
		System.out.println("before while");
		while(!instance.searchOver)
		{
			System.out.print("1");
		}
		System.out.println("after while");
		String[] ExpectedResult=new String[1];
		ExpectedResult[0]="Harry Potter";
		assertTrue(instance.books[0].getTitle().equals(ExpectedResult[0]));
	}
}