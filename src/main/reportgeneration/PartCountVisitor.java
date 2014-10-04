package reportgeneration;

import java.util.Hashtable;

public class PartCountVisitor implements PartVisitor {

	private int pieceCount = 0;
	private Hashtable<String, Integer> pieceHashtable = new Hashtable<String, Integer>();

	@Override
	public void visit(Assembly assembly) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PiecePart piecePart) {
		pieceCount++;
		String partNumber = piecePart.getPartNumber();
		int partNumberCount = 0;
		if (pieceHashtable.containsKey(partNumber)) {
			partNumberCount = pieceHashtable.get(partNumber);
		}

		partNumberCount++;
		pieceHashtable.put(partNumber, partNumberCount);
	}

	public int getPieceCount() {
		return pieceCount;
	}

	public int getPartNumberCount() {
		return pieceHashtable.size();
	}

	public int getCountForPart(String partNumber) {
		int partNumberCount = 0;
		if (pieceHashtable.containsKey(partNumber)) {
			partNumberCount = pieceHashtable.get(partNumber);
		}

		return partNumberCount;

	}
}
