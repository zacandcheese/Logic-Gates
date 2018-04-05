import java.util.List;
import java.util.ArrayList;
public class GateNot extends Gate{
	
	public GateNot(Wire inputs, Wire output) {
		super("NOT",one(inputs),output);
	}
	// Helper method to create an ArrayList of one thing so that Java's // stupid "super() must be first line" rule can be honored 
	public static <T> ArrayList<T> one(T x){  
		ArrayList<T> a = new ArrayList<T>();  
		a.add(x);  
		return a; 
	}
	@Override public boolean propagate() {
		boolean value = true;
		Wire newWire = getInputs().get(0);
		Signal signal = newWire.getSignal();
		signal = signal.invert();
		if (getOutput().getSignal().equals(signal)) {
			value = false;
		}
		getOutput().setSignal(signal);
		return value;
	}
}
