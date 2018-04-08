import java.util.*;
import java.io.*;

public class GateSim {
	public static void main(String[] args) throws IOException {
		//Circuit dummy = new Circuit("andy");
		String circuitName = "andy";
		File circuit = new File("samples/"+circuitName+".txt");
		Scanner scnr = new Scanner(circuit);
		System.out.print(circuit.exists());
	}
}
