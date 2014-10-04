package reportgeneration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class BOMReportTest {

	private PiecePart piecePart1;
	private PiecePart piecePart2;
	private Assembly assembly;

	@Before
	public void setUp() {
		piecePart1 = new PiecePart("997624", "MyPart", 3.20);
		piecePart2 = new PiecePart("7734", "Hell", 666);
		assembly = new Assembly("5879", "MyAssembly");
	}

	@Test
	public void createPart() {

		assertEquals("997624", piecePart1.getPartNumber());
		assertEquals("7734", piecePart2.getPartNumber());
		assertEquals(3.20, piecePart1.getCost(), 0.1);
	}

	@Test
	public void createAssembly() {

		assertEquals("5879", assembly.getPartNumber());
		assertEquals("MyAssembly", assembly.getDescription());
	}

	@Test
	public void assembly() {
		assembly.add(piecePart1);
		assembly.add(piecePart2);
		assertEquals(2, assembly.getParts().size());
		PiecePart piecePart = (PiecePart) assembly.getParts().get(0);
		assertEquals(piecePart, piecePart1);
		piecePart = (PiecePart) assembly.getParts().get(1);
		assertEquals(piecePart, piecePart2);

	}

	@Test
	public void assemblyOfAssemblies() {
		Assembly subAssembly = new Assembly("1234", "SubAssembly");
		subAssembly.add(piecePart1);
		assembly.add(subAssembly);
		assertEquals(subAssembly, assembly.getParts().get(0));
	}

	private class TestingVisitor implements PartVisitor {

		public ArrayList<Part> visitedParts = new ArrayList<Part>();

		@Override
		public void visit(Assembly assembly) {
			visitedParts.add(assembly);
		}

		@Override
		public void visit(PiecePart piecePart) {
			visitedParts.add(piecePart);
		}
	}

	@Test
	public void visitorCoverage() {
		assembly.add(piecePart1);
		assembly.add(piecePart2);

		TestingVisitor visitor = new TestingVisitor();
		assembly.accept(visitor);

		assertTrue(visitor.visitedParts.contains(piecePart1));
		assertTrue(visitor.visitedParts.contains(piecePart2));
		assertTrue(visitor.visitedParts.contains(assembly));

	}

	private Assembly cellphone;

	private void setUpReportDatabase() {
		cellphone = new Assembly("CP-7734", "Cell Phone");
		PiecePart display = new PiecePart("DS-1428", "LCD Display", 14.37);
		PiecePart speaker = new PiecePart("SP-92", "Speaker", 3.50);
		PiecePart microphone = new PiecePart("MC-28", "Microphone", 5.30);
		PiecePart cellRadio = new PiecePart("CR-56", "Cell Radio", 30);
		PiecePart frontCover = new PiecePart("FC-77", "Front Cover", 1.4);
		PiecePart backCover = new PiecePart("RC-77", "RearCover", 1.2);
		Assembly keypad = new Assembly("KP-62", "Keypad");
		Assembly button = new Assembly("B52", "Button");
		PiecePart buttonCover = new PiecePart("CV-15", "Cover", .5);
		PiecePart buttonContact = new PiecePart("CN-2", "Contact", 1.2);
		button.add(buttonCover);
		button.add(buttonContact);
		for (int i = 0; i < 15; i++)
			keypad.add(button);
		cellphone.add(display);
		cellphone.add(speaker);
		cellphone.add(microphone);
		cellphone.add(cellRadio);
		cellphone.add(frontCover);
		cellphone.add(backCover);
		cellphone.add(keypad);
	}

	@Test
	public void explodedCost() {
		setUpReportDatabase();
		ExplodedCostVisitor visitor = new ExplodedCostVisitor();
		cellphone.accept(visitor);
		assertEquals(81.27, visitor.getCost(), 0.001);
	}

	@Test
	public void partCount() {
		setUpReportDatabase();
		PartCountVisitor visitor = new PartCountVisitor();
		cellphone.accept(visitor);
		assertEquals(36, visitor.getPieceCount());
		assertEquals(8, visitor.getPartNumberCount());
		assertEquals("DS-1428", 1, visitor.getCountForPart("DS-1428"));
		assertEquals("SP-92", 1, visitor.getCountForPart("SP-92"));
		assertEquals("MC-28", 1, visitor.getCountForPart("MC-28"));
		assertEquals("CR-56", 1, visitor.getCountForPart("CR-56"));
		assertEquals("RC-77", 1, visitor.getCountForPart("RC-77"));
		assertEquals("CV-15", 15, visitor.getCountForPart("CV-15"));
		assertEquals("CN-2", 15, visitor.getCountForPart("CN-2"));
		assertEquals("Bob", 0, visitor.getCountForPart("Bob"));

	}

}
