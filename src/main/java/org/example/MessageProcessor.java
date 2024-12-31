package org.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageProcessor implements Runnable {

    private static final Logger logger = Logger.getLogger(MessageProcessor.class.getName());
    private final MessageQueue messageQueue;
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger errorCount = new AtomicInteger(0);

    public MessageProcessor(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            String message = messageQueue.dequeue();
            if (message != null) {
                try {
                    // Logic of Simulating message processing
                    processMessage(message);
                    successCount.incrementAndGet();
                    logger.log(Level.INFO, "Processed message successfully: " + message);
                } catch (Exception e) {
                    errorCount.incrementAndGet();
                    logger.log(Level.SEVERE, "Error processing message: " + message, e);
                }
            }
        }
    }

    private void processMessage(String message) throws Exception {
        if (message.contains("error")) {
            throw new Exception("Simulated processing error");
        }
    }

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getErrorCount() {
        return errorCount.get();
    }
}
