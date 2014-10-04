package decorator;

public abstract class ModemDecorator implements Modem {

	private Modem itsModem;

	public ModemDecorator(Modem modem) {
		this.itsModem = modem;
	}

	@Override
	public void dial(String phoneNumber) {
		itsModem.dial(phoneNumber);
	}

	@Override
	public String getPhoneNumber() {
		return itsModem.getPhoneNumber();
	}

	@Override
	public int getSpeakerVolume() {
		return itsModem.getSpeakerVolume();
	}

	@Override
	public void setSpeakerVolume(int speakerVolume) {
		itsModem.setSpeakerVolume(speakerVolume);
	}
}
