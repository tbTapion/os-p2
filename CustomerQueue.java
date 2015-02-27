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
        freeSeats = new ArrayList<Integer>();
        for (int i = 0; i < queueLength; i++) {
            freeSeats.add(i);
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
            gui.println("Doorman is waiting for free seats.");
            try {
                wait();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            gui.println("Doorman was notified of a free seat.");
            return false;
        }
        final int seatIndex = ThreadLocalRandom.current().nextInt(freeSeats.size());
        final QueueElement lastCustomer = new QueueElement(
                freeSeats.get(seatIndex),
                new Customer());
        queue.add(lastCustomer);
        freeSeats.remove(seatIndex);
        gui.fillLoungeChair(lastCustomer.seat, lastCustomer.customer);
        notify();
        return true;
    }

    /**
     * Move a customer from the queue to the specified barber chair.
     * @param barber Specified barber chair.
     * @return Success.
     */
    public synchronized boolean takeCustomer(final int barber) {
        if (queue.size() <= 0) {
            if (!(freeSeats.size() > 1)) {
                gui.println("Barber #" + barber + " is waiting for a customer.");
            }
            try {
                wait();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            if (!(freeSeats.size() > 1)) {
                gui.println("Barber #" + barber + " was notified of a new customer.");
            }
            return false;
        }
        final QueueElement firstCustomer = queue.get(0);
        queue.remove(0);
        freeSeats.add(firstCustomer.seat);
        gui.emptyLoungeChair(firstCustomer.seat);
        gui.fillBarberChair(barber, firstCustomer.customer);
        if (freeSeats.size() == 1) {
            notifyAll();
        }
        return true;
    }
}
