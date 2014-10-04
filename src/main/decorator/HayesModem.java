package decorator;

public class HayesModem implements Modem {

	private String phoneNumber;
	private int speakerVolume;

	@Override
	public void dial(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public int getSpeakerVolume() {
		return speakerVolume;
	}

	@Override
	public void setSpeakerVolume(int speakerVolume) {
		this.speakerVolume = speakerVolume;
	}

}
