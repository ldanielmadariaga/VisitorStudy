package visitor;

public class UnixModemConfiguration implements ModemVisitor {

	@Override
	public void visit(HayesModem modem) {
		modem.configurationString = "&bla1";
	}

	@Override
	public void visit(ZoomModem modem) {
		modem.configurationValue = 42;
	}

	@Override
	public void visit(ErnieModem modem) {
		modem.internalPattern = "C is too slow";
	}

}
