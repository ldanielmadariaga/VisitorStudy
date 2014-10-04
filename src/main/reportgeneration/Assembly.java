package reportgeneration;

import java.util.ArrayList;

public class Assembly implements Part {

	private ArrayList<Part> parts = new ArrayList<Part>();
	private String partNumber;
	private String description;

	public Assembly(String partNumber, String description) {
		this.partNumber = partNumber;
		this.description = description;
	}

	@Override
	public void accept(PartVisitor v) {
		v.visit(this);
		for (Part part : parts) {
			part.accept(v);
		}
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
