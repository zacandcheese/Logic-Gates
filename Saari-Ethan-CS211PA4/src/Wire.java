
public class Wire {
//Fields
	private Signal signal;
	private String name;
//Methods
	//public Wire(String name) Uses X ("unknown") as the starting Signal value,
		//and also initializes the name.
	public Wire(String name) {
		this.signal = Signal.X;
		this.name = name;
	}
	//@Override public String toString() Create and return the string of 
		//name, colon, signal value. Use the toString functionality of signals appropriately.
	@Override public String toString() {
		return this.name + ":" + this.signal;
	}
	//@Override public boolean equals(Object other). Performs an equality check, 
		//ensuring that the name and signal both match.
	@Override public boolean equals(Object other) {
		if(this.signal.equals(((Wire)other).getSignal())) {
			if(this.name.equals(((Wire)other).getName())) {
				return true;
			}
		}
		return false;
	}
//Getters and Setters
	//Gets the signal
	public Signal getSignal() {
		return signal;
	}
	//Sets the signal
	public void setSignal(Signal signal) {
		this.signal = signal;
	}
	//Gets the name
	public String getName() {
		return name;
	}
	//Sets the name
	public void setName(String name) {
		this.name = name;
	}
}
