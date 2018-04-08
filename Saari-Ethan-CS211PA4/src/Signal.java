import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public enum Signal {
//Values
	HI, LO, X;
//Methods
	//public Signal invert() returns the inversion of this signal. 
		//HI and LO return each other, and X returns itself.
	public Signal invert(){
		if(this.equals(HI)) {
			return LO;
		}
		else if(this.equals(LO)) {
			return HI;
		}
		else if(this.equals(X)){
			return X;
		}
		return this;
	}
	//public static Signal fromString(char c).
		//Given a single character, selects and returns a Signal representation.
	public static Signal fromString(char c) throws RuntimeException {
		Signal signal = X;
		if(c == '1') {
			signal = HI;
		}
		else if(c == '0') {
			signal = LO;
		}
		else if(c == 'X') {
			signal = X;
		}
		else if(c == 'x') {
			signal = X;
		}
		else {
			throw new ExceptionLogicMalformedSignal(c, "Invalid character!");
		}
		return signal;
	}
	//public static List<Signal> fromString(String inps) 
		//Create and return the List of Signal values found in the input string.
		//Beyond the per-character functionality of fromString(char), 
		//we also skip any spaces and tabs. You can use an ArrayList
		//as the concrete structure when returning something of the interface type List.
	 public static List<Signal> fromString(String inps){
		 List<Signal> list = new ArrayList<Signal> ();
		 String charactersOnly = inps.replaceAll("\\s","");
		 for (char c : charactersOnly.toCharArray()){
			 list.add(Signal.fromString(c));
		 }
		 return list;
	 }
	//@Override public String toString() HI returns "1". 
		//LO returns "0". X returns "X".
	@Override public String toString(){
		if(this == HI) {
			return "1";	
		}
		else if(this == LO) {
			return "0";
		}
		else if(this == X) {
			return "X";
		}
		return "Error";
	}
	//public static String toString(List<Signal> sig) converts each signal in the List via toString,
		//concatenates them into a single string, and returns it. 
		//Note that we are overloading toString with a static version that accepts arguments;
		//this has nothing to do with the inherited (and overridden) version above, 
		//but serves a similar enough purpose that it has been named toString also.
	public static String toString(List<Signal> sig) {
		String stuff = "";
		for (Signal s : sig) {
			stuff += s.toString();
		}
		return stuff;
	}
}