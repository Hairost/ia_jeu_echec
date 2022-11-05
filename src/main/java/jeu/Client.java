package jeu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.function.Function.identity;

public class Client {

    private Process process = null;
    private BufferedReader reader = null;
    private OutputStreamWriter writer = null;

    public void start(String cmd) {
        ProcessBuilder pb = new ProcessBuilder(cmd);
        try {
            this.process = pb.start();
            this.reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            this.writer = new OutputStreamWriter(process.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client() { }

    public void close() {
        if (this.process.isAlive()) {
            this.process.destroy();
        }
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T command(String cmd, Function<List<String>, T> commandProcessor, Predicate<String> breakCondition, long timeout)
        throws InterruptedException, ExecutionException, TimeoutException {

    // This completable future will send a command to the process
    // And gather all the output of the engine in the List<String>
    // At the end, the List<String> is translated to T through the
    // commandProcessor Function
    CompletableFuture<T> command = supplyAsync(() -> {
        final List<String> output = new ArrayList<>();
        try {
            writer.flush();
            writer.write(cmd + "\n");
            writer.write("isready\n");
            writer.flush();
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.contains("Unknown command")) {
                    throw new RuntimeException(line);
                }
                if (line.contains("Unexpected token")) {
                    throw new RuntimeException("Unexpected token: " + line);
                }
                output.add(line);
                if (breakCondition.test(line)) {
                    // At this point we are no longer interested to read any more
                    // output from the engine, we consider that the engine responded
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commandProcessor.apply(output);
    });

    return command.get(timeout, TimeUnit.MILLISECONDS);
}

    // -----
}