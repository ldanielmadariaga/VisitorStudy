package extensionobject;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;

public class XmlPiecePartExtension extends XmlPartExtension {

	private PiecePart piecePart;

	public XmlPiecePartExtension(PiecePart piecePart) throws ParserConfigurationException {
		this.piecePart = piecePart;
	}

	@Override
	public Element getXmlElement() {
		Element element = super.newElement("PiecePart");
		element.appendChild(super.newTextElement("PartNumber", piecePart.getPartNumber()));
		element.appendChild(super.newTextElement("Description", piecePart.getDescription()));
		element.appendChild(super.newTextElement("Cost", String.valueOf(piecePart.getCost())));

		return element;
	}
}
