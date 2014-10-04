package visitor;

public class HayesModem implements Modem {

	@Override
	public void hangUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public char receive() {
		return (char) 0;
	}

	@Override
	public void dial(String phoneNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accept(ModemVisitor modemVisitor) {
		modemVisitor.visit(this);
	}

	public String configurationString = null;

}
