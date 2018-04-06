import java.util.*;
import java.io.*;

public class Circuit implements Logic{
	private List<Logic> components;
	private List<Contact> inputs;
	private List<Contact> outputs;
	private List<Wire> innerWires;
	private List<String> importables;
	private String name;
	
	public Circuit(String circuitName, List<Logic> components, List<Contact> inputs, List<Contact> outputs, List<Wire> innerWires, List<String> importables) {
		this.components = components;
		this.inputs = inputs;
		this.outputs = outputs;
		this.innerWires = innerWires;
		this.importables = importables;
		this.name = circuitName;
	}
	public Circuit (String circuitName) throws IOException {
		components = new ArrayList<Logic>();
		this.name = circuitName;
		//get scanner
		Scanner scnr = getCircuitScanner(circuitName);
		//read through doc
		List<String> doc = new ArrayList<String>();
		while(scnr.hasNextLine()) {
			doc.add(scnr.nextLine());
		}
		//Check for imports
		String[] firstLine = doc.get(0).split("\\s");
		//If True parse it
		if(firstLine[0].equals("IMPORT")) {
			parseImportLine(doc.get(0));
			parseContactsLine(doc.get(1));
			for(int i=2; i<doc.size(); i++) {
				parseComponentLine(doc.get(i));
			}
		}
		else {
			//Noting to import
			importables = new ArrayList<String>(0);
			parseContactsLine(doc.get(0));
			for(int i = 1; i<doc.size(); i++) {
				parseComponentLine(doc.get(i));
			}
		}
	}
	public Scanner getCircuitScanner (String circuitName) throws IOException{
			File circuit = new File("samples/"+circuitName+".txt");
			Scanner scnr = new Scanner(circuit);
			return scnr;
	}
	public void parseImportLine(String line) {
			String string = "";
			importables = new ArrayList<String>();
			String[] l = line.split("\\s");
			for( int i = 1; i<l.length;i++) {
				importables.add(l[i]);
			}
		}
	public void parseContactsLine (String line) {
			inputs = new ArrayList<Contact>();
			outputs = new ArrayList<Contact>();
			innerWires = new ArrayList<Wire>();
			String left = "";
			String right = "";
			String[] seperated = line.split("\\->");
			left = seperated[0].trim();
			right = seperated[1].trim();
			
			//left
			String[] leftArray = left.split("\\s");
			for(String t: leftArray) {
				Wire w = new Wire(t);
				innerWires.add(w);
				inputs.add(new Contact(w,w,true));
			}
			//right
			String[] righttArray = right.split("\\s");
			for(String t: rightArray) {
				Wire w = new Wire(t);
				innerWires.add(w);
				outputs.add(new Contact(w,w,false));
			}
			
	}
	
		
	
}
