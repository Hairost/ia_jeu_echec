package jeu;

import java.util.List;

public class Node {
    boolean isMaxPlayer;
    Echiquier eq;
    int score;
    List<Node> children;
    // setters and getters
    
    public Echiquier getEchiquier() {
        return this.eq;
    }

    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public Node(Echiquier eq, boolean isMaxPlayer) {
        this.eq = eq;
        this.isMaxPlayer = isMaxPlayer;
    }
}
