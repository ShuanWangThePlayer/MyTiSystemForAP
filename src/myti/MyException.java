package myti;

/*
 * Extends the exception as a new exception that catch any error by user.
 * @author Xiangyu Wang s3556596
 * Last modified on 16/08/2016
 */
public class MyException extends Exception{

	public MyException(String err) {
		super(err);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
