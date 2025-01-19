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
        logger.info("Debut de la fonction et du multi");

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


        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                request();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "Yes";
        }, executorService);

        completableFuture.thenAccept(result -> {
                    logger.info("Promise Result: " + result);
                })
                .exceptionally(throwable -> {
                    logger.error("Error occurred: " + throwable.getMessage());
                    return null;
                });

        logger.info("Doing other task....");

        logger.info("Fin du multi");

        executorService.shutdown();

        logger.info("Fin de la fonction");

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
}
