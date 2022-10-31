package jeu.piece;

import java.util.ArrayList;

import jeu.Coordonnees;
import jeu.Echiquier;

public class Roi extends Piece {

	public Roi(String couleur, int x, int y, Echiquier echiquier) {
		super("roi", couleur, x, y, echiquier);
	}

	public boolean deplacementValide(int x, int y) {
		super.deplacementValide(x, y);

		// déplacement sur une ligne (droite ou gauche)
		if ((x == this.x + 1 || x == this.x - 1) && y == this.y) {
			return true;
		}

		// déplacement sur une colonne (haut ou bas)
		else if (x == this.x && (y == this.y + 1 || y == this.y - 1)) {
			return true;

		}

		// deplacement sur une diagonale
		else if ((x == this.x + 1 && y == this.y + 1) || (x == this.x + 1 && y == this.y - 1)
				|| (x == this.x - 1 && y == this.y - 1) || (x == this.x - 1 && y == this.y + 1)) {
			return true;
		}

		return false;
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
}
