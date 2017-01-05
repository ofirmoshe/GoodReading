package common;
import java.io.Serializable;

import controllers.AbstractController;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cont;
	private int func;
	private Object msg;
	
	public Message( String c, int f, Object m){
		setCont(c);
		setFunc(f);
		setMsg(m);
	}
	
	public Message( String c, int f){
		setCont(c);
		setFunc(f);
	}
	
	public Message(int f){
		setFunc(f);
	}

	public int getFunc() {
		return func;
	}

	public void setFunc(int func) {
		this.func = func;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}


}
