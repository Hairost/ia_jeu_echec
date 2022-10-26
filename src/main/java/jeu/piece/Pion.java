package jeu.piece;

import jeu.Echiquier;

public class Pion extends Piece {

	private boolean premierCoup;

	public Pion(String couleur, int x, int y, Echiquier echiquier) {
		super("pion", couleur, x, y, echiquier);
		setPremierCoup(true);
	}

	public boolean deplacementValide(int x, int y) {
		super.deplacementValide(x, y);

		// on vérifie si une pièce se trouve sur la case et peut être prise en diagonale
		if (this.echiquier.getCase(x, y) != null) {
			// on vérifie si la pièce est blanche
			if (this.isBlanc()) {
				if (x == this.x + 1 && y == this.y + 1) // déplacement en diagonale droite
					return true;

				else if (x == this.x - 1 && y == this.y + 1) // déplacement en diagonale gauche
					return true;
			}

			// sinon elle est noire
			else {
				if (x == this.x + 1 && y == this.y - 1) // déplacement en diagonale droite
					return true;

				else if (x == this.x - 1 && y == this.y - 1) // déplacement en diagonale gauche
					return true;
			}
		}

		// si aucune pièce ne se trouve sur la case, le pion peut avancer tout droit
		else if (this.echiquier.getCase(x, y) == null) {
			// on vérifie si la pièce est blanche
			if (this.isBlanc()) {
				// peut avancer de 2 case en avant pour le premier coup
				if (x == this.x && y == this.y + 2 && isPremierCoup())
					return true;

				// sinon avance de 1 case
				if (x == this.x && y == this.y + 1) {
					return true;
				}
			}

			// sinon elle est noire
			else {
				// peut avancer de 2 case en avant pour le premier coup
				if (x == this.x && y == this.y - 2 && isPremierCoup())
					return true;

				// sinon avance de 1 case
				if (x == this.x && y == this.y - 1) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public void deplacement(int x, int y) {
		super.deplacement(x, y);
		setPremierCoup(false); // si un déplacement à lieu, les coups suivants ne sont pas des premiers coups
	}

	public boolean isPremierCoup() {
		return this.premierCoup;
	}

	public void setPremierCoup(boolean premierCoup) {
		this.premierCoup = premierCoup;
	}

}
