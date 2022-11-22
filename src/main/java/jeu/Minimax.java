package jeu;

import java.util.List;

public class Minimax {
    Tree tree;

    public void constructTree(Echiquier eq) {
        tree = new Tree();
        Node root = new Node(eq, true);
        tree.setRoot(root);
        constructTree(root);
    }

    private void constructTree(Node parentNode) {
        List<Echiquier> listofPossibleHeaps 
          = parentNode.getEchiquier().getPossibleMoves();
        boolean isChildMaxPlayer = !parentNode.isMaxPlayer();
        listofPossibleHeaps.forEach(n -> {
            Node newNode = new Node(n, isChildMaxPlayer);
            parentNode.addChild(newNode);
            /*if (!newNode.termine()) {
                constructTree(newNode);
            }*/
        });
    }
}
