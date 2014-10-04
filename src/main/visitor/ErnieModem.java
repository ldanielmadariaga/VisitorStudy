package visitor;

public class ErnieModem implements Modem {

	public String internalPattern = null;

	@Override
	public void dial(String phoneNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hangUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public char receive() {
		// TODO Auto-generated method stub
		return (char) 0;
	}

	@Override
	public void accept(ModemVisitor modemVisitor) {
		modemVisitor.visit(this);
	}
}
