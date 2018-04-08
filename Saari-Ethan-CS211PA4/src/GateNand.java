import java.util.List;
import java.util.ArrayList;
public class GateNand extends Gate{
	
	public GateNand(List<Wire> inputs, Wire output) {
		super("NAND", inputs,output);
	}
	// Helper method to create an ArrayList of one thing so that Java's // stupid "super() must be first line" rule can be honored 
	public static <T> ArrayList<T> one(T x){  
		ArrayList<T> a = new ArrayList<T>();  
		a.add(x);  
		return a; 
	}
	//Lo if all inputs are high; HI otherwise
	@Override public boolean propagate() {
		boolean value = true;
		boolean xFlag = false;
		boolean flag = false;
		Signal signal = Signal.HI;
		for(Wire w: getInputs()) {
			if(w.getSignal() == Signal.LO) {
				flag = true;
			}
			else if(w.getSignal() == Signal.X) {
				xFlag = true;
			}
			else;
		}
		if(flag) {
			signal = Signal.HI;
			//flipped
		}
		else if(xFlag) {
			signal = Signal.X;
		}
		else {
			signal = Signal.LO;
		}
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
