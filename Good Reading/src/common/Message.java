package common;
import java.io.Serializable;

import controllers.AbstractController;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int func;
	private Object msg;
	
	public Message( int f, Object m){
		setFunc(f);
		setMsg(m);
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


}
