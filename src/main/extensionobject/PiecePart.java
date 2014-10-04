package extensionobject;

import javax.xml.parsers.ParserConfigurationException;

public class PiecePart extends Part {

	private String partNumber;
	private String description;
	private double cost;

	public PiecePart(String partNumber, String description, double cost) throws ParserConfigurationException {
		this.partNumber = partNumber;
		this.description = description;
		this.cost = cost;
		super.addExtension("CSV", new CsvPiecePartExtension(this));
		super.addExtension("XML", new XmlPiecePartExtension(this));

	}

	@Override
	public String getPartNumber() {
		return partNumber;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public double getCost() {
		return cost;
	}

}
