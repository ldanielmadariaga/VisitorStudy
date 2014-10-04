package extensionobject;

import java.text.DecimalFormat;

public class CsvPiecePartExtension implements CsvPartExtension {

	private PiecePart piecePart;

	public CsvPiecePartExtension(PiecePart piecePart) {
		this.piecePart = piecePart;
	}

	public String getCsvText() {
		StringBuilder builder = new StringBuilder("PiecePart,");
		builder.append(piecePart.getPartNumber());
		builder.append(",");
		builder.append(piecePart.getDescription());
		builder.append(",");
		DecimalFormat format = new DecimalFormat("0.#");
		String formatedDouble = format.format(piecePart.getCost());
		builder.append(formatedDouble);
		return builder.toString();
	}
}
