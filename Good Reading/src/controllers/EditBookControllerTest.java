package controllers;

import junit.framework.TestCase;


public class EditBookControllerTest extends TestCase {
	public EditBookController instance;
	SearchBookControllerTest sb;
	protected void setUp() throws Exception {
		
		instance=new EditBookController();
		sb=new SearchBookControllerTest();
		AbstractController.instance=instance;
	}

	public void testRemoveBook_BookNotExists() {
		instance.removeOver=false;
		instance.removeBook(100);
		while(instance.removeOver==false)
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		assertTrue(instance.removeFailed);
	}
	
	public void testRemoveBook_BookExists() {
		instance.removeOver=false;
		instance.removeBook(27);
		while(instance.removeOver==false)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		assertTrue(instance.removeSuccess);
		sb.instance.searchOver=false;
		sb.setSearchQuery("test", "", "", "", "", "");
		sb.instance.searchOnEnterPressed();
		while(sb.instance.searchOver==false)
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		boolean result=true;
		if(sb.instance.books.length==0)
			result=false;	
		assertFalse(result);
	}
	
	
}