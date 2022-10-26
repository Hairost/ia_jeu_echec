package jeu.piece;

import jeu.Echiquier;

public class Fou extends Piece {

	public Fou(String couleur, int x, int y, Echiquier echiquier) {
		super("fou", couleur, x, y, echiquier);
	}

	public boolean deplacementValide(int x, int y) {
		super.deplacementValide(x, y);

		// vérification du déplacement en diagonale
		for (int i = 0; i <= 8; i++) {
			if (this.x + i == x && this.y + i == y)
				return true;

			else if (this.x - i == x && this.y - i == y)
				return true;

			else if (this.x + i == x && this.y - i == y)
				return true;

			else if (this.x - i == x && this.y + i == y)
				return true;
		}

		// vérification obstacles sur la diagonale
		// diagonale haut droite
		if (x > this.x && y > this.y) {
			while (this.x != x - 1 && this.y != y - 1) {

				if (!echiquier.caseVide(x - 1, y - 1)) {
					return false;
				}
				x--;
				y--;
			}
			return true;
		}

		// diagonale haut gauche
		if (x < this.x && y > this.y) {
			while (this.x != x + 1 && this.y != y - 1) {

				if (!echiquier.caseVide(x + 1, y - 1)) {
					return false;
				}
				x++;
				y--;
			}
			return true;
		}

		// diagonale bas droite
		if (x > this.x && y < this.y) {
			while (this.x != x - 1 && this.y != y + 1) {

				if (!echiquier.caseVide(x - 1, y + 1)) {
					return false;
				}
				x--;
				y++;
			}
			return true;
		}

		// diagonale bas gauche
		if (x < this.x && y < this.y) {
			while (this.x != x + 1 && this.y != y + 1) {

				if (!echiquier.caseVide(x + 1, y + 1)) {
					return false;
				}
				x++;
				y++;
			}
			return true;
		}
		return false;
	}
}
