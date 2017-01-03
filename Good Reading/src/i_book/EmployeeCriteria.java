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

public class EmployeeCriteria extends AbstractORMCriteria {
	public final StringExpression ID;
	public final StringExpression Fname;
	public final StringExpression Lname;
	public final StringExpression password;
	public final IntegerExpression em_num;
	public final StringExpression email;
	public final StringExpression dep;
	public final StringExpression position;
	
	public EmployeeCriteria(Criteria criteria) {
		super(criteria);
		ID = new StringExpression("ID", this);
		Fname = new StringExpression("Fname", this);
		Lname = new StringExpression("Lname", this);
		password = new StringExpression("password", this);
		em_num = new IntegerExpression("em_num", this);
		email = new StringExpression("email", this);
		dep = new StringExpression("dep", this);
		position = new StringExpression("position", this);
	}
	
	public EmployeeCriteria(PersistentSession session) {
		this(session.createCriteria(Employee.class));
	}
	
	public EmployeeCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public Employee uniqueEmployee() {
		return (Employee) super.uniqueResult();
	}
	
	public Employee[] listEmployee() {
		java.util.List list = super.list();
		return (Employee[]) list.toArray(new Employee[list.size()]);
	}
}

