package jeu;

import java.util.ArrayList;

import jeu.piece.Cavalier;
import jeu.piece.Fou;
import jeu.piece.Piece;
import jeu.piece.Pion;
import jeu.piece.Reine;
import jeu.piece.Roi;
import jeu.piece.Tour;

public class Echiquier {

	protected Piece[][] echiquier;
	boolean couleur;

	public Echiquier() {
		echiquier = new Piece[8][8];
	}

	public Echiquier(Echiquier eq) {
		this.echiquier = eq.echiquier.clone();
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

	public void setupEchiquier() {
		for (int i = 0; i < echiquier.length; i++) {
			for (int j = 0; j < echiquier[i].length; j++) {
				echiquier[i][j] = null;
			}
		}

		setCase(0, 0, new Tour("blanc", 0, 0, this));
		setCase(1, 0, new Cavalier("blanc", 1, 0, this));
		setCase(2, 0, new Fou("blanc", 2, 0, this));
		setCase(3, 0, new Reine("blanc", 3, 0, this));
		setCase(4, 0, new Roi("blanc", 4, 0, this));
		setCase(5, 0, new Fou("blanc", 5, 0, this));
		setCase(6, 0, new Cavalier("blanc", 6, 0, this));
		setCase(7, 0, new Tour("blanc", 7, 0, this));

		for (int i = 0; i < echiquier.length; i++) {
			setCase(i, 1, new Pion("blanc", i, 1, this));
			setCase(i, 6, new Pion("noir", i, 6, this));
		}

		setCase(0, 7, new Tour("noir", 0, 7, this));
		setCase(1, 7, new Cavalier("noir", 1, 7, this));
		setCase(2, 7, new Fou("noir", 2, 7, this));
		setCase(3, 7, new Reine("noir", 3, 7, this));
		setCase(4, 7, new Roi("noir", 4, 7, this));
		setCase(5, 7, new Fou("noir", 5, 7, this));
		setCase(6, 7, new Cavalier("noir", 6, 7, this));
		setCase(7, 7, new Tour("noir", 7, 7, this));
	}

	// Vérifie que la case d'arrivée est sur le plateau (8x8)
	public boolean surEchiquier(int x, int y) {
		return x >= 0 && x <= 7 && y >= 0 && y <= 7;
	}

	public boolean caseVide(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0)
			return false;
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
				if (!caseVide(i, j) && getCase(i, j).isBlanc()) {
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
				if (!caseVide(i, j) && getCase(i, j).isNoir()) {
					listePieces.add(getCase(i, j));
				}
			}
		}
		return listePieces;
	}

	public void printEchiquier() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = this.getCase(i, j);
				try {
					System.out.print(p.getNom() + " |");
				} catch (NullPointerException e) {
					System.out.print(" |");
				}
			}
			System.out.println("");
		}
	}

	public ArrayList<Move> getPossibleMoves() {
		ArrayList<Move> list = new ArrayList<>();

		ArrayList<Piece> pieces;
		if (couleur == true)
			pieces = this.getPiecesBlanches();
		else {
			pieces = this.getPiecesNoires();
		}

		int i = 0;
		for (Piece piece : pieces) {
			ArrayList<Coordonnees> coords = piece.listeDeplacementsValides();
			for (Coordonnees coord : coords) {
				Echiquier eq2 = this.clone();
				if (couleur == true) {
					eq2.getPiecesBlanches().get(i).deplacement(coord.getX(), coord.getY());
					eq2 = eq2.getPiecesBlanches().get(i).getEchiquier().clone();
				} else {
					eq2.getPiecesNoires().get(i).deplacement(coord.getX(), coord.getY());
					eq2 = eq2.getPiecesNoires().get(i).getEchiquier().clone();
				}

				// System.out.println("-----");
				// eq2.printEchiquier();
				// System.out.println("-----");
				if (!eq2.isEchec()) {
					Coordonnees coord_start = new Coordonnees(piece.getX(), piece.getY());
					Move move;
					if (piece.isPion() && ((Pion) piece).isPromotion(coord.getY())) {// si la piece est un pion et est
																						// promue
						move = new Move(coord_start, new Coordonnees(coord.getX(), coord.getY()), eq2, true);

					} else
						move = new Move(coord_start, new Coordonnees(coord.getX(), coord.getY()), eq2);
					list.add(move);
				}
			}
			i++;
		}
		return list;
	}

	public boolean isEchec() {

		ArrayList<Piece> pieces;
		if (couleur == true)
			pieces = this.getPiecesBlanches();
		else {
			pieces = this.getPiecesNoires();
		}

		for (Piece p : pieces) {
			if (p.getNom() == "roi") {
				Roi pr = (Roi) p;
				boolean value = pr.isEchec();
				if (value == true)
					return true;
			}
		}
		return false;
	}

	public int evaluate() {
		int result = 0;

		ArrayList<Piece> pieces;
		if (couleur == true)
			pieces = this.getPiecesNoires();
		else {
			pieces = this.getPiecesBlanches();
		}

		for (Piece p : pieces) {
			if (p.getNom().equals("pion")) {
				result++;
			} else if (p.getNom().equals("reine")) {
				result += 50;
			} else {
				result += 20;
			}
		}

		if (couleur == true)
			pieces = this.getPiecesBlanches();
		else {
			pieces = this.getPiecesNoires();
		}

		for (Piece p : this.getPiecesBlanches()) {
			if (p.getNom().equals("pion")) {
				result--;
			} else if (p.getNom().equals("reine")) {
				result -= 50;
			} else {
				result -= 20;
			}
		}

		result = result + 2 * (this.getPossibleMoves().size());

		// Ajouter mise en echec

		return result;
	}

	public Piece getPieceAt(int x, int y) {

		for (Piece p : this.getPieces()) {
			if (p.getX() == x && p.getY() == y)
				return p;
		}
		return null;
	}

	@Override
	public Echiquier clone() {
		Echiquier eq2 = new Echiquier();
		eq2.couleur = this.couleur;
		eq2.echiquier = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				try {
					if (this.echiquier[i][j].getNom() == "pion") {
						eq2.echiquier[i][j] = new Pion(this.echiquier[i][j].getCouleur(), this.echiquier[i][j].getX(),
								this.echiquier[i][j].getY(), eq2);
					} else if (this.echiquier[i][j].getNom() == "fou") {
						eq2.echiquier[i][j] = new Fou(this.echiquier[i][j].getCouleur(), this.echiquier[i][j].getX(),
								this.echiquier[i][j].getY(), eq2);
					} else if (this.echiquier[i][j].getNom() == "tour") {
						eq2.echiquier[i][j] = new Tour(this.echiquier[i][j].getCouleur(), this.echiquier[i][j].getX(),
								this.echiquier[i][j].getY(), eq2);
					} else if (this.echiquier[i][j].getNom() == "cavalier") {
						eq2.echiquier[i][j] = new Cavalier(this.echiquier[i][j].getCouleur(),
								this.echiquier[i][j].getX(), this.echiquier[i][j].getY(), eq2);
					} else if (this.echiquier[i][j].getNom() == "roi") {
						eq2.echiquier[i][j] = new Roi(this.echiquier[i][j].getCouleur(), this.echiquier[i][j].getX(),
								this.echiquier[i][j].getY(), eq2);
					} else if (this.echiquier[i][j].getNom() == "reine") {
						eq2.echiquier[i][j] = new Reine(this.echiquier[i][j].getCouleur(), this.echiquier[i][j].getX(),
								this.echiquier[i][j].getY(), eq2);
					}
				} catch (NullPointerException e) {

				}

			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				try {
					eq2.echiquier[i][j].setEchiquier(eq2);
				} catch (NullPointerException e) {

				}
			}
		}

		return eq2;
	}
}
