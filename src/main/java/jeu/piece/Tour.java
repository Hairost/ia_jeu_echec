package jeu.piece;

import java.util.ArrayList;

import jeu.Coordonnees;
import jeu.Echiquier;

public class Tour extends Piece {

	private boolean premierCoup;
	private boolean positionGauche; // true si la tour commence à gauche, false sinon

	public Tour(String couleur, int x, int y, Echiquier echiquier) {
		super("tour", couleur, x, y, echiquier);
		setPremierCoup(true);
		if (x == 0)
			setPositionGauche(true);
		else
			setPositionGauche(false);
	}

	public boolean deplacementValide(int x, int y) {
		if(super.deplacementValide(x, y) == false) return false;



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

	public void deplacement(int x, int y) {
		super.deplacement(x, y);
		setPremierCoup(false); // si un déplacement à lieu, les coups suivants ne sont pas des premiers coups
	}

	// déplacement dans le cas d'un roque, on ne vérifie pas si on peut déplacer la
	// pièce (vérif pour le déplacement du roi déjà faite)
	public void deplacementRoque(int x, int y) {
		this.echiquier.setCase(this.x, this.y, null);
		this.x = x;
		this.y = y;
		this.echiquier.setCase(this.x, this.y, this);
		setPremierCoup(false);
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

		y = this.y - 1;
		while (deplacementValide(x, y)) {
			listeCoordoonees.add(new Coordonnees(x, y));
			y--;
		}
		return listeCoordoonees;
	}

	public boolean isPremierCoup() {
		return this.premierCoup;
	}

	public void setPremierCoup(boolean premierCoup) {
		this.premierCoup = premierCoup;
	}

	public boolean isGauche() {
		return this.positionGauche;
	}

	public void setPositionGauche(boolean positionGauche) {
		this.positionGauche = positionGauche;
	}
}
