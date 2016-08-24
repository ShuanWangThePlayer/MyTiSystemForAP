package myti;
import java.util.*;

public class TravelPass {
	private String day;
	private int time;
	private ArrayList<Journey> journey = new ArrayList<Journey>();
	
	public TravelPass(String day, int time){
		this.day = day;
		this.time = time;
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

	public void showJourney() {
		for(int i=0; i<journey.size(); i++)
		System.out.println("\t" + this.getJourney().get(i).getDay() + "\t\t" + this.getJourney().get(i).getTime());
	}
}
