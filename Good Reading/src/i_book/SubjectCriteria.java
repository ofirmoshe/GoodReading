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

public class SubjectCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression fieldId;
	public final AssociationExpression field;
	public final StringExpression sub;
	public final CollectionExpression book;
	
	public SubjectCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		fieldId = new IntegerExpression("field.ID", this);
		field = new AssociationExpression("field", this);
		sub = new StringExpression("sub", this);
		book = new CollectionExpression("ORM_Book", this);
	}
	
	public SubjectCriteria(PersistentSession session) {
		this(session.createCriteria(Subject.class));
	}
	
	public SubjectCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public FieldCriteria createFieldCriteria() {
		return new FieldCriteria(createCriteria("field"));
	}
	
	public BookCriteria createBookCriteria() {
		return new BookCriteria(createCriteria("ORM_Book"));
	}
	
	public Subject uniqueSubject() {
		return (Subject) super.uniqueResult();
	}
	
	public Subject[] listSubject() {
		java.util.List list = super.list();
		return (Subject[]) list.toArray(new Subject[list.size()]);
	}
}

