package extensionobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BomXmlTest {

	private PiecePart p1;
	private PiecePart p2;
	private Assembly assembly;

	@Before
	public void setUp() throws ParserConfigurationException {

		p1 = new PiecePart("997624", "MyPart", 3.20);
		p2 = new PiecePart("7734", "Hell", 666);
		assembly = new Assembly("5879", "MyAssembly");
	}

	@Test
	public void createPart() {
		assertEquals("997624", p1.getPartNumber());
		assertEquals("MyPart", p1.getDescription());
		assertEquals(3.20, p1.getCost(), 0.01);
	}

	@Test
	public void Assembly() {
		assembly.add(p1);
		assembly.add(p2);
		assertEquals(2, assembly.getParts().size());
		assertEquals(assembly.getParts().get(0), p1);
		assertEquals(assembly.getParts().get(1), p2);
	}

	@Test
	public void assemblyOfAssemblies() throws ParserConfigurationException {
		Assembly subAssembly = new Assembly("1324", "SubAssembly");
		subAssembly.add(p1);
		assembly.add(subAssembly);
		assertEquals(subAssembly, assembly.getParts().get(0));
	}

	private String childText(Element element, String childName) {
		return child(element, childName).getTextContent();
	}

	private Element child(Element element, String childName) {
		NodeList children = element.getElementsByTagName(childName);
		return (Element) children.item(0);
	}

	@Test
	public void piecePart1XML() {
		PartExtension e = p1.getExtension("XML");
		XmlPartExtension xe = (XmlPartExtension) e;
		Element xml = xe.getXmlElement();

		assertEquals("PiecePart", xml.getNodeName());
		assertEquals("997624", childText(xml, "PartNumber"));
		assertEquals("MyPart", childText(xml, "Description"));
		assertEquals(3.2, Double.parseDouble(childText(xml, "Cost")), 0.01);
	}

	@Test
	public void piecePart2XML() {
		PartExtension e = p2.getExtension("XML");
		XmlPartExtension xe = (XmlPartExtension) e;
		Element xml = xe.getXmlElement();

		assertEquals("PiecePart", xml.getNodeName());
		assertEquals("7734", childText(xml, "PartNumber"));
		assertEquals("Hell", childText(xml, "Description"));
		assertEquals(666, Double.parseDouble(childText(xml, "Cost")), 0.01);
	}

	@Test
	public void SimpleAssemblyXml() {
		PartExtension e = assembly.getExtension("XML");
		XmlPartExtension xe = (XmlPartExtension) e;
		Element xml = xe.getXmlElement();

		assertEquals("Assembly", xml.getNodeName());
		assertEquals("5879", childText(xml, "PartNumber"));
		assertEquals("MyAssembly", childText(xml, "Description"));

		Element parts = child(xml, "Parts");
		NodeList partList = parts.getChildNodes();

		assertEquals(0, partList.getLength());
	}

	@Test
	public void assemblyWithPartsXml() {
		assembly.add(p1);
		assembly.add(p2);

		PartExtension e = assembly.getExtension("XML");
		XmlPartExtension xe = (XmlPartExtension) e;
		Element xml = xe.getXmlElement();

		assertEquals("Assembly", xml.getNodeName());
		assertEquals("5879", childText(xml, "PartNumber"));
		assertEquals("MyAssembly", childText(xml, "Description"));

		Element parts = child(xml, "Parts");
		NodeList partList = parts.getChildNodes();
		assertEquals(2, partList.getLength());

		Element partElement = (Element) partList.item(0);
		assertEquals("PiecePart", partElement.getNodeName());
		assertEquals("997624", childText(partElement, "PartNumber"));
		assertEquals("MyPart", childText(partElement, "Description"));
		assertEquals(3.2, Double.parseDouble(childText(partElement, "Cost")), 0.01);

		partElement = (Element) partList.item(1);
		assertEquals("PiecePart", partElement.getNodeName());
		assertEquals("7734", childText(partElement, "PartNumber"));
		assertEquals("Hell", childText(partElement, "Description"));
		assertEquals(666, Double.parseDouble(childText(partElement, "Cost")), 0.01);
	}

	@Test
	public void piecePart1ToCSV() {
		PartExtension extension = p1.getExtension("CSV");
		CsvPartExtension ce = (CsvPartExtension) extension;
		String csv = ce.getCsvText();
		assertEquals("PiecePart,997624,MyPart,3.2", csv);
	}

	@Test
	public void piecePart2ToCSV() {
		PartExtension extension = p2.getExtension("CSV");
		CsvPartExtension ce = (CsvPartExtension) extension;
		String csv = ce.getCsvText();
		assertEquals("PiecePart,7734,Hell,666", csv);
	}

	@Test
	public void simpleAssemblyCSV() {
		PartExtension extension = assembly.getExtension("CSV");
		CsvPartExtension ce = (CsvPartExtension) extension;
		String csv = ce.getCsvText();
		assertEquals("Assembly,5879,MyAssembly", csv);
	}

	@Test
	public void assemblyWithPartsCSV() {
		assembly.add(p1);
		assembly.add(p2);
		PartExtension extension = assembly.getExtension("CSV");
		CsvPartExtension ce = (CsvPartExtension) extension;
		String csv = ce.getCsvText();
		assertEquals("Assembly,5879,MyAssembly,{PiecePart,997624,MyPart,3.2},{PiecePart,7734,Hell,666}", csv);
	}

	@Test
	public void badExtension() {
		PartExtension pe = p1.getExtension("NoExtension");
		assertTrue(pe instanceof BadPartExtension);
	}
}
