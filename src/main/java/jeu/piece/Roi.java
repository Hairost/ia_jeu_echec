package jeu.piece;

import java.util.ArrayList;

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
				// si la piece est de couleur opposée à celle du roi, il y a échec
				if (this.isCouleurOpposee(piece)) {
					return true;
				}
			}
		}
		return false;
	}

	// vérifie si le roi est en échec et mat
	public boolean estEchecEtMat() {
		ArrayList<Piece> listePieces; // liste de pièces même couleur que celle du roi
		if (this.isBlanc())
			listePieces = echiquier.getPiecesBlanches();

		else
			listePieces = echiquier.getPiecesNoires();

		return false;
	}
}
