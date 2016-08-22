package myti;
import java.util.*;

public class TravelPass {
	private String day;
	private int time;
	private int count = 0;
	private ArrayList<Journey> journey = new ArrayList<Journey>();
	
	public TravelPass(String day, int time){
		this.day = day;
		this.time = time;
	}

	public ArrayList<Journey> getJourney() {
		return journey;
	}
}
