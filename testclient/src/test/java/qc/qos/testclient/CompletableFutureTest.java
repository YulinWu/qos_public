package qc.qos.testclient;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * Copyright (c) 2020 onward, Yulin Wu. All rights reserved.
 *
 * @author Yulin Wu, mail4ywu@gmail.com/mail4ywu@icloud.com
 * University of Science and Technology of China
 */
public class CompletableFutureTest {
    @Test
    public void doTest0(){
        System.out.println("calling thread" + Thread.currentThread().getName());
        CompletableFuture<Void> f = CompletableFuture.completedFuture(null);
        f.thenCompose(x ->
            CompletableFuture.supplyAsync(() -> {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return 1;
                },
                Executors.newCachedThreadPool())
        ).thenAccept(x -> System.out.println("Thread 1" + Thread.currentThread().getName()));
        f.thenCompose(x ->
                CompletableFuture.supplyAsync(() -> {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return 2;
                        },
                        Executors.newCachedThreadPool())
        ).thenAccept(x -> System.out.println("Thread 2" + Thread.currentThread().getName()));
        f.thenCompose(x ->
                CompletableFuture.supplyAsync(() -> {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return 3;
                        },
                        Executors.newCachedThreadPool())
        ).thenAccept(x -> System.out.println("Thread 3" + Thread.currentThread().getName()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
