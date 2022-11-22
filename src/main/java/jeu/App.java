package jeu;

import java.util.Map;
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
        System.out.println("test");
        echec.setupEchiquier();
        echec.printEchiquier();
        System.out.println("test");
        echec.getPossibleMoves();

        Minimax miniMax = new Minimax();
        miniMax.constructTree(echec);
        boolean result = miniMax.checkWin();

        assertTrue(result);

        miniMax.constructTree(echec);
        result = miniMax.checkWin();

        assertFalse(result);

        /*try {
            Client client = new Client();
            String position = "r1bqkbnr/pppp1ppp/2n5/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 3 3";
            String analysisLineRegex = "info depth ([\\w]*) seldepth [\\w]* multipv ([\\w]*) score (cp ([\\-\\w]*)|mate ([\\w*])) [\\s\\w]*pv ([\\w]*)\\s*([\\s\\w]*)";
            final Pattern pattern = Pattern.compile(analysisLineRegex);

            //client.start("arena");

            // We initialise the engine to use the UCI interface
          //  client.command("uci", identity(), (s) -> s.startsWith("uciok"), 2000l);

            // We set MultiPV to 10
          //  client.command("setoption name MultiPV value 10", identity(), s -> s.startsWith("readyok"), 2000l);

            // We set the give position
           // client.command("position fen " + position, identity(), s -> s.startsWith("readyok"), 2000l);

            Map<Integer, String> bestMoves = client.command(
                    "go depth 18",
                    lines -> {
                        Map<Integer, String> result = new TreeMap<>();
                        for (String line : lines) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.matches()) {
                                Integer pv = Integer.parseInt(matcher.group(2));
                                String move = matcher.group(6);
                                result.put(pv, move);
                            }
                        }
                        return result;
                    },
                    s -> s.startsWith("bestmove"),
                    50000l);

            bestMoves.forEach((k, v) -> {
                System.out.printf("%d %s\n", k, v);
            });

            client.close();
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        } catch (TimeoutException e) {

        }*/

    }
}
