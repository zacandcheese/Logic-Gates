import java.util.*;
import java.io.*;
public class FeedbackCircuit extends Circuit{
	public FeedbackCircuit(String circuitName) throws IOException{
		super(circuitName);
		
	}
	@Override
	public boolean propagate() {return false;}
}
