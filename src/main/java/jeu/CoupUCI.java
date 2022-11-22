package jeu;

public class CoupUCI {
	int xDepart, yDepart, xArrivee, yArrivee;
	String promotedPiece; // q for Queen, n for Knight, b for Bishop, R for Rook (if promotion)

	public CoupUCI() {
	}

	public CoupUCI(int xDepart, int yDepart, int xArrivee, int yArrivee) {
		this.xDepart = xDepart;
		this.yDepart = yDepart;
		this.xArrivee = xArrivee;
		this.yArrivee = yArrivee;
		promotedPiece = "";
	}

	public CoupUCI(int xDepart, int yDepart, int xArrivee, int yArrivee, String promotedPiece) {
		this.xDepart = xDepart;
		this.yDepart = yDepart;
		this.xArrivee = xArrivee;
		this.yArrivee = yArrivee;
		this.promotedPiece = promotedPiece;
	}

	String sendMove() {
		return indexToColumn(this.xDepart) + indexToLine(this.yDepart) + indexToColumn(this.xArrivee)
				+ this.indexToLine(yArrivee) + this.promotedPiece;
	}

	// convertie un coup (coordonnées de départ et d'arrivée) en String pour l'UCI
	String sendMove(int xDepart, int yDepart, int xArrivee, int yArrivee, String promotedPiece) {
		return indexToColumn(xDepart) + indexToLine(yDepart) + indexToColumn(xArrivee) + indexToLine(yArrivee)
				+ this.promotedPiece;
	}

	// convertie un move de l'UCI en coordonnées (attributs de l'objet CoupUCI)
	void getMove(String move) {
		String[] moveSplit;
		moveSplit = move.split("");
		this.xDepart = ColumnToIndex(moveSplit[0]);
		this.yDepart = LineToIndex(moveSplit[1]);
		this.xArrivee = ColumnToIndex(moveSplit[2]);
		this.yArrivee = LineToIndex(moveSplit[3]);

		if (moveSplit[4] != null)
			this.promotedPiece = moveSplit[4];
	}

	String indexToColumn(int x) {
		String column;

		if (x == 0)
			column = "a";

		else if (x == 1)
			column = "b";

		else if (x == 2)
			column = "c";

		else if (x == 3)
			column = "d";

		else if (x == 4)
			column = "e";

		else if (x == 5)
			column = "f";

		else if (x == 6)
			column = "g";

		else
			column = "h";

		return column;
	}

	String indexToLine(int y) {
		return Integer.toString(y + 1);
	}

	int ColumnToIndex(String column) {
		int x;

		if (column == "a")
			x = 0;

		else if (column == "b")
			x = 1;

		else if (column == "c")
			x = 2;

		else if (column == "d")
			x = 3;

		else if (column == "e")
			x = 4;

		else if (column == "f")
			x = 5;

		else if (column == "g")
			x = 6;

		else
			x = 7;

		return x;
	}

	int LineToIndex(String line) {
		return Integer.parseInt(line) - 1;
	}

	// indique si le coup a permis une promotion
	boolean isPromotion() {
		return promotedPiece != "";
	}
}
