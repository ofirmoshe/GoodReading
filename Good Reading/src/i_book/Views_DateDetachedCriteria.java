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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class Views_DateDetachedCriteria extends AbstractORMDetachedCriteria {
	public final DateExpression date;
	public final IntegerExpression bookId;
	public final AssociationExpression book;
	public final IntegerExpression viewCount;
	
	public Views_DateDetachedCriteria() {
		super(i_book.Views_Date.class, i_book.Views_DateCriteria.class);
		date = new DateExpression("date", this.getDetachedCriteria());
		bookId = new IntegerExpression("book.ID", this.getDetachedCriteria());
		book = new AssociationExpression("book", this.getDetachedCriteria());
		viewCount = new IntegerExpression("viewCount", this.getDetachedCriteria());
	}
	
	public Views_DateDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.Views_DateCriteria.class);
		date = new DateExpression("date", this.getDetachedCriteria());
		bookId = new IntegerExpression("book.ID", this.getDetachedCriteria());
		book = new AssociationExpression("book", this.getDetachedCriteria());
		viewCount = new IntegerExpression("viewCount", this.getDetachedCriteria());
	}
	
	public BookDetachedCriteria createBookCriteria() {
		return new BookDetachedCriteria(createCriteria("book"));
	}
	
	public Views_Date uniqueViews_Date(PersistentSession session) {
		return (Views_Date) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Views_Date[] listViews_Date(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Views_Date[]) list.toArray(new Views_Date[list.size()]);
	}
}

