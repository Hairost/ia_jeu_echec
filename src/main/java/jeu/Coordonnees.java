package jeu;

public class Coordonnees {

	public int x, y;

	public Coordonnees(int x, int y) {
		setCoordonnees(x, y);
	}

	public void setCoordonnees(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
