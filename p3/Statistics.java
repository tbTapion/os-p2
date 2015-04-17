/**
 * This class contains a lot of public variables that can be updated
 * by other classes during a simulation, to collect information about
 * the run.
 */
public class Statistics
{
    /** The number of processes that have exited the system */
    public long nofCompletedProcesses = 0;
    /** The number of processes that have entered the system */
    public long nofCreatedProcesses = 0;
    /** The number of (forced) process switches */
    public long nofProcessSwitches = 0;
    /** The number of I/O operations processed */
    public long nofIoOperations = 0;
    /** The time cpu has spent running */
    public long totalTimeCpuRunning = 0;
    /** The time cpu has spent idle */
    public long totalTimeCpuIdle = 0;
    /** The total time that all completed processes have spent waiting for memory */
    public long totalTimeSpentWaitingForMemory = 0;
    /** The total time that all completed processes have spent waiting for cpu */
    public long totalTimeSpentWaitingForCpu = 0;
    /** The total time that all completed processes have spent waiting for I/O */
    public long totalTimeSpentWaitingForIo = 0;
    /** The total time that all completed process have spent in cpu */
    public long totalTimeSpentInCpu = 0;
    /** The total time that all completed process have spent in I/O */
    public long totalTimeSpentInIo = 0;
    /** The time-weighted length of the memory queue, divide this number by the total time to get average queue length */
    public long memoryQueueLengthTime = 0;
    /** The largest memory queue length that has occured */
    public long memoryQueueLargestLength = 0;
    /** The time-weighted length of the cpu queue, divide this number by the total time to get average queue length */
    public long cpuQueueLengthTime = 0;
    /** The largest cpu queue length that has occured */
    public long cpuQueueLargestLength = 0;
    /** The time-weighted length of the I/O queue, divide this number by the total time to get average queue length */
    public long ioQueueLengthTime = 0;
    /** The largest I/O queue length that has occured */
    public long ioQueueLargestLength = 0;
    /** The amount of times finished process have been put in the cpu queue */
    public long nofTimesPlacedInCpuQueue = 0;
    /** The amount of times finished process have been put in the I/O queue */
    public long nofTimesPlacedInIoQueue = 0;

    /**
     * Prints out a report summarizing all collected data about the simulation.
     * @param simulationLength	The number of milliseconds that the simulation covered.
     */
    public void printReport(long simulationLength) {
        System.out.println();
        System.out.println("Simulation statistics:");
        System.out.println();
        System.out.println("Number of completed processes:                                "+nofCompletedProcesses);
        System.out.println("Number of created processes:                                  "+nofCreatedProcesses);
        System.out.println("Number of (forced) process switches:                          "+nofProcessSwitches);
        System.out.println("Number of processed I/O operations:                           "+nofIoOperations);
        System.out.println("Average throughput (processes per second)                     "+(float)nofCompletedProcesses/simulationLength*1000);
        System.out.println();
        System.out.println("Total CPU time spent processing:                              "+totalTimeCpuRunning+" ms");
        System.out.println("Fraction of CPU time spent processing:                        "+(float)totalTimeCpuRunning/simulationLength+"%");
        System.out.println("Total CPU time spent waiting:                                 "+totalTimeCpuIdle+" ms");
        System.out.println("Fraction of CPU time spent waiting:                           "+(float)totalTimeCpuIdle/simulationLength+"%");
        System.out.println();
        System.out.println("Largest occuring memory queue length:                         "+memoryQueueLargestLength);
        System.out.println("Average memory queue length:                                  "+(float)memoryQueueLengthTime/simulationLength);
        System.out.println("Largest occuring cpu queue length:                            "+cpuQueueLargestLength);
        System.out.println("Average cpu queue length:                                     "+(float)cpuQueueLengthTime/simulationLength);
        System.out.println("Largest occuring I/O queue length:                            "+ioQueueLargestLength);
        System.out.println("Average I/O queue length:                                     "+(float)ioQueueLengthTime/simulationLength);
        if(nofCompletedProcesses > 0) {
            System.out.println("Average # of times a process has been placed in memory queue: "+1);
            System.out.println("Average # of times a process has been placed in cpu queue:    "+(float)nofTimesPlacedInCpuQueue/nofCompletedProcesses);
            System.out.println("Average # of times a process has been placed in I/O queue:    "+(float)nofTimesPlacedInIoQueue/nofCompletedProcesses);
            System.out.println();
            float totalTimeSpentInSystem = totalTimeSpentWaitingForMemory
                    +totalTimeSpentWaitingForCpu + totalTimeSpentInCpu
                    +totalTimeSpentWaitingForIo + totalTimeSpentInIo;
            System.out.println("Average time spent in system per process:                     "+totalTimeSpentInSystem/nofCompletedProcesses+" ms");
            System.out.println("Average time spent waiting for memory per process:            "+(float)totalTimeSpentWaitingForMemory/nofCompletedProcesses+" ms");
            System.out.println("Average time spent waiting for cpu per process:               "+(float)totalTimeSpentWaitingForCpu/nofCompletedProcesses+" ms");
            System.out.println("Average time spent processing per process:                    "+(float)totalTimeSpentInCpu/nofCompletedProcesses+" ms");
            System.out.println("Average time spent waiting for I/O per process:               "+(float)totalTimeSpentWaitingForIo/nofCompletedProcesses+" ms");
            System.out.println("Average time spent in I/O per process:                        "+(float)totalTimeSpentInIo/nofCompletedProcesses+"ms");
        }
    }
}
