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

	public Echiquier() {
		echiquier = new Piece[8][8];
	}

	public Echiquier(Echiquier eq) {
		this.echiquier = eq.echiquier;
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

	public void printEchiquier() {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				Piece p = this.getCase(i, j);
				try {
				System.out.print(p.getNom()+" |");
				}catch(NullPointerException e) {
					System.out.print(" |");
				}
			}
			System.out.println("");
		}
	}

	public ArrayList<Echiquier> getPossibleMoves() {
		ArrayList<Echiquier> list = new ArrayList<>();

		ArrayList<Piece> pieces = this.getPiecesBlanches();
		int i =0;
		for (Piece piece : pieces) {
			ArrayList<Coordonnees> coords = piece.listeDeplacementsValides();
			for(Coordonnees coord : coords) {
				Echiquier eq2 = new Echiquier(this);
				eq2.getPieces().get(i).deplacement(coord.getX(), coord.getY());
				list.add(eq2);
			}
			i++;
		}
		return list;
	}

	public boolean isEnd() {
		for(Piece p : this.getPieces()) {
			if(p.getNom() == "roi") {
				Roi pr = (Roi)p;
				boolean value = pr.isEchec();
				if(value == true) return true;
			}
		}
		return false;
	}

	public int evaluate() {
		int result = 0; 
		for(Piece p : this.getPiecesNoires()) {
			result ++;
		}
		return result;
	}
}
