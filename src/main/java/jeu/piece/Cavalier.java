package jeu.piece;

import jeu.Echiquier;

public class Cavalier extends Piece {

	public Cavalier(String couleur, int x, int y, Echiquier echiquier) {
		super("cavalier", couleur, x, y, echiquier);
	}

	public boolean deplacementValide(int x, int y) {
		super.deplacementValide(x, y);

		// on teste les différents déplacements en L
		if (x == this.x + 1 && y == this.y + 2) {
			return true;
		}

		else if (x == this.x + 1 && y == this.y - 2) {
			return true;
		}

		else if (x == this.x + 2 && y == this.y + 1) {
			return true;
		}

		else if (x == this.x + 2 && y == this.y - 1) {
			return true;
		}

		else if (x == this.x - 1 && y == this.y + 2) {
			return true;
		}

		else if (x == this.x - 1 && y == this.y - 2) {
			return true;
		}

		else if (x == this.x - 2 && y == this.y + 1) {
			return true;
		}

		else if (x == this.x - 2 && y == this.y - 1) {
			return true;
		}

		return false;
	}
}
