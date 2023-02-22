import javax.crypto.spec.PSource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureClass {

    public void execute(){

        //For writing completable future, you dont need executor interface.

        var completableFuture = CompletableFuture.runAsync(()-> {
            System.out.println(" Compltable Future is starting... ");

            try {
                LongTask();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(" Compltable Future is executed... ");
        });
    }

    public void execute2(){

        //You can create tasks using Runnable interface.
        Runnable task = () -> System.out.println(" Here is the Runnable Task...");
        var completableFuture = CompletableFuture.runAsync(task);
    }

    public int execute3(){

        //If you want to return some value, use supplyAsync
        //Runnable task = ;
        var completableFuture = CompletableFuture.supplyAsync(() ->
                                {   System.out.println(" Program started...");
                                    return 100;
                                }
        );

        try {
            return completableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendNotification(){
        var completableObject = CompletableFuture.runAsync(()-> new MailService().sendEmail());
    }

    //It shows usage of "thenRun" method on CompletableFuture
    public void execute4(){
        var future = CompletableFuture.supplyAsync(()->1);

        // perform this operation synchronously.
        future.thenRun(()-> {
                System.out.println(Thread.currentThread().getName()); // Runs on Main Thread Pool
                System.out.println(" Processing done sychronously");
        });

        // perform this operation Asynchronously.
        future.thenRunAsync(()-> {
            System.out.println(Thread.currentThread().getName()); // Runs on ForkJoin Pool
            System.out.println(" Processing done Asychronously");
        });
    }

    //It shows usage of "thenAccept" method on CompletableFuture
    public void execute5(){
        var completableFutureObject = CompletableFuture.supplyAsync(()-> processData());

        completableFutureObject.thenAccept(result -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("The result of the processData is : " + result);
        });
    }

    public void execute6(){
        var completableFutureObject = CompletableFuture.supplyAsync(()-> processData());

        completableFutureObject.thenAcceptAsync(result -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("The result of the processData is : " + result);
        });
    }

    // Handling exceptions.
    // If we see below code, it wont throw exception because exception was thrown on a different thread.
    // Asynchronous function run on different threads so exception will be thrown there so we cannot see it.
    // We can see exception thrown only on the Main thread. If you want to see the exception, you will have to
    // call completableFutureObject.get() method.
    // If you want to avoid exception and instead send some default value, you can use exceptionally().
    public void execute7() {
        var completableFutureObject = CompletableFuture.supplyAsync(() -> {
            System.out.println(" Error will occur during the data processing....");
            processData();
            throw new IllegalArgumentException();
        });

        try {
            //completableFutureObject.get(); // this throws asynchronus thread exception.

            var result = completableFutureObject.exceptionally(ex -> 100).get(); // if error, it will send 100 back.
            System.out.println(" Result is  " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

        // Using thenApply and thenAccept methods.
        // This program converts celsius to fahrenheit

        public void execute8(){
            var completeFuture = CompletableFuture.supplyAsync(() -> 20);
            //Apply the logic once completable future is complete.
            completeFuture
                    .thenApply( c -> (c * 1.8) + 32)  // or you can call function here.
                    //Provide the logic to consume the input
                    .thenAccept(result -> System.out.println(" Temprature is : " + result));

        }

        // Use 2 different functions and combine their results
    public void execute9(){
        //
        var first = CompletableFuture
                        .supplyAsync(() -> "20USD")
                        .thenApply(str-> str.replace("USD", ""))
                        .thenApply(str->Integer.parseInt(str));
        var second = CompletableFuture.supplyAsync(() -> 0.9);

        first.thenCombine(second, (price, exRate) -> price * exRate )
                //Provide the logic to consume the input
                .thenAccept(result -> System.out.println(" Exchange Amount is : " + result));

    }

    private int processData() {
        return 1234;
    }


    private void LongTask() throws InterruptedException {
        Thread.sleep(5000);
    }
    static void takeNap(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
