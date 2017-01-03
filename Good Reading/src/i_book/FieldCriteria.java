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

public class FieldCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression field;
	public final CollectionExpression book;
	public final CollectionExpression subject;
	
	public FieldCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		field = new StringExpression("field", this);
		book = new CollectionExpression("ORM_Book", this);
		subject = new CollectionExpression("ORM_Subject", this);
	}
	
	public FieldCriteria(PersistentSession session) {
		this(session.createCriteria(Field.class));
	}
	
	public FieldCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public BookCriteria createBookCriteria() {
		return new BookCriteria(createCriteria("ORM_Book"));
	}
	
	public SubjectCriteria createSubjectCriteria() {
		return new SubjectCriteria(createCriteria("ORM_Subject"));
	}
	
	public Field uniqueField() {
		return (Field) super.uniqueResult();
	}
	
	public Field[] listField() {
		java.util.List list = super.list();
		return (Field[]) list.toArray(new Field[list.size()]);
	}
}

