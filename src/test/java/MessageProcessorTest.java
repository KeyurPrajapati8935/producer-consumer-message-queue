import org.example.MessageProcessor;
import org.example.MessageQueue;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageProcessorTest {

    @Test
    public void testSuccessfulMessageProcessing() throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();
        MessageProcessor processor = new MessageProcessor(messageQueue);

        messageQueue.enqueue("Message-1");

        // Create and start a thread for the processor
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(processor);

        // Wait for the thread to complete processing (adjust timeout as needed)
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(1, processor.getSuccessCount());
        assertEquals(0, processor.getErrorCount());
    }

    @Test
    public void testFailedMessageProcessing() throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue();
        MessageProcessor processor = new MessageProcessor(messageQueue);

        messageQueue.enqueue("Message-error");

        // Create and start a thread for the processor
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(processor);

        // Wait for the thread to complete processing (adjust timeout as needed)
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(0, processor.getSuccessCount());
        assertEquals(1, processor.getErrorCount());
    }
}