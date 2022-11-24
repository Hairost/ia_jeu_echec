package jeu;

import jeu.piece.Cavalier;
import jeu.piece.Fou;
import jeu.piece.Reine;
import jeu.piece.Tour;

public class Move {

	Coordonnees pieceDebut;
	Coordonnees pieceFin;
	Echiquier eq;
	boolean isPromotion;

	public Move(Coordonnees p1, Coordonnees p2, Echiquier eq) {
		this.pieceDebut = p1;
		this.pieceFin = p2;
		this.eq = eq;
		isPromotion = false;
	}

	public Move(Coordonnees p1, Coordonnees p2, Echiquier eq, boolean isPromotion) {
		this.pieceDebut = p1;
		this.pieceFin = p2;
		this.eq = eq;
		this.isPromotion = isPromotion;
	}

	public Move(String move, Echiquier eq) {
		this.eq = eq;
		char c1 = move.charAt(0);
		char c2 = move.charAt(1);
		char c3 = move.charAt(2);
		char c4 = move.charAt(3);

		try {
			char c5 = move.charAt(4);
			if (c5 == 'q') {
				System.out.println("Ok ca marche");
				String color;
				if (eq.couleur == true)
					color = "blanc";
				else
					color = "noir";

				eq.setCase((int) (c3 - 97), (int) (c4 - 49), new Reine(color, c3 - 97, c4 - 49, eq));
			}

			else if (c5 == 'r') {
				System.out.println("Ok ca marche");
				String color;
				if (eq.couleur == true)
					color = "blanc";
				else
					color = "noir";

				eq.setCase((int) (c3 - 97), (int) (c4 - 49), new Tour(color, c3 - 97, c4 - 49, eq));
			}

			else if (c5 == 'n') {
				System.out.println("Ok ca marche");
				String color;
				if (eq.couleur == true)
					color = "blanc";
				else
					color = "noir";

				eq.setCase((int) (c3 - 97), (int) (c4 - 49), new Cavalier(color, c3 - 97, c4 - 49, eq));
			}

			else if (c5 == 'b') {
				System.out.println("Ok ca marche");
				String color;
				if (eq.couleur == true)
					color = "blanc";
				else
					color = "noir";

				eq.setCase((int) (c3 - 97), (int) (c4 - 49), new Fou(color, c3 - 97, c4 - 49, eq));
			}
		} catch (IndexOutOfBoundsException e) {

		}

		this.pieceDebut = new Coordonnees((int) (c1 - 97), (int) (c2 - 49));
		this.pieceFin = new Coordonnees((int) (c3 - 97), (int) (c4 - 49));
	}

	public String convertPieceDebutUCI() {
		char p1 = (char) (97 + pieceDebut.getX());
		String p2 = Integer.toString(pieceDebut.getY() + 1);

		String result = p1 + p2;
		return result;
	}

	public String convertPieceFinUCI() {
		char p1 = (char) (97 + pieceFin.getX());
		String p2 = Integer.toString(pieceFin.getY() + 1);

		String result = p1 + p2;
		if (this.isPromotion == true)
			result += "q";

		return result;
	}

}
