package esempi;

public class InterruptibleThread implements Runnable {
	/**
	 * This thread will continue to run as long as it is not interrupted.
	 */
	@Override
	public void run() {
		while (true) {
			System.out.println("a");

			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Thread figlio: I'm interrupted!");
				break;
			}
		}
		// clean up and terminate
	}
}
