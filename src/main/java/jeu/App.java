package jeu;

import java.util.Scanner;

public class App {
	static String moves;
	static Echiquier echec;
	static boolean color_set = false;

	public static void main(String[] args) {

		@SuppressWarnings("all")
		Scanner input = new Scanner(System.in);
		while (true) {
			String inputString = input.nextLine();
			if ("uci".equals(inputString)) {
				inputUCI();
			} else if (inputString.startsWith("setoption")) // pas necessaire
			{
				inputSetOption(inputString);
			} else if ("isready".equals(inputString)) {
				inputIsReady();
			} else if ("ucinewgame".equals(inputString)) {
				inputUCINewGame();
			} else if (inputString.startsWith("position")) {
				inputPosition(inputString);
			} else if (inputString.startsWith("go")) {
				inputGo();
			} else if (inputString.equals("quit")) {
				inputQuit();
			} else if ("print".equals(inputString)) {
				inputPrint();
			}

		}
	}

	public static void inputUCI() {
		System.out.println("id name IA_JEU_ECHEC_V1");
		System.out.println("id author Paulin");
		// options go here
		System.out.println("uciok");
	}

	public static void inputSetOption(String inputString) {
		// set options
	}

	public static void inputIsReady() {
		echec = new Echiquier();
		echec.setupEchiquier();
		System.out.println("readyok");
	}

	public static void inputUCINewGame() {
		// add code here
		echec = new Echiquier();
		echec.setupEchiquier();
	}

	public static void inputPosition(String input) {
		input = input.substring(9).concat(" ");
		if (input.contains("startpos ")) {
			input = input.substring(9);
		} else if (input.contains("fen")) {
			input = input.substring(4);
		}

		if (color_set == false) {
			if (input.contains("moves")) {
				echec.couleur = false;
			} else {
				echec.couleur = true;
			}
		}

		if (input.contains("moves")) {
			input = input.substring(input.indexOf("moves") + 6);
			moves = input;

			String[] all_moves = moves.split(" ");

			String last_move = all_moves[all_moves.length - 1];
			System.out.println("Dernier mouvement : " + last_move);
			Move new_mouv = new Move(last_move, echec);

			echec.getPieceAt(new_mouv.pieceDebut.getX(), new_mouv.pieceDebut.getY())
					.force_deplacement(new_mouv.pieceFin.getX(), new_mouv.pieceFin.getY());
			if (new_mouv.promotedPiece != null)
				echec.setCase(new_mouv.pieceFin.getX(), new_mouv.pieceFin.getY(), new_mouv.promotedPiece);
		}
	}

	public static void inputGo() {

		Move move = null;

		echec.printEchiquier();

		move = Minimax.maxiFirst(3, echec);

		String UCI_start_move = move.convertPieceDebutUCI();
		String UCI_end_move = move.convertPieceFinUCI();
		String result = UCI_start_move + UCI_end_move;
		System.out.println(move.pieceDebut.getX() + "|" + move.pieceDebut.getY());
		System.out.println(move.pieceFin.getX() + "|" + move.pieceFin.getY());
		echec.printEchiquier();
		echec.getPieceAt(move.pieceDebut.getX(), move.pieceDebut.getY()).deplacement(move.pieceFin.getX(),
				move.pieceFin.getY());
		System.out.println("bestmove " + result);
	}

	public static void inputQuit() {
		System.exit(0);
	}

	public static void inputPrint() {
	}
}
