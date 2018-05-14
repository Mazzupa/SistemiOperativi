package counter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

	private static final int MAX = 5;

	public int _cont;

	private Lock _lock = new ReentrantLock();
	private Condition _notEmpty = _lock.newCondition();
	private Condition _notFull = _lock.newCondition();

	public Counter(int contInit) {
		_cont = contInit;
	}

	public void incrementa() throws InterruptedException {
		_lock.lock();

		try {
			while(_cont == MAX)
				_notFull.await();
			
			_cont++;
			
			
			_notEmpty.signal();
		} finally {
			_lock.unlock();
		}
	}

	public void decrementa() throws InterruptedException {
		_lock.lock();

		try {
			while(_cont == 0)
				_notEmpty.await();
			
			_cont--;

			_notFull.signal();
		} finally {
			_lock.unlock();
		}
	}
}
