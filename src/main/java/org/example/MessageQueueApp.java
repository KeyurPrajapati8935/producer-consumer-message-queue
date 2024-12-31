package org.example;

public class MessageQueueApp {

    public static void main(String[] args) {

        MessageQueue messageQueue = new MessageQueue();
        MessageProducer producer = new MessageProducer(messageQueue);
        MessageProcessor processor = new MessageProcessor(messageQueue);

        Thread producerThread = new Thread(producer);
        Thread processorThread = new Thread(processor);

        producerThread.start();
        processorThread.start();

        // Simulate application running for a certain duration
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producerThread.interrupt();
        processorThread.interrupt();
    }
}
