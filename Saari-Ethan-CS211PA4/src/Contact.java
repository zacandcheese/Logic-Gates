import java.util.List;

public class Contact implements Logic {
//Fields
	private Wire in;
	private Wire out;
	private boolean inbound;
//Methods
	//public Contact(Wire in, Wire out, boolean inbound) the obvious constructor (initialize all fields). 
	public Contact(Wire in, Wire out, boolean inbound) {
		this.in = in;
		this.out = out;
		this.inbound = inbound;
	}
	//@Override public String toString() A Contact will indicate the wire names and the value currently on it.
		//When the two wires of this Contact have the same name, you must construct a string showing the name,
		//A colon, and the signal value that the wires are currently sharing, such as "A:0", "temp:X", "Cout:1".
		//When the two wires (in, out) have different names, you must show the two wire names,
		//and parenthesize the one that is inside the circuit. Then, include a colon and the current signal value. 
	@Override public String toString() {
		if (in.getName().equals(out.getName())){
			return String.format("%s:%s", in.getName(), in.getSignal());
		}
		else{
			return String.format(inbound ? "%s(%s):%s" : "(%s)%s:%s", in.getName(), out.getName(), out.getSignal());
	    }
	}
	//@Override public boolean equals(Object o) equality check that the in and out wires, 
		//as well as inbound, are all equal between the two objects. 
	@Override public boolean equals(Object o) {
		if(this.in.equals(((Contact) o).getIn())) {
			if(this.out.equals(((Contact) o).getOut())) {
				if(this.inbound == ((Contact) o).getInbound()) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void feed(List<Signal> inSignals) {		
	}

	@Override
	public void feed(String inSignals) {		
	}

	@Override
	public boolean propagate() {
		return false;
	}

	@Override
	public List<Signal> read() {
		return null;
	}

	@Override
	public List<Signal> inspect(List<Signal> inputs) {
		return null;
	}

	@Override
	public String inspect(String inputs) {
		return null;
	}
	
//Getters and Setters
	//Gets the in
	public Wire getIn() {
		return in;
	}
	//Sets the in
	public void setIn(Wire in) {
		this.in = in;
	}
	//Gets the out
	public Wire getOut() {
		return out;
	}
	//Sets the out
	public void setOut(Wire out) {
		this.out = out;
	}
	//Gets the inbound
	public boolean getInbound() {
		return inbound;
	}
	//Sets the inbound
	public void setInbound(boolean inbound) {
		this.inbound = inbound;
	}

}
