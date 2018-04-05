public class ExceptionLogicParameters extends RuntimeException{
//Fields
	private boolean inputsRelated;
	private int expected;
	private int found;
//Methods
	//public ExceptionLogicParameters(boolean inputsRelated, int expected, int found) constructor that assigns to each field. 
	public ExceptionLogicParameters(boolean inputsRelated, int expected, int found) {
		this.setInputsRelated(inputsRelated);
		this.expected = expected;
		this.found = found;
	}
	//@Override public String toString() build up a message of your choosing.
	@Override public String toString() {
		return(expected + " was expected, however, " + found + " was found.");
	}
//Getters and Setters
	//Get the inputsRelated
	public boolean isInputsRelated() {
		return inputsRelated;
	}
	//Set the inputsRelated
	public void setInputsRelated(boolean inputsRelated) {
		this.inputsRelated = inputsRelated;
	}
	public int expected() {
		return expected;
	}
	public void setExpected(int expected) {
		this.expected = expected;
	}
	public int found() {
		return found;
	}
	public void setFound(int found) {
		this.found = found;
	}
}
