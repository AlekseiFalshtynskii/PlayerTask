package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String QUEUE1 = "QUEUE1";
    private static final String QUEUE2 = "QUEUE2";

    public static void main(String[] args) {
        MessageBroker messageBroker = new MessageBroker();
        messageBroker.registerQueue(QUEUE1);
        messageBroker.registerQueue(QUEUE2);

        Player player1 = new Player("Player1", QUEUE2, QUEUE1, messageBroker);
        Player player2 = new Player("Player2", QUEUE1, QUEUE2, messageBroker);

        Runnable listener1 = createListener(player1);
        Runnable listener2 = createListener(player2);

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(listener1);
        service.submit(listener2);

        player1.sendMessage("test");
    }

    private static Runnable createListener(Player player) {
        return () -> {
            while (true) {
                player.receiveMessage();
            }
        };
    }
}
