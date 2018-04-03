import java.util.List;

public interface Logic {
//Methods
	//public abstract void feed(List<Signal> inSignals) Assigns the given signals to the input wires of this thing. 
		//For gates, the input wires themselves should be assigned these new Signal values, 
		//in the order given in inSignals. For a circuit, these	wires will all be attached to the Contact points
		//found in Circuit::inputs, in the order given (same indexes of both lists are used together).
		//In effect, you're zipping together the inSignals values with the inputs values, and updating all the
		//inputs' signal values with those given by inSignals.
	public abstract void feed(List<Signal> inSignals);
	//public abstract void feed(String inSignals) Same purpose as feed(List<Signal>).
		//We accept a string that can be converted into a List<Signal> value with help from Signal.fromString.
	public abstract void feed(String inSignals);
	//public abstract boolean propagate() Using the current values of our input wires,
		//let all inner components perform their logic and generate their outputs. 
		//Be sure that a single call to propagate will make the entire thing's outputs stabilize. 
		//(The order in	which you visit gates, or how often you (re)visit them might be significant).
	public abstract boolean propagate();
	//public abstract List<Signal> read() Read the signal values on the output wires, 
		//and return them as a List<Signal> value.
	public abstract List<Signal> read();
	//public abstract List<Signal> inspect(List<Signal> inputs). A combination of feeding, propagating, and reading.
		//(These functionalities are all declared here in this interface directly).
	public abstract List<Signal> inspect(List<Signal> inputs);
	//public abstract String inspect(String inputs) also a combination of feeding, propagating, and reading. 
		//Should use the fromString and toString functionality of the Signal type.
	public abstract String inspect(String inputs);
}
