package katas.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueWithFailoverTest {

    private QueueWithFailover jobQueue;

    @BeforeEach
    void setUp() {
        jobQueue = new QueueWithFailover(3);
    }

    @Test
    void testSendJobAndSize() {
        jobQueue.sendJob("Job 1");
        jobQueue.sendJob("Job 2");

        assertEquals(2, jobQueue.size());
    }

    @Test
    void testGetJob() {
        jobQueue.sendJob("Job 1");
        String job = jobQueue.getJob();

        assertEquals("Job 1", job);
        assertEquals(0, jobQueue.size());
        assertEquals(1, jobQueue.inFlightSize());
    }

    @Test
    void testEmptyQueueException() {
        assertThrows(QueueWithFailover.EmptyQueueException.class, () -> {
            jobQueue.getJob();
        });
    }

    @Test
    void testNullJobInput() {
        assertThrows(NullPointerException.class, () -> {
            jobQueue.sendJob(null);
        });
    }

    @Test
    void testEmptyJobInput() {
        jobQueue.sendJob("");
        assertEquals(1, jobQueue.size());
        assertEquals("", jobQueue.getJob());
    }

    @Test
    void testJobDoneRemovesHiddenJob() {
        jobQueue.sendJob("Job 1");
        String job = jobQueue.getJob();

        jobQueue.jobDone(job);

        assertEquals(0, jobQueue.inFlightSize());
    }

    @Test
    void testJobDoneThrowsIfJobExpired() throws InterruptedException {
        jobQueue.sendJob("Job 1");
        String job = jobQueue.getJob();

        Thread.sleep(4000); // Simulate job timeout
        jobQueue.returnExpiredJobsToQueue();

        assertThrows(IllegalArgumentException.class, () -> {
            jobQueue.jobDone(job);
        });
    }

    @Test
    void testReturnExpiredJobsToQueue() throws InterruptedException {
        jobQueue.sendJob("Job 1");
        jobQueue.sendJob("Job 2");
        jobQueue.getJob();

        Thread.sleep(4000);

        jobQueue.returnExpiredJobsToQueue();

        assertEquals(2, jobQueue.size());
        assertEquals(0, jobQueue.inFlightSize());
        assertTrue(jobQueue.getJob().equals("Job 2"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(jobQueue.isEmpty());

        jobQueue.sendJob("Job 1");

        assertFalse(jobQueue.isEmpty());
    }

    @Test
    void testInFlightSize() {
        jobQueue.sendJob("Job 1");
        jobQueue.getJob();

        assertEquals(1, jobQueue.inFlightSize());
    }

    @Test
    void testMultipleJobHandling() {
        jobQueue.sendJob("Job 1");
        jobQueue.sendJob("Job 2");
        jobQueue.sendJob("Job 3");

        assertEquals("Job 1", jobQueue.getJob());
        assertEquals(2, jobQueue.size());

        jobQueue.sendJob("Job 4");
        assertEquals(3, jobQueue.size());

        jobQueue.returnExpiredJobsToQueue();
        assertEquals(3, jobQueue.size());
    }

    @Test
    void testJobReEnqueueAfterTimeout() throws InterruptedException {
        jobQueue.sendJob("Job 1");
        String job = jobQueue.getJob();

        Thread.sleep(4000);
        jobQueue.returnExpiredJobsToQueue();

        assertEquals(0, jobQueue.inFlightSize());
        assertEquals("Job 1", jobQueue.getJob());

    }
}
