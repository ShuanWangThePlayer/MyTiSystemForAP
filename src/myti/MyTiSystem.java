package myti;

import java.util.*;
/**
 * Implementation of basic MyTi system outline.
 * A MyTi card can be used to purchase travel passes:
 *     2Hour or All Day, Zone 1 or Zones 1+2.
 * Each type of travel pass has different prices.
 * This program loops through options of purchasing a pass, 
 *     topping up the MyTi card, or viewing current credit.
 *     
 * This is an *outline* --- you will need to add menu options and fill in
 *   the implementation details for all the functionality
 *     
 * @author lcavedon
 *
 */

/*
 * Main codes here, run the system
 * @author Xiangyu Wang s3556596
 * Last modified 08/09/2016
 */
public class MyTiSystem {
	// create a new Scanner from standard input
	static Scanner input = new Scanner(System.in);
	
	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<Station> stations = new ArrayList<Station>();
	static ArrayList<MyTiCard> cards = new ArrayList<MyTiCard>();
	private static User user = null;
	/**
	 * main program: this contains the main menu loop
	 */
	public static void main(String[] args) throws MyException,InputMismatchException{
		// CODE HERE:
		// create a collection of the MyTi passes available to the system
		// for this assignment, you may hard-code a number of passes and their ids
		users.add(new User("Lawrence Cavedon","lawrence.cavedon@rmit.edu.au",new MyTiCard("lc")));
		// CODE HERE:
		// create a collection of stations for the system
		// for this assignment, you may hard-code a number of stations and their details
		stations.add(new Station("Central","1"));
		stations.add(new Station("Flagstaff","1"));
		stations.add(new Station("Richmond","1"));
		stations.add(new Station("Lilydale","2"));
		stations.add(new Station("Epping","2"));
		// main menu loop: print menu, then do something depending on selection
		int option = 0;
		do {
			boolean valid = false;
			printMainMenu();
			do{
				try {
					option = input.nextInt();
					if (option > 7 || option < 0) {
						throw new MyException("Please choose 0 to 7");
					}
					else if (option >= 0 && option < 8){
						valid = true;
					}
				}catch (MyException me) {
					System.out.println(me.getMessage());
					printMainMenu();
				}catch (InputMismatchException ime) {
					System.out.println("Invalid input.");
					input.nextLine();
					printMainMenu();
				}
			}while(!valid);
			System.out.println();
			// perform correct action, depending on selection
			switch (option) {
			case 1: purchasePass();
				break;
			case 2: recharge();
				break;
			case 3: showCredit();
				break;
			case 4: purchaseJourney();
				break;
			case 5: printJourney();
				break;
			case 6: showStations();
				break;

			case 0: System.out.println("Goodbye!");  // will quit---do nothing
				break;
			default:
				// if no legal option selected, just go round and choose again
				System.out.println("Invalid option!");
			}
			
		} while (option != 0);// finishing processing ... close the input stream
		input.close();
	}
	
	/*
	 * Print the main menu (you have to modify / add options)
	 */
	static void printMainMenu() {
		System.out.println();
		System.out.println("Select an option:");
		System.out.println("1. Purchase a travel pass");
		System.out.println("2. Recharge your MyTi card");
		System.out.println("3. Show remaining MyTi credit");
		System.out.println("4. Purchase a journey using a MyTi card");
		System.out.println("5. Print all journeys made using all MyTi cards");
		System.out.println("6. Show Station Statistics");
		System.out.println("7. Add a new user");
		System.out.println("0. Quit");
		System.out.print("Your option: ");
	}
	
