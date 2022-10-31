package jeu;

public class Jeu {

	protected Echiquier echiquier;

	public Jeu() {
		Echiquier echiquier = new Echiquier();
		echiquier.setupEchiquier();
	}

	public Jeu(Echiquier echiquier) {
		setEchiquier(echiquier);
	}

	public Echiquier getEchiquier() {
		return echiquier;
	}

	public void setEchiquier(Echiquier echiquier) {
		this.echiquier = echiquier;
	}
}
