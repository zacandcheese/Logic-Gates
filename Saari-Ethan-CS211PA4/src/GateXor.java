import java.util.List;
import java.util.ArrayList;
public class GateXor extends Gate{
	
	public GateXor(List<Wire> inputs, Wire output) {
		super("XOR", inputs,output);
	}
	// Helper method to create an ArrayList of one thing so that Java's // stupid "super() must be first line" rule can be honored 
	public static <T> ArrayList<T> one(T x){  
		ArrayList<T> a = new ArrayList<T>();  
		a.add(x);  
		return a; 
	}
	//HI if exactly one input is high; LO otherwise
	@Override public boolean propagate() {
		boolean value = true;
		boolean flag = false;
		int counter = 0;
		Signal signal = Signal.X;
		for(Wire w: getInputs()) {
			if(w.getSignal() == Signal.HI) {
				flag = true;
				counter++;
			}
			else continue;
		}
		if(flag && counter==1) {
			signal = Signal.HI;
			//meaning one was negative
		}
		else signal = Signal.LO;
		
		//Checks to see if changed signal
		if (getOutput().getSignal().equals(signal)) {
			value = false;
		}
		getOutput().setSignal(signal);
		return value;
	}
	@Override public boolean equals(Object other) {
		GateNot obj = (GateNot)(other);
		if(obj.getInputs()!=this.getInputs()) {
			return false;
		}
		if(obj.getOutput()!=this.getOutput()) {
			return false;
		}
		if(obj.getName()!=this.getName()) {
			return false;
		}
		return true;
	}
}
