package distributore;

import java.util.concurrent.Semaphore;

public class CokeMachineV2 implements Distributore {

	private Semaphore _mutex;
	private int _numLattine;
	private Semaphore _empty;

	public CokeMachineV2() {
		_mutex = new Semaphore(1);
		_numLattine = NUM_MAX_LATTINE;
		_empty = new Semaphore(0);
	}

	@Override
	public void preleva() throws InterruptedException {

		_mutex.acquire();

		if (_numLattine > 0) {
			_numLattine--;
			System.out.println(Thread.currentThread().getName() + " Preleva lattina, lattine rimaste: " + _numLattine);
		}
		if (_numLattine == 0) {
			System.out.println(Thread.currentThread().getName() + " Avvisa fornitore");
			_empty.release();
		}

		_mutex.release();
	}

	@Override
	public void rifornisci() throws InterruptedException {

		_empty.acquire();
		_mutex.acquire();
		
		_numLattine = NUM_MAX_LATTINE;
		System.out.println("Rifornimento effettuato");
		
		_mutex.release();
	}

}
