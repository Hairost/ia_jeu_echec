package jeu.piece;

import java.util.ArrayList;

import jeu.Coordonnees;
import jeu.Echiquier;

public class Cavalier extends Piece {

	public Cavalier(String couleur, int x, int y, Echiquier echiquier) {
		super("cavalier", couleur, x, y, echiquier);
	}

	public boolean deplacementValide(int x, int y) {
		if (super.deplacementValide(x, y) == false)
			return false;

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

	public ArrayList<Coordonnees> listeDeplacementsValides() {
		ArrayList<Coordonnees> listeCoordoonees = new ArrayList<Coordonnees>();

		if (deplacementValide(x + 1, y + 2))
			listeCoordoonees.add(new Coordonnees(x + 1, y + 2));

		if (deplacementValide(x + 1, y - 2))
			listeCoordoonees.add(new Coordonnees(x + 1, y - 2));

		if (deplacementValide(x + 2, y + 1))
			listeCoordoonees.add(new Coordonnees(x + 2, y + 1));

		if (deplacementValide(x + 2, y - 1))
			listeCoordoonees.add(new Coordonnees(x + 2, y - 1));

		if (deplacementValide(x - 1, y + 2))
			listeCoordoonees.add(new Coordonnees(x - 1, y + 2));

		if (deplacementValide(x - 1, y - 2))
			listeCoordoonees.add(new Coordonnees(x - 1, y - 2));

		if (deplacementValide(x - 2, y + 1))
			listeCoordoonees.add(new Coordonnees(x - 2, y + 1));

		if (deplacementValide(x - 2, y - 1))
			listeCoordoonees.add(new Coordonnees(x - 2, y - 1));

		return listeCoordoonees;
	}
}
