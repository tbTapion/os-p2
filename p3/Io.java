/**
 * This class implements functionality associated with
 * the IO device of the simulated system.
 */
public class Io {
    /** The queue of processes waiting for IO access */
    private Queue ioQueue;
    /** A reference to the statistics collector */
    private Statistics statistics;
    /** The maximum time quant used by the RR algorithm */
    private long avgIoTime;

    /**
     * Creates a new cpu device with the given parameters.
     * @param ioQueue	The memory queue to be used.
     * @param avgIoTime	The amount of memory in the memory device.
     * @param statistics	A reference to the statistics collector.
     */
    public Io(Queue ioQueue, long avgIoTime, Statistics statistics) {
        this.ioQueue = ioQueue;
        this.avgIoTime = avgIoTime;
        this.statistics = statistics;
    }
}
