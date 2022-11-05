package jeu.piece;

import java.util.ArrayList;

import jeu.Coordonnees;
import jeu.Echiquier;

public class Roi extends Piece {

	private boolean premierCoup;

	public Roi(String couleur, int x, int y, Echiquier echiquier) {
		super("roi", couleur, x, y, echiquier);
		setPremierCoup(true);
	}

	public boolean deplacementValide(int x, int y) {
		super.deplacementValide(x, y);

		// déplacement sur une ligne (droite ou gauche)
		if ((x == this.x + 1 || x == this.x - 1) && y == this.y && !this.isEchec(x, y)) {
			return true;
		}

		// déplacement sur une colonne (haut ou bas)
		else if (x == this.x && (y == this.y + 1 || y == this.y - 1) && !this.isEchec(x, y)) {
			return true;

		}

		// deplacement sur une diagonale
		else if (((x == this.x + 1 && y == this.y + 1) || (x == this.x + 1 && y == this.y - 1)
				|| (x == this.x - 1 && y == this.y - 1) || (x == this.x - 1 && y == this.y + 1))
				&& !this.isEchec(x, y)) {
			return true;
		}

		// petit roque
		else if (this.isPetitRoque(x, y)) {
			// on vérifie que les cases entre le roi et la tour sont vides
			boolean caseVide1 = this.echiquier.caseVide(this.getX() + 1, this.getY());
			boolean caseVide2 = this.echiquier.caseVide(this.getX() + 2, this.getY());
			// on vérifie qu'il n'y a pas d'échec sur les cases où passe le roi
			boolean echecCase1 = this.isEchec(this.getX() + 1, this.getY());
			boolean echecCase2 = this.isEchec(this.getX() + 2, this.getY());

			if (caseVide1 && caseVide2 && !echecCase1 && !echecCase2) {
				Piece piece;
				if (this.isBlanc())
					piece = this.getEchiquier().getCase(7, 0);
				else
					piece = this.getEchiquier().getCase(7, 7);

				if (piece != null && piece.isTour()) {
					Tour tour = (Tour) piece;
					if (tour.isPremierCoup())
						return true;
				}
			}
		}

		// grand roque
		else if (this.isGrandRoque(x, y)) {
			// on vérifie que les cases entre le roi et la tour sont vides
			boolean caseVide1 = this.echiquier.caseVide(this.getX() - 1, this.getY());
			boolean caseVide2 = this.echiquier.caseVide(this.getX() - 2, this.getY());
			boolean caseVide3 = this.echiquier.caseVide(this.getX() - 3, this.getY());
			// on vérifie qu'il n'y a pas d'échec sur les cases où passe le roi
			boolean echecCase1 = this.isEchec(this.getX() - 1, this.getY());
			boolean echecCase2 = this.isEchec(this.getX() - 2, this.getY());

			if (caseVide1 && caseVide2 && caseVide3 && !echecCase1 && !echecCase2) {
				Piece piece;
				if (this.isBlanc())
					piece = this.getEchiquier().getCase(0, 0);
				else
					piece = this.getEchiquier().getCase(0, 7);
				if (piece != null && piece.isTour()) {
					Tour tour = (Tour) piece;
					if (tour.isPremierCoup()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public void deplacement(int x, int y) {
		super.deplacement(x, y);
		if (this.isPetitRoque(x, y)) {
			Tour tour;
			if (this.isBlanc())
				tour = (Tour) this.getEchiquier().getCase(7, 0);
			else
				tour = (Tour) this.getEchiquier().getCase(7, 7);
			tour.deplacementRoque(this.getX() + 1, this.getY());
		}

		else if (this.isGrandRoque(x, y)) {
			Tour tour;
			if (this.isBlanc())
				tour = (Tour) this.getEchiquier().getCase(0, 0);
			else
				tour = (Tour) this.getEchiquier().getCase(0, 7);
			tour.deplacementRoque(this.getX() - 1, this.getY());
		}
		this.setPremierCoup(false); // si un déplacement à lieu, les coups suivants ne sont pas des premiers coups
	}

	public ArrayList<Coordonnees> listeDeplacementsValides() {
		ArrayList<Coordonnees> listeCoordoonees = new ArrayList<Coordonnees>();

		if (deplacementValide(x + 1, y))
			listeCoordoonees.add(new Coordonnees(x + 1, y));

		if (deplacementValide(x - 1, y))
			listeCoordoonees.add(new Coordonnees(x - 1, y));

		if (deplacementValide(x, y + 1))
			listeCoordoonees.add(new Coordonnees(x, y + 1));

		if (deplacementValide(x, y - 1))
			listeCoordoonees.add(new Coordonnees(x, y - 1));

		if (deplacementValide(x + 1, y + 1))
			listeCoordoonees.add(new Coordonnees(x + 1, y + 1));

		if (deplacementValide(x + 1, y - 1))
			listeCoordoonees.add(new Coordonnees(x + 1, y - 1));

		if (deplacementValide(x - 1, y - 1))
			listeCoordoonees.add(new Coordonnees(x - 1, y - 1));

		if (deplacementValide(x - 1, y + 1))
			listeCoordoonees.add(new Coordonnees(x - 1, y + 1));

		return listeCoordoonees;
	}

	public boolean isPremierCoup() {
		return this.premierCoup;
	}

	public void setPremierCoup(boolean premierCoup) {
		this.premierCoup = premierCoup;
	}

	// vérifie si le roi est en échec
	public boolean isEchec() {
		ArrayList<Piece> listePieces; // liste de pièces de couleur opposée à celle du roi

		if (this.isBlanc())
			listePieces = echiquier.getPiecesNoires();

		else
			listePieces = echiquier.getPiecesBlanches();

		for (Piece piece : listePieces) {
			// true si la piece a un déplacement possible sur la case du Roi
			if (piece.deplacementValide(this.x, this.y)) {
				return true;
			}
		}
		return false;
	}

	// vérifie si le roi est en échec s'il bouge sur une case (x, y)
	public boolean isEchec(int x, int y) {
		ArrayList<Piece> listePieces; // liste de pièces de couleur opposée à celle du roi

		if (this.isBlanc())
			listePieces = echiquier.getPiecesNoires();

		else
			listePieces = echiquier.getPiecesBlanches();

		for (Piece piece : listePieces) {
			// true si la piece a un déplacement possible sur la case du Roi
			if (piece.deplacementValide(x, y)) {
				return true;
			}
		}
		return false;
	}

	// vérifie si le roi est en échec et mat
	public boolean isEchecEtMat() {
		ArrayList<Piece> listePieces; // liste de pièces de même couleur que celle du roi

		if (this.isBlanc())
			listePieces = echiquier.getPiecesBlanches();

		else
			listePieces = echiquier.getPiecesNoires();

		for (Piece piece : listePieces) {
			// recupere toutes les cases ou peut aller la piece
			ArrayList<Coordonnees> listeCasesPossibles = piece.listeDeplacementsValides();

			// on prend chaque coordonnee une a une
			for (int i = 0; i < listeCasesPossibles.size(); i++) {

				// deplace la piece à la coordonnée d'index i
				int x_depart = piece.getX();
				int y_depart = piece.getY();

				int x_arrivee = listeCasesPossibles.get(i).getX();
				int y_arrivee = listeCasesPossibles.get(i).getY();
				Piece piece_arrivee = this.echiquier.getCase(x_arrivee, y_arrivee);

				piece.deplacement(x_arrivee, y_arrivee);

				if (!this.isEchec()) {
					// on revient à l'état de l'échiquier avant le déplacement
					piece.deplacement(x_depart, y_depart);
					this.echiquier.setCase(x_arrivee, y_arrivee, piece_arrivee);
					return false;
				}
				piece.deplacement(x_depart, y_depart);
				this.echiquier.setCase(x_arrivee, y_arrivee, piece_arrivee);
			}
		}
		return true;
	}

	public boolean isPetitRoque(int x, int y) {
		if (y == this.getY() && x == (this.getX() + 2) && this.isPremierCoup())
			return true;
		return false;
	}

	public boolean isGrandRoque(int x, int y) {
		if (y == this.getY() && x == (this.getX() - 2) && this.isPremierCoup())
			return true;
		return false;
	}
}
