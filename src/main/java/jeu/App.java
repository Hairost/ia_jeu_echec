package jeu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import static java.util.function.Function.identity;
import java.util.regex.Pattern;

public class App {
    static  Echiquier echec;
    public static void main(String[] args) {

        echec = new Echiquier();
        echec.setupEchiquier();
        echec.printEchiquier();
        echec.getPossibleMoves();

        Scanner sc = new Scanner(System.in);

        String value = sc.nextLine(); 


        sc.close();
    }
}
