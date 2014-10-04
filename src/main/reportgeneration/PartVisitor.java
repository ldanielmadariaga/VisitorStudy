package reportgeneration;

public interface PartVisitor {

	void visit(Assembly assembly);

	void visit(PiecePart piecePart);

}
