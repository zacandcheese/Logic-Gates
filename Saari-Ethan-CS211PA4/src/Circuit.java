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
			String[] rightArray = right.split("\\s");
			for(String t: rightArray) {
				Wire w = new Wire(t);
				innerWires.add(w);
				outputs.add(new Contact(w,w,false));
			}
			
	}
	public List<Wire> parseString(String s){
		List<Wire> w = new ArrayList<Wire>();
			if (s.length() > 0){
				String[] stringArray = s.split("\\s");
				for (String t : stringArray){
					w.add(new Wire(t));
				}
		    }
			return w;
	}
	public Wire findWire(String name) {
		for(Wire w: innerWires) {
			if(w.getName().equals(name)) {
				return w;
			}
		}
		return null;
	}
	public void hookUp(List<Wire> inWires, List<Wire> outWires) {
		if(inWires.size()== inputs.size() && outWires.size()==outputs.size()) {
			for(int i=0; i<inputs.size();i++) {
				inputs.get(i).setIn(inWires.get(i));
			}
			for(int i=0; i<outputs.size();i++) {
				outputs.get(i).setOut(outWires.get(i));
			}
		}
		else throw new ExceptionLogicParameters(true,inputs.size(),inWires.size());
	}
	public void parseComponentLine(String line) throws IOException{
		Scanner scnr = new Scanner(line);
		String type = scnr.next();
		
		List<Wire> inWires = new ArrayList<Wire>();
		List<Wire> outWires = new ArrayList<Wire>();
		
		//Lets make Wires
		String newWire = scnr.nextLine().trim();
		String left = "";
		String right = "";
		
		String[] stringArray = newWire.split("\\->");
		left = stringArray[0].trim();
		right = stringArray[1].trim();
		
		inWires = parseString(left);
		outWires = parseString(right);
		
		if(inWires.size()==0) throw new ExceptionLogicParameters(true,1,0);
		if(outWires.size()==0) throw new ExceptionLogicParameters(false,1,0);
		
		//re=use any known wire
		for(int i=0; i<inWires.size();i++) {
			if( findWire(inWires.get(i).getName()) != null) {
				inWires.set(i, findWire(inWires.get(i).getName()));
			}
			else {
				innerWires.add(inWires.get(i));
			}
		}
		for(int i=0; i<inWires.size();i++) {
			if(findWire(outWires.get(i).getName()) != null) {
				outWires.set(i, findWire(outWires.get(i).getName()));
			}
			else {
				innerWires.add(outWires.get(i));
			}
		}
		//subcircuit
		if( importables.contains(type)) {
			Circuit newObject = new Circuit(type);
			newObject.hookUp(inWires, outWires);
			this.components.add(newObject);
		}
		else {
			if(type.equals("NOT")) {
				if(inWires.size()==1) {
					components.add(new GateNot(inWires.get(0), outWires.get(0)));
				}
				else {
					throw new ExceptionLogicParameters(true,1,inWires.size());
				}
			}
			else if(type.equals("AND")) {
				components.add(new GateAnd(inWires, outWires.get(0)));
			}
			else if(type.equals("NAND")) {
				components.add(new GateNand(inWires, outWires.get(0)));
			}
			else if(type.equals("OR")) {
				components.add(new GateOr(inWires, outWires.get(0)));
			}
			else if(type.equals("XOR")) {
				components.add(new GateXor(inWires, outWires.get(0)));
			}
			else if(type.equals("NOR")) {
				components.add(new GateNor(inWires, outWires.get(0)));
			}
		}
	}
	
	@Override
	public void feed(List<Signal> inputs2) {
		if( inputs2.size() == inputs.size()) {
			for(int i=0;i<inputs.size();i++) {
				inputs.get(i).getIn().setSignal(inputs2.get(i));
			}
		}
		else {
			throw new ExceptionLogicParameters(true,inputs.size(), inputs2.size());
		}
	}
	
	@Override
	public void feed(String signalStr) {
		List<Signal> sigList = Signal.fromString(signalStr);
		feed(sigList);
	}
	@Override
	public boolean propagate() {
		boolean flag = false;
		List<Signal> f = read();
		for(Contact c :inputs) {
			c.getOut().setSignal(c.getIn().getSignal());
		}
		for(Logic component:components) {
			component.propagate();
		}
		for(Contact cOut:outputs) {
			if(cOut.getOut().getSignal() != cOut.getIn().getSignal()) {
				cOut.getOut().setSignal(cOut.getIn().getSignal());
			}
		}
		List<Signal> s = read();
		for(int i = 0; i<f.size();i++) {
			if(!f.get(i).equals(s.get(i))) {
				flag = true;
			}
		}
		return flag;
	}
	
	@Override
	public List<Signal> read() {
		List<Signal> list = new ArrayList<Signal>();
		for(Contact sig: outputs) {
			list.add(sig.getOut().getSignal());
		}
		return list;
	}
	@Override public String toString(){
		StringBuilder stringRepr = new StringBuilder();
		stringRepr.append(name+" : "+inputs+" -> "+outputs+"\n");
	    for (Logic comp : components){
	    	stringRepr.append(indent(comp.toString()));
	    }
	    return stringRepr.toString();
	}
	public static String indent(String s){
		String indented = "";
	    String stuff = "";
	    Scanner str = new Scanner(s);
	    while (str.hasNextLine()){
	    	stuff = str.nextLine();
	    	indented += "  " + stuff + "\n";
	    }
	    return indented;
	}
	@Override
	public List<Signal> inspect(List<Signal> inputs) {
		feed(inputs);
		propagate();
		return read();

	}
	@Override
	public String inspect(String inputs) {
		feed(inputs);
		propagate();
		return read().toString();
	}
	//Getters and Setters
	public void setComponents(List<Logic> components) {
		this.components = components;
	}
	public List<Logic> getComponents(){
		return components;
	}
	public void setInputs(List<Contact> inputs) {
		this.inputs = inputs;
	}
	public List<Contact> getInputs(){
		return inputs;
	}
	public void setOutputs(List<Contact> outputs) {
		this.outputs = outputs;
	}
	public List<Contact> getOutputs(){
		return outputs;
	}
	public void setInnerWires(List<Wire> innerWires) {
		this.innerWires = innerWires;
	}
	public List<Wire> getInnerWires(){
		return innerWires;
	}
	public void setImportables(List<String> importables) {
		 this.importables = importables;
	}
	public List<String> getImportables(){
		return importables;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
