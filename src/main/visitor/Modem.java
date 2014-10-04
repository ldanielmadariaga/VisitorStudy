package visitor;

public interface Modem {

	public void dial(String phoneNumber);

	public void send(char c);

	public void hangUp();

	public char receive();

	public void accept(ModemVisitor modemVisitor);
}
