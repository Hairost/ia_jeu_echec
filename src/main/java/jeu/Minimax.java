package jeu;

public class Minimax {
    static Move bestmove;

    public static int maxi(int depth, Echiquier eq) {
        if (depth == 0)
            return eq.evaluate();
        int max = -9999;
        for (Move mv : eq.getPossibleMoves()) {
            int score = mini(depth - 1, mv.eq);
            if (score > max)
                bestmove = mv;
                max = score;
        }
        return max;
    }

    public static int mini(int depth, Echiquier eq) {
        if (depth == 0)
            return -eq.evaluate();
        int min = 9999;
        for (Move mv : eq.getPossibleMoves()) {
            int score = maxi(depth - 1, mv.eq);
            if (score < min)
                bestmove = mv;
                min = score;
        }
        return min;
    }

}
