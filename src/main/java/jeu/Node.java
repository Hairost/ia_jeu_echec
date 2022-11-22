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

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Node> getChildren() {
        return this.children;
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
