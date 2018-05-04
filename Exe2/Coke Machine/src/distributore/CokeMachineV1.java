package distributore;

import java.util.concurrent.Semaphore;

public class CokeMachineV1 implements Distributore {

	private Semaphore _mutex;
	private int _numLattine;
	private boolean _fornitoreAvvisato;

	public CokeMachineV1() {
		_mutex = new Semaphore(1);
		_numLattine = NUM_MAX_LATTINE;
		_fornitoreAvvisato = false;
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
			_fornitoreAvvisato = true;
		}

		_mutex.release();
	}

	@Override
	public void rifornisci() throws InterruptedException {

		_mutex.acquire();

		if (_fornitoreAvvisato) {

			_numLattine = NUM_MAX_LATTINE;
			System.out.println("Rifornimento effettuato");
			_fornitoreAvvisato = false;
		}
		_mutex.release();
	}

}
