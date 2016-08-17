package myti;

public class User {
	private String name;
	private String email;
	private String id;
	private MyTiCard card;
//	private Scanner scan = new Scanner(System.in);
	
	public User(String name, String email, MyTiCard card){
		this.name = name;
		this.email=email;
		this.card=card;
		this.id=this.card.getId();
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public MyTiCard getCard() {
		return card;
	}
}
