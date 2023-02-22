import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        new First().execute();

        new Second().execute();

        new CompletableFutureClass().execute();
        new CompletableFutureClass().execute2();

         /* its output will be
             Compltable Future is starting...
             Here is the Runnable Task...
             Compltable Future is executed...
         */

        int result = new CompletableFutureClass().execute3();
        System.out.println(" CompletableFuture Result " + result); // CompletableFuture Result 100

        new CompletableFutureClass().sendNotification();
        System.out.println(" Order is placed and email is sent");

        new CompletableFutureClass().execute4();

        new CompletableFutureClass().execute5();

        new CompletableFutureClass().execute6();

        new CompletableFutureClass().execute7();

        new CompletableFutureClass().execute8();

        new CompletableFutureClass().execute9();

        new FlightProject().init();

    }


}