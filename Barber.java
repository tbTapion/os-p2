/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread {
    private final CustomerQueue queue;
    private final Gui gui;
    private final int pos;
    private boolean running;
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.queue = queue;
		this.gui = gui;
		this.pos = pos;
	}
	
	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		running = true;
	    this.start();
	}
	
	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		running = false;
	}

	@Override
    public void run() {
        while (running) {
            try {
                //Resting time
                gui.barberIsSleeping(pos);
                this.sleep((long) (Math.random() * Globals.barberSleep));
                gui.barberIsAwake(pos);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            //Grabs a customer
            if (queue.takeCustomer(pos)) {
                try {
                    //Working his magic as a barber
                    this.sleep((long) (Math.random() * Globals.barberWork));
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                gui.emptyBarberChair(pos);
            }
        }
    }
}

