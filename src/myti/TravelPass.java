package myti;
import java.util.*;

/*
* Store Travel Pass details
* @author Xiangyu Wang s3556596
* last modified on 05/09/2016
*/

public class TravelPass {
	private String day;
	private int time;
	private String type;
	private ArrayList<Journey> journey = new ArrayList<Journey>();
	
	public TravelPass(String day, int time, String type){
		this.day = day;
		this.time = time;
		this.type = type;
	}

	public ArrayList<Journey> getJourney() {
		return journey;
	}
	
	public String getDay() {
		return day;
	}

	public int getTime() {
		return time;
	}
	
	public String getType() {
		return type;
	}

/*
 * Print each journey by this Travel Pass
 */
	public void showJourney() {
		for(int i=0; i<journey.size(); i++)
		System.out.println("\t" + this.getJourney().get(i).getDay() + "\t\t" + this.getJourney().get(i).getTime());
	}
}
