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

public class AuthorDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression name;
	public final CollectionExpression book;
	
	public AuthorDetachedCriteria() {
		super(i_book.Author.class, i_book.AuthorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		book = new CollectionExpression("ORM_Book", this.getDetachedCriteria());
	}
	
	public AuthorDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.AuthorCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		book = new CollectionExpression("ORM_Book", this.getDetachedCriteria());
	}
	
	public BookDetachedCriteria createBookCriteria() {
		return new BookDetachedCriteria(createCriteria("ORM_Book"));
	}
	
	public Author uniqueAuthor(PersistentSession session) {
		return (Author) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Author[] listAuthor(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Author[]) list.toArray(new Author[list.size()]);
	}
}

