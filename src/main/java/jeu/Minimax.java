package jeu;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Minimax {
    int maxi(int depth, Echiquier eq) {
        if (depth == 0)
            return eq.evaluate();
        int max = -9999;
        for (Echiquier eq2 : eq.getPossibleMoves()) {
            int score = mini(depth - 1, eq2);
            if (score > max)
                max = score;
        }
        return max;
    }

    int mini(int depth, Echiquier eq) {
        if (depth == 0)
            return -eq.evaluate();
        int min = 9999;
        for (Echiquier eq2 : eq.getPossibleMoves()) {
            int score = maxi(depth - 1, eq2);
            if (score < min)
                min = score;
        }
        return min;
    }

}
