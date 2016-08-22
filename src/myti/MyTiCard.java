package myti;

import java.util.ArrayList;
import java.util.Scanner;

public class MyTiCard {

	// some constants
	final static double CREDIT_LIMIT = 100.0;  // maximum allowed credit
	final static int LEGAL_MULTIPLE = 5; // multiple that we can re-charge by

	// we need to keep track of all (successful) purchases made
	private final static int MAX_PURCHASES = 10;
    // create a structure to keep track of travel passes purchased
	
	private double credit;
	private String id;
	private ArrayList<TravelPass> tp = new ArrayList<TravelPass>();
	Scanner scan = new Scanner(System.in);
	public MyTiCard(String id){
		this.id = id;
		this.credit = 0;
	}
	
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	public void popup(double amount) {
		this.credit += amount;
	}

	public String getId(){
		return id;
	}
	
	public double getCredit() {
		return credit;
	}
	
	public ArrayList<TravelPass> getTravelPass() throws MyException{
		if (tp.size() <= this.MAX_PURCHASES)
		return tp;
		else 
			throw new MyException("Your max purchases are 10.");
	}
}