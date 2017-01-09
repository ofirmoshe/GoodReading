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

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class Employee extends i_book.GeneralUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Employee() {
	}
	
	public static Employee loadEmployeeByORMID(String ID) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadEmployeeByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee getEmployeeByORMID(String ID) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getEmployeeByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee loadEmployeeByORMID(String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadEmployeeByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee getEmployeeByORMID(String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getEmployeeByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee loadEmployeeByORMID(PersistentSession session, String ID) throws PersistentException {
		try {
			return (Employee) session.load(i_book.Employee.class, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee getEmployeeByORMID(PersistentSession session, String ID) throws PersistentException {
		try {
			return (Employee) session.get(i_book.Employee.class, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee loadEmployeeByORMID(PersistentSession session, String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Employee) session.load(i_book.Employee.class, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee getEmployeeByORMID(PersistentSession session, String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Employee) session.get(i_book.Employee.class, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryEmployee(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryEmployee(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryEmployee(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryEmployee(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee[] listEmployeeByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listEmployeeByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee[] listEmployeeByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listEmployeeByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryEmployee(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Employee as Employee");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryEmployee(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Employee as Employee");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Employee", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee[] listEmployeeByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryEmployee(session, condition, orderBy);
			return (Employee[]) list.toArray(new Employee[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee[] listEmployeeByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryEmployee(session, condition, orderBy, lockMode);
			return (Employee[]) list.toArray(new Employee[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee loadEmployeeByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadEmployeeByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee loadEmployeeByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadEmployeeByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee loadEmployeeByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Employee[] employees = listEmployeeByQuery(session, condition, orderBy);
		if (employees != null && employees.length > 0)
			return employees[0];
		else
			return null;
	}
	
	public static Employee loadEmployeeByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Employee[] employees = listEmployeeByQuery(session, condition, orderBy, lockMode);
		if (employees != null && employees.length > 0)
			return employees[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateEmployeeByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateEmployeeByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateEmployeeByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateEmployeeByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateEmployeeByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Employee as Employee");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateEmployeeByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Employee as Employee");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Employee", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Employee loadEmployeeByCriteria(EmployeeCriteria employeeCriteria) {
		Employee[] employees = listEmployeeByCriteria(employeeCriteria);
		if(employees == null || employees.length == 0) {
			return null;
		}
		return employees[0];
	}
	
	public static Employee[] listEmployeeByCriteria(EmployeeCriteria employeeCriteria) {
		return employeeCriteria.listEmployee();
	}
	
	public static Employee createEmployee() {
		return new i_book.Employee();
	}
	
	private int em_num;
	
	private String email;
	
	private String dep;
	
	private String position;
	
	public void setEm_num(int value) {
		this.em_num = value;
	}
	
	public int getEm_num() {
		return em_num;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setDep(String value) {
		this.dep = value;
	}
	
	public String getDep() {
		return dep;
	}
	
	public void setPosition(String value) {
		this.position = value;
	}
	
	public String getPosition() {
		return position;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
