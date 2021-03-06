import java.util.List;
import java.util.ArrayList;
public class GateOr extends Gate{
	
	public GateOr(List<Wire> inputs, Wire output) {
		super("OR", inputs, output);
	}
	// Helper method to create an ArrayList of one thing so that Java's // stupid "super() must be first line" rule can be honored 
	public static <T> ArrayList<T> one(T x){  
		ArrayList<T> a = new ArrayList<T>();  
		a.add(x);  
		return a; 
	}
	//Hi if any inputs are high;LO otherwise
	@Override public boolean propagate() {
		Signal signal = Signal.LO;
		boolean value = true;
		boolean flag = false;
		boolean xFlag = false;
		for(Wire w: getInputs()) {
			if(w.getSignal() == Signal.HI) {
				flag = true;
				
			}
			else if(w.getSignal() == Signal.X) {
				xFlag = true;
				
			}
			else continue;
		}
		
		if(flag) {
			signal = Signal.HI;
			//meaning one was negative
		}
		else if(xFlag){
			signal = Signal.X;
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
