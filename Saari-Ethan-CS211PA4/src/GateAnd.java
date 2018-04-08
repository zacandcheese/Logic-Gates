import java.util.List;
import java.util.ArrayList;
public class GateAnd extends Gate{
	
	public GateAnd(List<Wire> inputs, Wire output) {
		super("AND", inputs, output);
	}
	// Helper method to create an ArrayList of one thing so that Java's // stupid "super() must be first line" rule can be honored 
	public static <T> ArrayList<T> one(T x){  
		ArrayList<T> a = new ArrayList<T>();  
		a.add(x);  
		return a; 
	}
	@Override public boolean propagate() {
		boolean value = true;
		boolean flag = false;
		boolean xFlag = false;
		Signal signal = Signal.X;
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
			signal = Signal.LO;
			//meaning one was negative
		}
		else if(xFlag) {
			signal = Signal.X;
		}
		else signal = Signal.HI;
		
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
