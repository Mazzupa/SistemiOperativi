package boundedbuffer_0;

/**
 * This is the consumer thread for the bounded buffer problem.
 *
 * Figure 7.15
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Sixth Edition
 * Copyright John Wiley & Sons - 2003.
 */
import java.util.*;

public class Consumer implements Runnable {
	private Buffer buffer;

	public Consumer(Buffer b) {
		buffer = b;
	}

	public void run() {
		@SuppressWarnings("unused")
		Date message;

		while (true) {
			System.out.println("Consumer napping");
			SleepUtilities.nap();

			// consume an item from the buffer
			System.out.println("Consumer wants to consume.");
			Object omessage = buffer.remove();
			if (omessage != null)
				message = (Date) omessage;
			message = (Date) buffer.remove(); // may be null
		}
	}

}
