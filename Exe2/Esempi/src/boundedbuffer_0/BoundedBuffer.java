package boundedbuffer_0;

import java.util.concurrent.Semaphore;

/**
 * BoundedBuffer.java
 *
 * This program implements the bounded buffer using Java synchronization.
 *
 * Figure 7.31
 *
 * @author Gagne, Galvin, Silberschatz Operating System Concepts with Java -
 *         Sixth Edition Copyright John Wiley & Sons - 2003.
 */

public class BoundedBuffer implements Buffer {
	private static final int BUFFER_SIZE = 5;

	private int count; // number of items in the buffer
	private int in; // points to the next free position in the buffer
	private int out; // points to the next full position in the buffer
	private Object[] buffer;
	private Semaphore mutex;

	public BoundedBuffer() {
		// buffer is initially empty
		count = 0;
		in = 0;
		out = 0;

		buffer = new Object[BUFFER_SIZE];
		mutex = new Semaphore(1);
	}

	public void insert(Object item) {

		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (count < BUFFER_SIZE) {
			// add an item to the buffer
			++count;
			buffer[in] = item;
			in = (in + 1) % BUFFER_SIZE;
			if (count == BUFFER_SIZE)
				System.out.println("Producer Entered " + item + " Buffer FULL");
			else
				System.out.println("Producer Entered " + item + " Buffer Size = " + count);
		}
		mutex.release();
	}

	// consumer calls this method
	public Object remove() {
		Object item = null;

		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (count > 0) {
			// remove an item from the buffer
			--count;
			item = buffer[out];
			out = (out + 1) % BUFFER_SIZE;
			if (count == 0)
				System.out.println("Consumer Consumed " + item + " Buffer EMPTY");
			else
				System.out.println("Consumer Consumed " + item + " Buffer Size = " + count);
		}
		mutex.release();

		return item;
	}

}
