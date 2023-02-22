import java.util.concurrent.Executors;

public class First {

    public void execute() {
        var executor = Executors.newFixedThreadPool(2);

        System.out.println(executor.getClass().getName());

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> System.out.println(Thread.currentThread().getName()));
        }

        System.out.println("Hello world!");
    }
}
