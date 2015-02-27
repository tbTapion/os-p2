/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread{

	private CustomerQueue queue;
	private Gui gui;
	private boolean running;

	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) {
		this.queue = queue;
		this.gui = gui;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		running = true;
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
	 	running = false;
		this.run();
	}

	// Add more methods as needed
	@Override
	private void run(){
		while(running){
			try{
				this.sleep((long)(Math.random()*Globals.doormanSleep));
			}catch(Exception E){
				e.printStackTrace();
			}
			gui.println("Doorman: Trying to get a new customer...");
			queue.addCustomer();
		}
	}
}
