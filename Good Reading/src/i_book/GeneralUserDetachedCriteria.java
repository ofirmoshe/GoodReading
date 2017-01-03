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

public class GeneralUserDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression ID;
	public final StringExpression Fname;
	public final StringExpression Lname;
	public final StringExpression password;
	
	public GeneralUserDetachedCriteria() {
		super(i_book.GeneralUser.class, i_book.GeneralUserCriteria.class);
		ID = new StringExpression("ID", this.getDetachedCriteria());
		Fname = new StringExpression("Fname", this.getDetachedCriteria());
		Lname = new StringExpression("Lname", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
	}
	
	public GeneralUserDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.GeneralUserCriteria.class);
		ID = new StringExpression("ID", this.getDetachedCriteria());
		Fname = new StringExpression("Fname", this.getDetachedCriteria());
		Lname = new StringExpression("Lname", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
	}
	
	public GeneralUser uniqueGeneralUser(PersistentSession session) {
		return (GeneralUser) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public GeneralUser[] listGeneralUser(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (GeneralUser[]) list.toArray(new GeneralUser[list.size()]);
	}
}

