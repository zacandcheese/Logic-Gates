import java.util.ArrayList;
import java.util.List;
public abstract class Gate implements Logic {
//Fields
	//The list of (one or more) input wires.
	private List<Wire> inputs;
	//The solitary output wire.
	private Wire output;
	//The name of this kind of gate in all caps, such as "AND", "OR", etc.
	private String name;
//Methods
	//public Gate(String name, List<Wire> ins, Wire out) the obvious constructor (instantiate all fields).
	public Gate(String name, List<Wire> ins, Wire out) {
		this.name = name;
		this.output = out;
		if(ins.size() > 0) {
			this.inputs = ins;
		}
		else {
			throw new ExceptionLogicParameters (true,1,0);
		}
	}
	//@Override public void feed(List<Signal> inSigs) feed these signals to the input wires.
	@Override public void feed(List<Signal> inSigs) {
		if (inSigs.size() == inputs.size()){
			for (int i=0; i < inputs.size(); i++){
				Wire w = inputs.get(i);
		        w.setSignal(inSigs.get(i));
		        inputs.set(i, w);
			}
		}
		else {
			throw new ExceptionLogicParameters(true, inputs.size(),inSigs.size());
		}
	}
	//@Override public void feed(String signalsStr). Same notion, but obtain the Signal values
		//out of this string via Signal.fromString.
	@Override public void feed(String signalsStr) {
	    List<Signal> inSigs = Signal.fromString(signalsStr);
	    feed(inSigs);
	}
	//@Override public List<Signal> read() read the single signal value from the output wire, 
		//and return it as a single value in a List. (It requires that a list be returned
		//because we got this method from the Logic interface, and that will be used by more than just one-output gates).
	@Override public List<Signal> read(){
		List<Signal> list = new ArrayList<Signal>();
		list.add(output.getSignal());
		return list;
	}
	//@Override public List<Signal> inspect(List<Signal> inSigs). Combine the Logic methods into a convenient single call:
		//feed the given inSigs, propagate them through, and return the results of read.
	@Override public List<Signal> inspect(List<Signal> inSigs){
		feed(inSigs);
		propagate();
		return read();
	}
	//@Override public String inspect(String inStr). Identical to the previous inspect() method
		//but pass a String of signals in rather than a list.
	@Override public String inspect(String inStr) {
		feed(inStr);
		propagate();
		return read().toString();
	}
	//@Override public String toString() Include the name and then use the List definition's 
		//own toString implementations to include the inputs and output values, such as this 
		//And example from halfadder's definition. Note where the four required spaces are, 
		//but others may arise from converting a List to a string.
	@Override public String toString() {
		return String.format("%s( %s | %s )", name, inputs, output);
	}
	//@Override public boolean equals(Object other). Performs an equality check, ensuring that the inputs,
		//output, and name are the same values.
	@Override public boolean equals(Object other) {
		if(this.inputs.equals(((Gate) other).getInputs())) {
			if(this.output.equals(((Gate) other).getOutput())) {
				if(this.name.equals(((Gate) other).getName())) {
					return true;
				}
			}
		}
		return false;
	}
//Getters and Setters
	//Gets the inputs
	public List<Wire> getInputs(){
		return this.inputs;
	}
	//Sets the inputs
	public void setInputs(List<Wire> inputs) {
		this.inputs = inputs;
	}
	//Gets the output
	public Wire getOutput() {
		return this.output;
	}
	//Sets the output
	public void setOutput(Wire output) {
		this.output = output;
	}
	//Gets the name
	public String getName() {
		return this.name;
	}
	//Sets the name
	public void setName(String name) {
		this.name = name;
	}
}
