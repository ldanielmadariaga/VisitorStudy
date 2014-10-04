package extensionobject;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public abstract class XmlPartExtension implements PartExtension {

	private static Document document;

	public XmlPartExtension() throws ParserConfigurationException {
		document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
	}

	public abstract Element getXmlElement();

	protected Element newElement(String name) {
		return document.createElement(name);
	}

	protected Element newTextElement(String name, String text) {
		Element element = document.createElement(name);
		Text xmlText = document.createTextNode(text);
		element.appendChild(xmlText);
		return element;
	}

}
