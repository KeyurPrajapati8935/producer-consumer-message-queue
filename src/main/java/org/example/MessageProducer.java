package org.example;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageProducer implements Runnable {

    private static final Logger logger = Logger.getLogger(MessageProducer.class.getName());
    private final MessageQueue messageQueue;

    public MessageProducer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = "Message-" + ThreadLocalRandom.current().nextInt(100);
                messageQueue.enqueue(message);
                logger.log(Level.INFO, "Produced message: " + message);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.log(Level.INFO, "Producer thread interrupted, stopping production.");
                return;
            }
        }
    }
}