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
    }
}
