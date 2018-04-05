import java.util.List;
public class GateNot extends Gate{
	
	public GateNot(Wire inputs, Wire output) {
		super("NOT",inputs,output);
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
