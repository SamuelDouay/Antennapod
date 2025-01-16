package com.podcast.antennapod;

import com.podcast.antennapod.logic.config.ConfigProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.podcast.antennapod.logic.database.SQLQuery.getPodcast;

public class MainTest {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        /*CompletableFuture<String> completableFuture =CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        CompletableFuture<Void> future = completableFuture
                .thenAccept(s -> logger.info("Computation returned: " + s))
                .thenRun(() -> logger.info("Computation finished."));
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        try {
            combinedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        logger.info(combined); */

        /*
        try {
            Future<Integer> future = new SquareCalculator().calculate(10);

            while (!future.isDone()) {
                logger.info("Calculating...");
                Thread.sleep(300);
            }
            Integer result = future.get(500, TimeUnit.MICROSECONDS);
            future.cancel(true);

            logger.info(result);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }


        try {
            SquareCalculator squareCalculator = new SquareCalculator();

            Future<Integer> future1 = squareCalculator.calculate(10);
            Future<Integer> future2 = squareCalculator.calculate(100);

            while (!(future1.isDone() && future2.isDone())) {
                logger.info(
                        String.format(
                                "future1 is %s and future2 is %s",
                                future1.isDone() ? "done" : "not done",
                                future2.isDone() ? "done" : "not done"
                        )
                );
                Thread.sleep(300);
            }

            Integer result1 = future1.get();
            Integer result2 = future2.get();

            logger.info(result1 + " and " + result2);
            future1.cancel(true);
            future2.cancel(true);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        request();

         */

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<String> completableFutureResult = CompletableFuture.supplyAsync(() -> {
            return getTestAsync(1000);
        }, executorService);

        completableFutureResult.thenAccept(result -> {
                    logger.info("Promise Result: " + result);
                })
                .exceptionally(throwable -> {
                    logger.error("Error occurred: " + throwable.getMessage());
                    return null;
                });

        logger.info("Doing other tasks...");
        logger.info(getTestAsync(500));

        executorService.shutdown();

    }

    private static String getTestAsync(int milli) {
        try {
            Thread.sleep(milli);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "CompletableFuture Result";
    }

    public static void request() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://feeds.acast.com/public/shows/5e6a404cd22bfc26784b114c"))
                .build();
        CompletableFuture<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);

        while (!response.isDone()) {
            logger.info("Wait request....");
            Thread.sleep(300);
        }

        logger.info(response.get());
    }

    public static Future<HashMap<String, String>> mapFuture() {
        CompletableFuture<HashMap<String, String>> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(1000);
            logger.info("Yes thread");
            return getPodcast();
        });

        return completableFuture;
    }

    private void config() {

        ConfigProperties configProperties = ConfigProperties.getInstance();

        logger.info(configProperties.getProperty("page.name"));
        String page_name = configProperties.getProperty("page.name");
        String[] page = page_name.split(",");

        for (String s : page) {
            logger.info(configProperties.getProperty("page." + s + ".name"));

            logger.info(configProperties.getProperty("page." + s + ".icon"));
        }
    }

    public static class SquareCalculator {

        private ExecutorService executor = Executors.newFixedThreadPool(2);

        public Future<Integer> calculate(Integer input) {
            return executor.submit(() -> {
                logger.info("Calculate square of " + input);
                Thread.sleep(1000);
                return input * input;
            });
        }
    }
}
