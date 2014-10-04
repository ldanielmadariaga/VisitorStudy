package extensionobject;

import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class Assembly extends Part {

	private ArrayList<Part> parts = new ArrayList<Part>();
	private String partNumber;
	private String description;

	public Assembly(String partNumber, String description) throws ParserConfigurationException {
		this.partNumber = partNumber;
		this.description = description;
		super.addExtension("CSV", new CsvAssemblyExtension(this));
		super.addExtension("XML", new XmlAssemblyExtension(this));
	}

	public void add(Part part) {
		parts.add(part);
	}

	public ArrayList<Part> getParts() {
		return parts;
	}

	@Override
	public String getPartNumber() {
		return partNumber;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
