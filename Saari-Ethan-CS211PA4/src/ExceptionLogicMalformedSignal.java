public class ExceptionLogicMalformedSignal extends RuntimeException {
//Fields
	private char bad;
	private String msg;
//Methods
	//public ExceptionLogicMalformedSignal(char bad, String msg) constructor that assigns to each field.
	public ExceptionLogicMalformedSignal(char bad, String msg) {
		this.bad = bad;
		this.msg = msg;
	}
	//@Override public String toString() return your msg.
	@Override public String toString() {
		return this.msg;
	}
	
//Getters and Setters
	//Get the bad
	public char getBad() {
		return bad;
	}
	//Set the bad
	public void setBad(char bad) {
		this.bad = bad;
	}
	//Get the message
	public String getMsg() {
		return msg;
	}
	//Set the message
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
