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

public class KeywordDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression word;
	public final CollectionExpression book;
	
	public KeywordDetachedCriteria() {
		super(i_book.Keyword.class, i_book.KeywordCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		word = new StringExpression("word", this.getDetachedCriteria());
		book = new CollectionExpression("ORM_Book", this.getDetachedCriteria());
	}
	
	public KeywordDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.KeywordCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		word = new StringExpression("word", this.getDetachedCriteria());
		book = new CollectionExpression("ORM_Book", this.getDetachedCriteria());
	}
	
	public BookDetachedCriteria createBookCriteria() {
		return new BookDetachedCriteria(createCriteria("ORM_Book"));
	}
	
	public Keyword uniqueKeyword(PersistentSession session) {
		return (Keyword) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Keyword[] listKeyword(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Keyword[]) list.toArray(new Keyword[list.size()]);
	}
}

