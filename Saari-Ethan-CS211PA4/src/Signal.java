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
	public static Signal fromString(char c) {
		if(c == '1') {
			return HI;
		}
		else if(c == '0') {
			return LO;
		}
		else if(c == 'X') {
			return X;
		}
		else if(c == 'x') {
			return X;
		}
		else {
			throw new ExceptionLogicMalformedSignal(c, "Invalid character!");
		}
	}
	//public static List<Signal> fromString(String inps) 
		//Create and return the List of Signal values found in the input string.
		//Beyond the per-character functionality of fromString(char), 
		//we also skip any spaces and tabs. You can use an ArrayList
		//as the concrete structure when returning something of the interface type List.
	public static List<Signal> fromString(String inps){
        List<Signal> values = new LinkedList<Signal>();
        if(inps.equals("1")) {
        	values.add(HI);
        }
        else if(inps.equals("0")) {
        	values.add(LO);
        }
        else if(inps.equals("X")) {
            values.add(X);
        }
        else if(inps.equals("x")) {
            values.add(X);
        }
        else {
        	throw new ExceptionLogicMalformedSignal(inps.charAt(0), "Invalid character!");
        }
		return values;
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