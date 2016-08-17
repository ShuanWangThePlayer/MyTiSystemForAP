package myti;

public class MyException extends Exception{
	public MyException(String err) {
		super(err);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
