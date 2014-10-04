package reportgeneration;

public class ExplodedCostVisitor implements PartVisitor {

	private double cost = 0;

	public double getCost() {
		return cost;
	}

	@Override
	public void visit(Assembly assembly) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PiecePart piecePart) {
		cost += piecePart.getCost();
	}

}
