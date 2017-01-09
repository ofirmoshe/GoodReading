/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Ort Braude College
 * License Type: Academic
 */
package i_book;

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class Views_DateCriteria extends AbstractORMCriteria {
	public final DateExpression date;
	public final IntegerExpression bookId;
	public final AssociationExpression book;
	public final IntegerExpression viewCount;
	
	public Views_DateCriteria(Criteria criteria) {
		super(criteria);
		date = new DateExpression("date", this);
		bookId = new IntegerExpression("book.ID", this);
		book = new AssociationExpression("book", this);
		viewCount = new IntegerExpression("viewCount", this);
	}
	
	public Views_DateCriteria(PersistentSession session) {
		this(session.createCriteria(Views_Date.class));
	}
	
	public Views_DateCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public BookCriteria createBookCriteria() {
		return new BookCriteria(createCriteria("book"));
	}
	
	public Views_Date uniqueViews_Date() {
		return (Views_Date) super.uniqueResult();
	}
	
	public Views_Date[] listViews_Date() {
		java.util.List list = super.list();
		return (Views_Date[]) list.toArray(new Views_Date[list.size()]);
	}
}

