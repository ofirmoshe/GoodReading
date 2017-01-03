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

public class AuthorCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression name;
	public final CollectionExpression book;
	
	public AuthorCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		name = new StringExpression("name", this);
		book = new CollectionExpression("ORM_Book", this);
	}
	
	public AuthorCriteria(PersistentSession session) {
		this(session.createCriteria(Author.class));
	}
	
	public AuthorCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public BookCriteria createBookCriteria() {
		return new BookCriteria(createCriteria("ORM_Book"));
	}
	
	public Author uniqueAuthor() {
		return (Author) super.uniqueResult();
	}
	
	public Author[] listAuthor() {
		java.util.List list = super.list();
		return (Author[]) list.toArray(new Author[list.size()]);
	}
}

