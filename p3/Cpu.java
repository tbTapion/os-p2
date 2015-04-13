/**
 * This class implements functionality associated with
 * the cpu device of the simulated system.
 */
public class Cpu {
    /** The queue of processes waiting for cpu access */
    private Queue cpuQueue;
    /** A reference to the statistics collector */
    private Statistics statistics;
    /** The maximum time quant used by the RR algorithm */
    private long maxCpuTime;
    /** The process currently running */
    private Process runningProcess;

    /**
     * Creates a new cpu device with the given parameters.
     * @param cpuQueue	The memory queue to be used.
     * @param maxCpuTime	The amount of memory in the memory device.
     * @param statistics	A reference to the statistics collector.
     */
    public Cpu(Queue cpuQueue, long maxCpuTime, Statistics statistics) {
        this.cpuQueue = cpuQueue;
        this.maxCpuTime = maxCpuTime;
        this.statistics = statistics;
        this.runningProcess = null;
    }

    /**
     * Returns the max cpu time for each RR segment.
     * @return The max cpu time.
     */
    public long getMaxCpuTime() { return maxCpuTime; }

    /**
     * Adds a process to the cpu queue.
     * @param p	The process to be added.
     */
    public void insertProcess(Process p) {
        cpuQueue.insert(p);
    }

    /**
     * Starts running a process from the cpu queue.
     * @param p The process to start.
     */
    public void startProcess(Process p) {
        runningProcess = p;
    }

    /**
     * Checks if a process is currently running and returns the running process. Returns null if
     * no process is running.
     * @return The running process.
     */
    public Process checkRunning() {
        return runningProcess;
    }

    /**
     * Removes the next process in line and returns it. Returns null if the queue is empty.
     * @return The next process in line.
     */
    public Process removeNextProcess() {
        if (cpuQueue.isEmpty()) { return null; }
        return (Process)cpuQueue.removeNext();
    }

    /**
     * This method is called when a discrete amount of time has passed.
     * @param timePassed	The amount of time that has passed since the last call to this method.
     */
    public void timePassed(long timePassed) {
        statistics.cpuQueueLengthTime += cpuQueue.getQueueLength()*timePassed;
        if (cpuQueue.getQueueLength() > statistics.cpuQueueLargestLength) {
            statistics.cpuQueueLargestLength = cpuQueue.getQueueLength();
        }
        //TODO: Statistics on cpu idle/running
    }
}
