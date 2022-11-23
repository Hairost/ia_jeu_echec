package jeu.piece;

import java.util.ArrayList;

import jeu.Coordonnees;
import jeu.Echiquier;

public class Reine extends Piece {

	public Reine(String couleur, int x, int y, Echiquier echiquier) {
		super("reine", couleur, x, y, echiquier);
	}

	public boolean deplacementValide(int x, int y) {
		if (super.deplacementValide(x, y) == false)
			return false;

		// même déplacements que la tour
		// déplacement sur une ligne
		if (x != this.x && y == this.y) {

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

		// déplacement sur une colonne
		else if (x == this.x && y != this.y) {

			// déplacement vers le haut
			if (y > this.y) {
				// on parcourt la ligne pour voir s'il y a un obstacle
				for (int i = this.y + 1; i < y; i++) {
					if (!echiquier.caseVide(i, x))
						return false;
				}
				return true;
			}

			// sinon déplacement vers le bas
			else {
				// on parcourt la ligne pour voir s'il y a un obstacle
				for (int i = this.y - 1; i > y; i--) {
					if (!echiquier.caseVide(i, x))
						return false;
				}
				return true;
			}
		}
		boolean deplacementValide = false;
		// vérification du déplacement en diagonale
		for (int i = 0; i <= 8; i++) {
			if (this.x + i == x && this.y + i == y)
				deplacementValide = true;

			else if (this.x - i == x && this.y - i == y)
				deplacementValide = true;

			else if (this.x + i == x && this.y - i == y)
				deplacementValide = true;

			else if (this.x - i == x && this.y + i == y)
				deplacementValide = true;
		}

		if (!deplacementValide)
			return false;

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
}
