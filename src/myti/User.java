package myti;

/*
 * Store the users' basic details
 * @author Xiangyu Wang s3556596
 * last modified on 15/08/2016
 */
public class User {
	private String name;
	private String email;
	private String id;
	private MyTiCard card;
	
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
