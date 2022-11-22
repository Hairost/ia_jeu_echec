package jeu.piece;

import java.util.ArrayList;

import jeu.Coordonnees;
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
					if (!echiquier.isCaseVide(i, y))
						return false;
				}
				return true;
			}

			// sinon deplacement vers la gauche
			else {
				// on parcourt la colonne pour voir s'il y a un obstacle
				for (int i = this.x - 1; i > x; i--) {
					if (!echiquier.isCaseVide(i, y))
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
					if (!echiquier.isCaseVide(i, x))
						return false;
				}
				return true;
			}

			// sinon déplacement vers la gauche
			else {
				// on parcourt la ligne pour voir s'il y a un obstacle
				for (int i = this.y - 1; i > y; i--) {
					if (!echiquier.isCaseVide(i, x))
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

				if (!echiquier.isCaseVide(x - 1, y - 1)) {
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

				if (!echiquier.isCaseVide(x + 1, y - 1)) {
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

				if (!echiquier.isCaseVide(x - 1, y + 1)) {
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

				if (!echiquier.isCaseVide(x + 1, y + 1)) {
					return false;
				}
				x++;
				y++;
			}
			return true;
		}

		return false;
	}

	public ArrayList<Coordonnees> listeDeplacementsValides() {
		ArrayList<Coordonnees> listeCoordoonees = new ArrayList<Coordonnees>();

		int x = this.x + 1;
		int y = this.y;
		while (deplacementValide(x, y)) {
			listeCoordoonees.add(new Coordonnees(x, y));
			x++;
		}

		x = this.x - 1;
		while (deplacementValide(x, y)) {
			listeCoordoonees.add(new Coordonnees(x, y));
			x--;
		}

		x = this.x;
		y = this.y + 1;
		while (deplacementValide(x, y)) {
			listeCoordoonees.add(new Coordonnees(x, y));
			y++;
		}

		x = this.x;
		y = this.y - 1;
		while (deplacementValide(x, y)) {
			listeCoordoonees.add(new Coordonnees(x, y));
			y--;
		}

		x = this.x + 1;
		y = this.y + 1;
		while (deplacementValide(x, y)) {
			listeCoordoonees.add(new Coordonnees(x, y));
			x++;
			y++;
		}

		x = this.x - 1;
		y = this.y - 1;
		while (deplacementValide(x, y)) {
			listeCoordoonees.add(new Coordonnees(x, y));
			x--;
			y--;
		}

		x = this.x - 1;
		y = this.y + 1;
		while (deplacementValide(x, y)) {
			listeCoordoonees.add(new Coordonnees(x, y));
			x--;
			y++;
		}

		x = this.x + 1;
		y = this.y - 1;
		while (deplacementValide(x, y)) {
			listeCoordoonees.add(new Coordonnees(x, y));
			x++;
			y--;
		}
		return listeCoordoonees;
	}

	public String getLettre() {
		if (this.isBlanc())
			return "Q";
		return "q";
	}
}
