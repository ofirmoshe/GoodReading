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

public class KeywordCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression word;
	public final CollectionExpression book;
	
	public KeywordCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		word = new StringExpression("word", this);
		book = new CollectionExpression("ORM_Book", this);
	}
	
	public KeywordCriteria(PersistentSession session) {
		this(session.createCriteria(Keyword.class));
	}
	
	public KeywordCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public BookCriteria createBookCriteria() {
		return new BookCriteria(createCriteria("ORM_Book"));
	}
	
	public Keyword uniqueKeyword() {
		return (Keyword) super.uniqueResult();
	}
	
	public Keyword[] listKeyword() {
		java.util.List list = super.list();
		return (Keyword[]) list.toArray(new Keyword[list.size()]);
	}
}

