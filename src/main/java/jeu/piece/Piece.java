package jeu.piece;

import java.util.ArrayList;

import jeu.Coordonnees;
import jeu.Echiquier;

public abstract class Piece {
	private String nom;
	protected String couleur; // "blanc" ou "noir"
	protected int x, y;
	protected Echiquier echiquier; // echiquier sur lequel se trouve la pièce

	public Piece(String nom, String couleur, int x, int y, Echiquier echiquier) {
		setNom(nom);
		setCouleur(couleur);
		setX(x);
		setY(y);
		setEchiquier(echiquier);
	}

	public Piece(Piece p) {
		this.nom = p.nom;
		this.couleur = p.couleur;
		this.x = p.x;
		this.y = p.y;
		this.echiquier = p.echiquier;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Echiquier getEchiquier() {
		return echiquier;
	}

	public void setEchiquier(Echiquier echiquier) {
		this.echiquier = echiquier;
	}

	public boolean surEchiquier(int x, int y) {
		return echiquier.surEchiquier(x, y);
	}

	// Indique si le deplacement de la piece est valide
	public boolean deplacementValide(int x, int y) {
		if (!surEchiquier(x, y))
			return false;

		if (!this.echiquier.caseVide(x, y)) {
			Piece piece = this.echiquier.getCase(x, y);
			// si piece de même couleur sur la case visée, deplacement invalide
			if (!this.isCouleurOpposee(piece))
				return false;
		}
		return true;
	};

	// Deplacement de la piece aux coordonnées (x,y)
	public void deplacement(int x, int y) {
		if (deplacementValide(x, y)) {
			this.echiquier.setCase(this.x, this.y, null);
			this.x = x;
			this.y = y;
			this.echiquier.setCase(this.x, this.y, this);
		}
	}

	// Deplacement de la piece aux coordonnées (x,y)
	public void force_deplacement(int x, int y) {
		this.echiquier.setCase(this.x, this.y, null);
		this.x = x;
		this.y = y;
		this.echiquier.setCase(this.x, this.y, this);

	}

	public boolean isBlanc() {
		return getCouleur().equals("blanc");
	}

	public boolean isNoir() {
		return getCouleur().equals("noir");
	}

	public boolean isCouleurOpposee(Piece piece) {
		if ((this.isBlanc() && piece.isNoir()) || (this.isNoir() && piece.isBlanc()))
			return true;
		return false;
	}

	public abstract ArrayList<Coordonnees> listeDeplacementsValides();

	public boolean isPion() {
		if (this.getNom().equals("pion"))
			return true;
		return false;
	}

	public boolean isTour() {
		if (this.getNom().equals("tour"))
			return true;
		return false;
	}

	public boolean isCavalier() {
		if (this.getNom().equals("cavalier"))
			return true;
		return false;
	}

	public boolean isFou() {
		if (this.getNom().equals("fou"))
			return true;
		return false;
	}

	public boolean isRoi() {
		if (this.getNom().equals("roi"))
			return true;
		return false;
	}

	public boolean isReine() {
		if (this.getNom().equals("reine"))
			return true;
		return false;
	}
}
