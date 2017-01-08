package controllers;

import common.Message;
import javafx.fxml.FXML;

public class MembershipController extends SystemController {
	
	public void initialize(){
		super.initialize();
	}
	
	public void monthlyOnClick(){
		System.out.println("monthly");
	}
	
	public void yearlyOnClick(){
		System.out.println("yearly");
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
