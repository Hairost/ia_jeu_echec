package jeu.piece;

import jeu.Echiquier;

public class Reine extends Piece {

	public Reine(String couleur, int x, int y, Echiquier echiquier) {
		super("reine", couleur, x, y, echiquier);
	}

	public boolean deplacementValide(int x, int y) {
		super.deplacementValide(x, y);

		// même déplacements que la tour
		// déplacement sur une colonne
		if (x == this.x && y != this.y) {

			// déplacement vers la droite
			if (x > this.x) {
				// on parcourt la colonne pour voir s'il y a un obstacle
				for (int i = this.x + 1; i < x; i++) {
					if (!echiquier.caseVide(i, y))
						return false;
				}
				return true;
			}

			// sinon deplacement vers la gauche
			else {
				// on parcourt la colonne pour voir s'il y a un obstacle
				for (int i = this.x - 1; i > x; i--) {
					if (!echiquier.caseVide(i, y))
						return false;
				}
				return true;
			}
		}

		// déplacement sur une ligne
		else if (x != this.x && y == this.y) {

			// déplacement vers la droite
			if (y > this.y) {
				// on parcourt la ligne pour voir s'il y a un obstacle
				for (int i = this.y + 1; i < y; i++) {
					if (!echiquier.caseVide(i, x))
						return false;
				}
				return true;
			}

			// sinon déplacement vers la gauche
			else {
				// on parcourt la ligne pour voir s'il y a un obstacle
				for (int i = this.y - 1; i > y; i--) {
					if (!echiquier.caseVide(i, x))
						return false;
				}
				return true;
			}
		}

		// même déplacements que le fou
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
