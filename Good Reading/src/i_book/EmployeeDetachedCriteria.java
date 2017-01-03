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

public class EmployeeDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression ID;
	public final StringExpression Fname;
	public final StringExpression Lname;
	public final StringExpression password;
	public final IntegerExpression em_num;
	public final StringExpression email;
	public final StringExpression dep;
	public final StringExpression position;
	
	public EmployeeDetachedCriteria() {
		super(i_book.Employee.class, i_book.EmployeeCriteria.class);
		ID = new StringExpression("ID", this.getDetachedCriteria());
		Fname = new StringExpression("Fname", this.getDetachedCriteria());
		Lname = new StringExpression("Lname", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		em_num = new IntegerExpression("em_num", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		dep = new StringExpression("dep", this.getDetachedCriteria());
		position = new StringExpression("position", this.getDetachedCriteria());
	}
	
	public EmployeeDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.EmployeeCriteria.class);
		ID = new StringExpression("ID", this.getDetachedCriteria());
		Fname = new StringExpression("Fname", this.getDetachedCriteria());
		Lname = new StringExpression("Lname", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		em_num = new IntegerExpression("em_num", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		dep = new StringExpression("dep", this.getDetachedCriteria());
		position = new StringExpression("position", this.getDetachedCriteria());
	}
	
	public Employee uniqueEmployee(PersistentSession session) {
		return (Employee) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Employee[] listEmployee(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Employee[]) list.toArray(new Employee[list.size()]);
	}
}

