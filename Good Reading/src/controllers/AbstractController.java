package controllers;

import java.io.Serializable;

import common.Message;

public abstract class AbstractController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static AbstractController instance;
	
	

	public abstract void handleMessage(Message msg);

	public String toString(){
		return "Abstract Controller";
	}

}
