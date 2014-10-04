package decorator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ModemDecoratorTest {

	@Test
	public void createHayes() {
		Modem modem = new HayesModem();

		assertEquals(null, modem.getPhoneNumber());
		assertEquals(0, modem.getSpeakerVolume());

		modem.dial("5551212");
		modem.setSpeakerVolume(10);

		assertEquals("5551212", modem.getPhoneNumber());
		assertEquals(10, modem.getSpeakerVolume());
	}

	@Test
	public void loudDialModem() {
		Modem modem = new HayesModem();
		Modem loudDialModem = new LoudDialModem(modem);
		assertEquals(null, loudDialModem.getPhoneNumber());
		assertEquals(0, loudDialModem.getSpeakerVolume());

		loudDialModem.dial("5551212");

		assertEquals("5551212", loudDialModem.getPhoneNumber());
		assertEquals(10, loudDialModem.getSpeakerVolume());
	}

}