	/*
	 * Purchase a travel pass using MyTi credit
	 */
	static void purchasePass() throws MyException,InputMismatchException{
		// first, get the MyTi card that we plan to use to purchase Travel Pass
		System.out.println();
		System.out.print("What is the id of the MyTi pass:");
		String id = input.next();
		MyTiCard myticard = null;
		// CODE GOES HERE: look up MyTi card matching this id --- if no match, return null
		for (int i=0; i<users.size(); i++){
			if (users.get(i).getId().compareTo(id) == 0){
				myticard = users.get(i).getCard();
			}
		}
		if (myticard == null) {
			System.out.println("No MyTi Card matching that id was found ...");
			System.out.println("\nPress enter to continue...");
			input.nextLine();
			input.nextLine();
			return;
		}
		// ... else we found a matching MyTi card: continue with purchasing Travel Pass
		// print time options
		boolean valid = false;
		String length;
		do{
			System.out.println();
			System.out.println("How long do you need a pass for:");
			System.out.println("a. 2 Hours");
			System.out.println("b. All Day");
			System.out.println("c. cancel");
			System.out.println("Your selection: ");
			length = input.next();
			if (length .equals("c")) return;// cancel
			try{
				if (length.compareTo("a")!=0 && length.compareTo("b")!=0) {
					throw new MyException("Invalid input, please try again");	
				}
			}catch (MyException e) {
				System.out.println(e.getMessage());
				return;
			}
			do{ 
				try{
					System.out.print("Day of purchase (use abbr. mon/tue etc.): ");
					String day = input.next();
					System.out.print("Time of purchase: ");
					int time = input.nextInt();
					if ((!day.equals("mon") && !day.equals("tue") && !day.equals("wed") && !day.equals("thu") && !day.equals("fri") && !day.equals("sat") && !day.equals("sun")) || (time > 2400 || time%100 >59))
						throw new MyException("Invalid day or time input, please try again.");
					else if (length.compareTo("a")==0){
						myticard.getTravelPass().add(new TravelPass(day,time,"2h"));
						valid = true;
					}else if (length.compareTo("b")==0){
						myticard.getTravelPass().add(new TravelPass(day,time,"ad"));
						valid = true;
					}
				}catch (MyException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException ime) {
					System.out.println("Input format error, please try again.");
					input.nextLine();
					return;
				}
//				else if (length.compareTo("a")==0){
//					myticard.getTravelPass().add(new TravelPass(day,time,"2h"));
//				}else if (length.compareTo("b")==0){
//					myticard.getTravelPass().add(new TravelPass(day,time,"ad"));
//				}
//			myticard.getTravelPass().add(new TravelPass(day,time));
//			valid = true;
			}while(!valid);
		} while (!valid);
		System.out.println();
		System.out.println("For which zones:");
		System.out.println("a. Zone 1");
		System.out.println("b. Zones 1 and 2");
		System.out.println("c. cancel");
		System.out.println("Your selection: ");
		String zones = input.next();
		if (zones.equals("c")) return;    // cancel
		System.out.println();	// first check if valid options were selected
		if ((!length.equals("a") && !length.equals("b"))
				|| (!zones.equals("a") && !zones.equals("b"))) {
			System.out.println("You have selected an illegal option. Please try again.");
			// if not, then re-try purchasing a pass
			purchasePass();
		} else {
			// selected legal options --- purchase a Travel Pass on this MyTi card
			// NOTE: you will need to Check Credit before finalising the purchase!
			//   --- Raise an Exception if there is not enough credit
			try{
				if (length.equals("a") && zones.equals("a")) {
				// CODE HERE: purchase a 2 Hour Zone 1 Travel Pass on this MyKi card;
					if (myticard.getCredit() >= 3.5)
						myticard.setCredit(myticard.getCredit() - 3.5);
					else 
						throw new MyException("Your current credit is not enough, please recharge your MyTi at first.");
				} else if (length.equals("a") && zones.equals("b")) {
				// CODE HERE: purchase a 2 Hour Zone 2 Travel Pass on this MyKi card;
					if (myticard.getCredit() >= 5.0)
						myticard.setCredit(myticard.getCredit() - 5.0);
					else
						throw new MyException("Your current credit is not enough, please recharge your MyTi at first.");
				} else if (length.equals("b") && zones.equals("a")) {
				// CODE HERE: purchase an All Day Zone 1 Travel Pass on this MyKi card;
					if (myticard.getCredit() >= 6.9)
						myticard.setCredit(myticard.getCredit() - 6.9);
					else
						throw new MyException("Your current credit is not enough, please recharge your MyTi at first.");
				} else if (length.equals("b") && zones.equals("b")) {
				// CODE HERE: purchase an All Day Zone 2 Travel Pass on this MyKi card;
					if (myticard.getCredit() >= 9.8)
						myticard.setCredit(myticard.getCredit() - 9.8);
					else
						throw new MyException("Your current credit is not enough, please recharge your MyTi at first.");
				}
			} catch (MyException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Your current credit is: $" + myticard.getCredit());
			System.out.println("\nPress enter to continue...");
			input.nextLine();
			input.nextLine();
		}
	}
	
	/*
	 * Recharge a MyTi card
	 */
	static void recharge() throws MyException,InputMismatchException{
		// CODE HERE: Get MyTi card id from user and find matching MyTiCard 
		System.out.println();
		System.out.println("What is the id of the MyTi pass:");
		String id = input.next();
		MyTiCard myticard = null;
		for (int i=0; i<users.size(); i++){
			if (users.get(i).getId().compareTo(id) == 0){
				myticard = users.get(i).getCard();
			}
		}
		if (myticard == null) {
			System.out.println("No MyTi Card matching that id was found ...");
			System.out.println("\nPress enter to continue...");
			input.nextLine();
			input.nextLine();
			return;
		}
		boolean valid = false;
		do{
//		System.out.print("How much credit do you want to add: ");
//		// read charge amount from input Scanner
//		double amount = input.nextDouble();
		// CODE HERE: add that credit to the MyTiCard 
		// - check that it does not go above max amount (raise Exception if it does)
			try {
				System.out.print("How much credit do you want to add: ");
				double amount = input.nextDouble();
				if (amount <= 0) {
					throw new MyException("Charging amount must be greater than 0.");
				}
				if (amount % 5 !=0 && amount + myticard.getCredit() < myticard.CREDIT_LIMIT){					
					throw new MyException("Charge failed. Invalid amount, your charging amount must be in precise multiples of 5.");
				}
				else if (amount + myticard.getCredit() > myticard.CREDIT_LIMIT) {
					throw new MyException("Charge failed. Your credit should not be more than $100.0, amounts exceeds.");
				}
				else if (amount % 5 ==0 || (amount + myticard.getCredit() <= myticard.CREDIT_LIMIT)){
					myticard.popup(amount);;
					System.out.println();
					System.out.println("Your current credit: $" + myticard.getCredit());
					valid = true;
				}
			} catch (MyException e){
				System.out.println(e.getMessage());
				}
			catch (InputMismatchException ime){
				System.out.println("\nPlease input a number between 0 to 100.");
				input.nextLine(); //clear buffer, otherwise the block will do an infinite loop.
			}
		}while(!valid);
		System.out.println("\nPress enter to continue...");
		input.nextLine();
		input.nextLine();
	}

	/*
	 * Display the remaining credit on MyTi card
	 */
	static void showCredit() {
		// CODE HERE: Get MyTi card id from user and find matching MyTiCard
		System.out.println();
		System.out.println("What is the id of the MyTi pass:");
		String id = input.next();
		MyTiCard myticard = null;
		for (int i=0; i<users.size(); i++){
			if (users.get(i).getId().compareTo(id) == 0){
				myticard = users.get(i).getCard();
			}
		}
		if (myticard == null) {
			System.out.println("No MyTi Card matching that id was found ...");
			return;
		}
		// CODE HERE: Display credit for that MyTiCard
		if (myticard != null) {
			System.out.println("Your current credit is: $" + myticard.getCredit());
		}
		System.out.println("\nPress enter to continue...");
		input.nextLine();
		input.nextLine();
	}
	
	static void purchaseJourney() throws MyException{
		System.out.println();
		System.out.println("What is the id of the MyTi pass:");
		String id = input.next();
		MyTiCard myticard = null;
		for (int i=0; i<users.size(); i++){
			if (users.get(i).getId().compareTo(id) == 0){
				myticard = users.get(i).getCard();
			}
		}
		if (myticard == null) {
			System.out.println("No MyTi Card matching that id was found ...");
			System.out.println("\nPress enter to continue...");
			input.nextLine();
			input.nextLine();
			return;
		}
		boolean test = false;
		do{
			try{
				System.out.print("Please enter your journey day: ");
				String day = input.next();
				System.out.print("Please enter your journey start time: ");
				int time = input.nextInt();
				for (int i=0; i<myticard.getTravelPass().size(); i++){
					if (day.equals(myticard.getTravelPass().get(i).getDay())){
						if ((myticard.getTravelPass().get(i).getType().compareTo("2h")==0) && (myticard.getTravelPass().get(i).getTime()+200 > time)){
							myticard.getTravelPass().get(i).getJourney().add(new Journey(day,time));
							test = true;
						}else if (myticard.getTravelPass().get(i).getType().compareTo("ad")==0){
							myticard.getTravelPass().get(i).getJourney().add(new Journey(day,time));
							test = true;
						}else 
							throw new MyException("Invalid journey start time, please check your current travel pass.");
					}else 
						throw new MyException("You have no valid Travel Pass through this journey period.");
				}
			}catch (MyException e) {
				System.out.println(e.getMessage());
				System.out.println("\nPress enter to continue...");
				input.nextLine();
				input.nextLine();
				return;
			}
		}while(!test);
		System.out.println("\nPress enter to continue...");
		input.nextLine();
		input.nextLine();
	}
	
	/*
	 *  Display all journeys made by all MyTi cards
	 */
	static void printJourney() throws MyException {
		System.out.println("\tUserID\tJourneyDay\tStartTime\tStartStation");
		for (int i=0; i<users.size(); i++) {
//			for(int j=0; j<users.get(i).getCard().getTravelPass().size(); j++)
//				for(int k=0; k<users.get(i).getCard().getTravelPass().get(j).getJourney().size(); k++)
//				System.out.println("\t" + users.get(i).getId() + "\t" + users.get(i).getCard().getTravelPass().get(j).getJourney().get(k).getTime() + "\t");
			user = users.get(i);
			for(int j=0; j<user.getCard().getTravelPass().size(); j++) {
				System.out.print("\t" + user.getId());
				user.getCard().getTravelPass().get(j).showJourney();
			}
		}
		System.out.println("\nPress enter to continue...");
		input.nextLine();
		input.nextLine();
	}
	
	/*
	 *  Display the stations details
	 */
	static void showStations() {
		for (int i=0; i<stations.size(); i++) {
			System.out.println("Station: " + stations.get(i).getName() + ", Zone " + stations.get(i).getZone());
		}
		System.out.println("\nPress enter to continue...");
		input.nextLine();
		input.nextLine();
	}
	
	/*
	 * Check current credit is valid or not to purchase
	 */
	static boolean checkCredit(MyTiCard myticard, double amt) {
		if (myticard.getCredit() >= amt)
			return true;
		else
			return false;
	}
}