package contatore;

import java.util.concurrent.Semaphore;

public class MyCounter implements Counter {

	public int cont;
	private Semaphore mutex;

	private static final int MAX = 10;
	private static final int MIN = 0;

	public MyCounter() {
		cont = 0;
		mutex = new Semaphore(1);
	}

	@Override
	public void incrementa() {
		try {
			mutex.acquire();
			if (cont != MAX)
				cont++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
		}
	}

	@Override
	public void decrementa() {
		try {
			mutex.acquire();
			if (cont != MIN)
				cont--;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
		}
	}

}
