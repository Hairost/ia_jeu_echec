package jeu;

import java.util.ArrayList;

import jeu.piece.Piece;

public class Echiquier {

	protected Piece[][] echiquier;

	public Echiquier() {
		echiquier = new Piece[8][8];
	}

	public Piece getCase(int x, int y) {
		if (surEchiquier(x, y))
			return echiquier[x][y];
		return null;
	}

	public void setCase(int x, int y, Piece piece) {
		if (surEchiquier(x, y))
			this.echiquier[x][y] = piece;
	}

	// Vérifie que la case d'arrivée est sur le plateau (7x7)
	public boolean surEchiquier(int x, int y) {
		return x > 0 && x < 7 && y > 0 && y < 7;
	}

	public boolean caseVide(int x, int y) {
		if (echiquier[x][y] == null) {
			return true;
		}

		return false;
	}

	public ArrayList<Piece> getPieces() {
		ArrayList<Piece> listePieces = new ArrayList<Piece>();

		for (int i = 0; i < echiquier.length; i++) {
			for (int j = 0; j < echiquier.length; j++) {
				if (getCase(i, j) != null) {
					listePieces.add(getCase(i, j));
				}
			}
		}
		return listePieces;
	}

	public ArrayList<Piece> getPiecesBlanches() {
		ArrayList<Piece> listePieces = new ArrayList<Piece>();

		for (int i = 0; i < echiquier.length; i++) {
			for (int j = 0; j < echiquier.length; j++) {
				if (!caseVide(i, j) && getCase(i, j).getCouleur().equals("blanc")) {
					listePieces.add(getCase(i, j));
				}
			}
		}
		return listePieces;
	}

	public ArrayList<Piece> getPiecesNoires() {
		ArrayList<Piece> listePieces = new ArrayList<Piece>();

		for (int i = 0; i < echiquier.length; i++) {
			for (int j = 0; j < echiquier.length; j++) {
				if (!caseVide(i, j) && getCase(i, j).getCouleur().equals("noir")) {
					listePieces.add(getCase(i, j));
				}
			}
		}
		return listePieces;
	}
}
