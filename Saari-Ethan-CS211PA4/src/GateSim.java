import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GateSim {
	//GateSim.main(new String[]{"and", "11"}); 
	public static void main(String[] args) throws IOException {
		//Circuit dummy = new Circuit("andy");
		//args = new String[] {"and","11"};
		ArrayList<Signal> list = new ArrayList<Signal>();
		
		try {
			Circuit cir1 = new Circuit(args[0]);
			List<Signal> sig1 = Signal.fromString(args[1]);
			cir1.feed(sig1);
			cir1.propagate();
			System.out.println(cir1.getOutputs());
			list.clear();
		}
		catch(FileNotFoundException e){
			System.out.println("You failed");
		}
		catch(ExceptionLogicMalformedSignal e) {
			System.out.println("You failed");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("You failed");
		}
		
		//------------USER FUN-----------
		while(true) {
			try {
				Scanner scnr = new Scanner(System.in);
				System.out.print("Which Circuit: ");
				String file = scnr.nextLine();
				Circuit cir = new Circuit(file);
				System.out.println("Enter "+ cir.getInputs().size() + " inputs");
				System.out.println("--------------\n");
				for(int i=0; i<cir.getInputs().size();i++) {
					System.out.print("Enter Boolean Digit for "+i+" spot: ");
					String ans = scnr.next();
					list.add(Signal.fromString((ans).charAt(0)));//("HI");
				}
				
				cir.feed(list);
				cir.propagate();
			    System.out.println(cir.getOutputs());
			}
			catch(FileNotFoundException e){
				System.out.println("You failed");
			}
			catch(ExceptionLogicMalformedSignal e) {
				System.out.println("You failed");
			}
			list.clear();
		}
	}
	//GateSim.main(new String[]{"and", "11"});
}
