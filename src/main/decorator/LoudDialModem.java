package decorator;

public class LoudDialModem extends ModemDecorator {

	private Modem itsModem;

	public LoudDialModem(Modem modem) {
		super(modem);
		this.itsModem = modem;
	}

	@Override
	public void dial(String phoneNumber) {
		itsModem.setSpeakerVolume(10);
		itsModem.dial(phoneNumber);
	}
}
