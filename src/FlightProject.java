import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class FlightProject {
    List<String> lstWebsites = null;
    public void init(){
        lstWebsites = Arrays.asList("site1", "site2", "site3");

        lstWebsites.stream().forEach(FlightProject::getFlightQuotes);
    }

    public static void getFlightQuotes(String siteName) {
        var first = CompletableFuture
                .runAsync(() -> {
                    System.out.println(String.format("Getting quote from %s", siteName));
                    printQuote(siteName);
                });
    }

    public static void printQuote(String siteName){
        Random random = new Random();
        int price = random.nextInt(100, 200);
        //System.out.println( String.format("Quote{site=%s, price=%s}", siteName, number));
        Quote quote = new Quote(siteName, price);
        System.out.println(quote.toString());
    }
}
