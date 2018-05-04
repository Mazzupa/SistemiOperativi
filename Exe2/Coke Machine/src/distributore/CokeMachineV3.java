package distributore;

import java.util.concurrent.Semaphore;

public class CokeMachineV3 implements Distributore {

	private Semaphore _mutex;
	private int _numLattine;
	private Semaphore _empty;
	private boolean _fornitoreAvvisato;

	public CokeMachineV3() {
		_mutex = new Semaphore(1);
		_numLattine = NUM_MAX_LATTINE;
		_empty = new Semaphore(0);
		_fornitoreAvvisato = false;
	}

	@Override
	public void preleva() throws InterruptedException {

		_mutex.acquire();

		if (_numLattine == 0) {
			if (!_fornitoreAvvisato) {
				System.err.println(Thread.currentThread().getName() + " Avvisa fornitore");
				_empty.release();
				_fornitoreAvvisato = true;
			}
		} else {
			_numLattine--;
			System.out.println(Thread.currentThread().getName() + " Preleva lattina, lattine rimaste: " + _numLattine);
		}

		_mutex.release();
	}

	@Override
	public void rifornisci() throws InterruptedException {

		_empty.acquire();
		_mutex.acquire();

		_numLattine = NUM_MAX_LATTINE;
		_fornitoreAvvisato = false;
		System.err.println("Rifornimento effettuato");

		_mutex.release();
	}

}
