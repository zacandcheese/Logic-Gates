import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GateSim {
	 
	public static void main(String[] args) throws IOException {
		//Circuit dummy = new Circuit("andy");
		ArrayList<Signal> list = new ArrayList<Signal>();
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
}
