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

public class GeneralUserCriteria extends AbstractORMCriteria {
	public final StringExpression ID;
	public final StringExpression Fname;
	public final StringExpression Lname;
	public final StringExpression password;
	
	public GeneralUserCriteria(Criteria criteria) {
		super(criteria);
		ID = new StringExpression("ID", this);
		Fname = new StringExpression("Fname", this);
		Lname = new StringExpression("Lname", this);
		password = new StringExpression("password", this);
	}
	
	public GeneralUserCriteria(PersistentSession session) {
		this(session.createCriteria(GeneralUser.class));
	}
	
	public GeneralUserCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public GeneralUser uniqueGeneralUser() {
		return (GeneralUser) super.uniqueResult();
	}
	
	public GeneralUser[] listGeneralUser() {
		java.util.List list = super.list();
		return (GeneralUser[]) list.toArray(new GeneralUser[list.size()]);
	}
}

