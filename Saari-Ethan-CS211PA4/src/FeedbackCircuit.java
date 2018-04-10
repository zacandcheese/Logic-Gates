import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class FeedbackCircuit extends Circuit{

	public FeedbackCircuit(String circuitName) throws IOException{
		super(circuitName, new ArrayList<Logic>(), new ArrayList<Contact>(), new ArrayList<Contact>(), new ArrayList<Wire>(), new ArrayList<String>()); 
			
		/*first, call the 6-argument parent constructor with all empty lists as arguments. then, 
		 * carry on as with Circuit, being careful to observe the intended sharing of wires, 
		 * wherever they show up.*/
		 /*@Override public boolean propagate(). Re-implement propagation so that we continuously perform propagation acts on all sub-circuits/
		 *gates until no changes are occurring. 
		 *(Let the circuit's signals "settle" to a stable state).*/ 
		

	}
}
