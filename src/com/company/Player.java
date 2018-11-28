package com.company;

public class Player {
    private String name;
    private String sendingQueue;
    private String receivingQueue;
    private String message;
    private MessageBroker messageBroker;

    public Player(String name, String sendingQueue, String receivingQueue, MessageBroker messageBroker) {
        this.name = name;
        this.sendingQueue = sendingQueue;
        this.receivingQueue = receivingQueue;
        this.messageBroker = messageBroker;
    }

    public void sendMessage(String message) {
        this.messageBroker.sendMessage(this.sendingQueue, message);
        System.out.println(this.name + " sendMessage:    " + message);
    }

    public void receiveMessage() {
        while ((this.message = this.messageBroker.receiveMessage(this.receivingQueue)) == null) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.name + " receiveMessage: " + this.message);
        sendMessage(this.message + this.messageBroker.getCount());
    }
}
