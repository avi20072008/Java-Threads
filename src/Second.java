import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Second {

    public void execute() {
        var executor = Executors.newFixedThreadPool(4);
        var future = executor.submit(()->{
            TimeConsumingMethod();
            return 1;
        });

        try{
            System.out.println(" Thread processing started...");
            var result = future.get();
            System.out.println( " Result is : " + result);
        }catch (InterruptedException | ExecutionException e ){
            System.out.println("Error occurred...");
        }finally{
            executor.shutdown();
        }



    }

    private void TimeConsumingMethod() throws InterruptedException {
        Thread.sleep(5000);
    }
}
