package myti;

public class Journey {
	private String day;
	private int time;
	
	Journey(String day, int time){
		this.day = day;
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public int getTime() {
		return time;
	}
}
