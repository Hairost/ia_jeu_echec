package jeu.piece;

import jeu.Echiquier;

public class Tour extends Piece {

	public Tour(String couleur, int x, int y, Echiquier echiquier) {
		super("tour", couleur, x, y, echiquier);
	}

	public boolean deplacementValide(int x, int y) {
		super.deplacementValide(x, y);

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
		return false;
	}
}
