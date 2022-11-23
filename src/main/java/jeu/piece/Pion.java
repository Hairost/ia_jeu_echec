package jeu.piece;

import java.util.ArrayList;

import jeu.Coordonnees;
import jeu.Echiquier;

public class Pion extends Piece {

	private boolean premierCoup;

	public Pion(String couleur, int x, int y, Echiquier echiquier) {
		super("pion", couleur, x, y, echiquier);
		setPremierCoup(true);
	}

	public boolean deplacementValide(int x, int y) {
		if (super.deplacementValide(x, y) == false)
			return false;

		// on vérifie si une pièce se trouve sur la case et peut être prise en diagonale
		if (!this.echiquier.caseVide(x, y)) {
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
		else if (this.echiquier.caseVide(x, y)) {
			// on vérifie si la pièce est blanche
			if (this.isBlanc()) {
				// peut avancer de 2 case en avant pour le premier coup
				if (x == this.x && y == this.y + 2 && isPremierCoup() && this.echiquier.caseVide(x, y + 1))
					return true;

				// sinon avance de 1 case
				if (x == this.x && y == this.y + 1) {
					return true;
				}
			}

			// sinon elle est noire
			else {
				// peut avancer de 2 case en avant pour le premier coup
				if (x == this.x && y == this.y - 2 && isPremierCoup() && this.echiquier.caseVide(x, y - 1))
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
		if (isPromotion())
			this.getEchiquier().setCase(x, y, new Reine(this.getCouleur(), x, y, this.getEchiquier()));
	}

	public boolean isPremierCoup() {
		return this.premierCoup;
	}

	public void setPremierCoup(boolean premierCoup) {
		this.premierCoup = premierCoup;
	}

	public ArrayList<Coordonnees> listeDeplacementsValides() {
		ArrayList<Coordonnees> listeCoordoonees = new ArrayList<Coordonnees>();

		// on vérifie si la pièce est blanche
		if (this.isBlanc()) {
			if (deplacementValide(x + 1, y + 1))
				listeCoordoonees.add(new Coordonnees(x + 1, y + 1));

			if (deplacementValide(x - 1, y + 1))
				listeCoordoonees.add(new Coordonnees(x - 1, y + 1));

			if (deplacementValide(x, y + 1))
				listeCoordoonees.add(new Coordonnees(x, y + 1));

			if (deplacementValide(x, y + 2))
				listeCoordoonees.add(new Coordonnees(x, y + 2));

		}

		// sinon elle est noire
		else {
			if (deplacementValide(x + 1, y - 1))
				listeCoordoonees.add(new Coordonnees(x + 1, y - 1));

			if (deplacementValide(x - 1, y - 1))
				listeCoordoonees.add(new Coordonnees(x - 1, y - 1));

			if (deplacementValide(x, y - 1))
				listeCoordoonees.add(new Coordonnees(x, y - 1));

			if (deplacementValide(x, y - 2))
				listeCoordoonees.add(new Coordonnees(x, y - 2));
		}
		return listeCoordoonees;
	}

	public boolean isPromotion() {
		if (this.isBlanc() && this.getY() == 7) {
			return true;
		}

		else if (this.isNoir() && this.getY() == 0) {
			return true;
		}

		else
			return false;
	}
}
