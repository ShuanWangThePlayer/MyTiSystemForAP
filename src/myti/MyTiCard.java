package myti;

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
	Scanner scan = new Scanner(System.in);
	public MyTiCard(String id){
		this.id = id;
		this.credit = 0;
	}
	
	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getId(){
		return id;
	}
	
	public double getCredit() {
		return credit;
	}
}