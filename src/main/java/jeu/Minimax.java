package jeu;

import java.util.ArrayList;
import java.util.HashMap;

public class Minimax {
    static HashMap<Integer, Move> bestmove = new HashMap<>();

    public static Move maxiFirst(int depth, Echiquier eq) {
        Move bestmove = null;
        int max = -999999;
        for (Move mv : eq.getPossibleMoves()) {

            int score = (int) mini(depth - 1, mv.eq).get(1);
            System.out.println(mv.pieceDebut.getX() + "/" + mv.pieceDebut.getY() + " -> " + mv.pieceFin.getX() + "/"
                    + mv.pieceFin.getY() + "(score : " + score + ")");
            if (score > max) {
                bestmove = new Move(mv.pieceDebut, mv.pieceFin, eq);
                max = score;
            }
        }
        return bestmove;
    }

    @SuppressWarnings("all")
    public static ArrayList maxi(int depth, Echiquier eq) {

        if (depth == 0) {
            ArrayList array = new ArrayList<>();
            array.add(null);
            array.add(eq.evaluate());
            return array;
        }
        ArrayList max = new ArrayList<>();
        max.add(null);
        max.add(-999999);

        for (Move mv : eq.getPossibleMoves()) {
            int score = (int) mini(depth - 1, mv.eq).get(1);
            if (score > (int) max.get(1)) {
                max.set(0, mv);
                max.set(1, score);
            }
        }
        return max;
    }

    @SuppressWarnings("all")
    public static ArrayList mini(int depth, Echiquier eq) {
        if (depth == 0) {
            ArrayList array = new ArrayList<>();
            array.add(null);
            array.add(eq.evaluate());
            return array;
        }
        ArrayList min = new ArrayList<>();
        min.add(null);
        min.add(999999);
        for (Move mv : eq.getPossibleMoves()) {
            int score = (int) maxi(depth - 1, mv.eq).get(1);
            if (score < (int) min.get(1))
                min.set(0, mv);
            min.set(1, score);
        }
        return min;
    }

}
