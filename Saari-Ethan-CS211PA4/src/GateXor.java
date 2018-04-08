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
		int counter = 0;
		int xCounter = 0;
		boolean xFlag = false;
		Signal signal = Signal.X;
		for(Wire w: getInputs()) {
			if(w.getSignal() == Signal.HI) {
				counter++;
			}
			else if(w.getSignal() == Signal.X){
				xFlag = true;
			}
			else continue;
		}
		if(counter == 1 && xFlag == false) {
			signal = Signal.HI;
			//meaning one was negative
		}
		else if(xFlag) {
			signal = Signal.X;
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
