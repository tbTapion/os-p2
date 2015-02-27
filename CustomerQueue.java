import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
    private ArrayList<Integer> freeSeats;
    private ArrayList<QueueElement> queue;
    private int queueMax;
    private Gui gui;

    class QueueElement {
        protected int seat;
        protected Customer customer;

        protected QueueElement(final int seat, final Customer customer) {
            this.seat = seat;
            this.customer = customer;
        }
    }

	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
        this.gui = gui;
        freeSeats = new ArrayList<Integer>(queueLength);
        for (int i = 0; i < queueLength; i++) {
            freeSeats.set(i, i);
        }
        queue = new ArrayList<QueueElement>();
        queueMax = queueLength;
	}

    /**
     * Add a customer to the queue in a random free seat.
     * @return Success.
     */
    public synchronized boolean addCustomer() {
        if (queue.size() >= queueMax) {
            return false;
        }
        final int seatIndex = ThreadLocalRandom.current().nextInt(freeSeats.size());
        final QueueElement lastCustomer = new QueueElement(
                freeSeats.get(seatIndex),
                new Customer());
        queue.add(lastCustomer);
        freeSeats.remove(seatIndex);
        gui.fillLoungeChair(lastCustomer.seat, lastCustomer.customer);
        return true;
    }

    /**
     * Move a customer from the queue to the specified barber chair.
     * @param barber Specified barber chair.
     * @return Success.
     */
    public synchronized boolean takeCustomer(final int barber) {
        if (queue.size() <= 0) {
            return false;
        }
        final QueueElement firstCustomer = queue.get(0);
        queue.remove(0);
        freeSeats.add(firstCustomer.seat);
        gui.emptyLoungeChair(firstCustomer.seat);
        gui.fillBarberChair(barber, firstCustomer.customer);
        return true;
    }
}
