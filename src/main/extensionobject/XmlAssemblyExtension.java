package extensionobject;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;

public class XmlAssemblyExtension extends XmlPartExtension {

	private Assembly assembly;

	public XmlAssemblyExtension(Assembly assembly) throws ParserConfigurationException {
		this.assembly = assembly;
	}

	@Override
	public Element getXmlElement() {
		Element element = super.newElement("Assembly");
		element.appendChild(super.newTextElement("PartNumber", assembly.getPartNumber()));
		element.appendChild(super.newTextElement("Description", assembly.getDescription()));

		Element parts = super.newElement("Parts");
		for (Part part : assembly.getParts()) {
			XmlPartExtension xpe = (XmlPartExtension) part.getExtension("XML");
			parts.appendChild(xpe.getXmlElement());
		}

		element.appendChild(parts);

		return element;
	}
}
