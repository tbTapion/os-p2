/**
 * This class implements functionality associated with
 * the IO device of the simulated system.
 */
public class Io {
    /**
     * The queue of processes waiting for IO access
     */
    private Queue ioQueue;
    /**
     * A reference to the statistics collector
     */
    private Statistics statistics;
    /**
     * The maximum time quant used by the RR algorithm
     */
    private long avgIoTime;
    /**
     * Current process running
     */
    private Process currentProcess;

    /**
     * Creates a new cpu device with the given parameters.
     * @param ioQueue    The memory queue to be used.
     * @param avgIoTime  The amount of memory in the memory device.
     * @param statistics A reference to the statistics collector.
     */
    public Io(Queue ioQueue, long avgIoTime, Statistics statistics) {
        this.ioQueue = ioQueue;
        this.avgIoTime = avgIoTime;
        this.statistics = statistics;
        this.currentProcess = null;
    }

    /**
     * Return the time to process the I/O event
     * @return ioTime - long
     */
    public long getIoTime() {
        return (long)(avgIoTime * (0.9 + Math.random() * 0.2));
    }

    /**
     * Adds a process to the IO queue to be used by the RR algorithm
     * @param p - Process
     */
    public void insertProcess(Process p) {
        ioQueue.insert(p);
    }

    /**
     * Sets a process as the current process used by the IO
     * @param p - Process
     */
    public void startProcess(Process p) {
        currentProcess = p;
    }

    /**
     * Gets the current process in the IO
     * @return Process
     */
    public Process checkRunning() {
        return currentProcess;
    }

    /**
     * Gets a process and removes it from the IO queue. If no process in queue, returns null
     * @return Process
     */
    public Process removeNextProcess() {
        if (ioQueue.isEmpty()) { return null; }
        return (Process) ioQueue.removeNext();
    }

    public void timePassed(long timePassed) {
        statistics.cpuQueueLengthTime += ioQueue.getQueueLength() * timePassed;
        if (ioQueue.getQueueLength() > statistics.ioQueueLargestLength) {
            statistics.ioQueueLargestLength = ioQueue.getQueueLength();
        }
    }
}
