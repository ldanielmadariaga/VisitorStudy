package acyclicvisitor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import acyclicvisitor.ErnieModem;
import acyclicvisitor.HayesModem;
import acyclicvisitor.UnixModemConfiguration;
import acyclicvisitor.ZoomModem;

public class ModemAcyclicVisitorTest {

	private UnixModemConfiguration unixModemConfiguration;
	private HayesModem hayesModem;
	private ZoomModem zoomModem;
	private ErnieModem ernieModem;

	@Before
	public void setUp() {
		unixModemConfiguration = new UnixModemConfiguration();
		hayesModem = new HayesModem();
		zoomModem = new ZoomModem();
		ernieModem = new ErnieModem();

	}

	@Test
	public void HayesForUnix() {
		hayesModem.accept(unixModemConfiguration);
		assertEquals("&bla1", hayesModem.configurationString);
	}

	@Test
	public void ZoomForUnix() {
		zoomModem.accept(unixModemConfiguration);
		assertEquals(42, zoomModem.configurationValue);
	}

	@Test
	public void ErnieForUnix() {
		ernieModem.accept(unixModemConfiguration);
		assertEquals("C is too slow", ernieModem.internalPattern);
	}
}
