package cokeMachine;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Distributore {

	public static final int MAX_LATTINE = 50;
	private int _numLattine;

	private Lock _lock = new ReentrantLock();
	private Condition _notEmpty = _lock.newCondition();
	private Condition _empty = _lock.newCondition();

	public Distributore() {
		_numLattine = MAX_LATTINE;
	}

	public void remove() throws InterruptedException {
		_lock.lock();
		try {
			
			while(_numLattine == 0){
				_empty.signal();
				_notEmpty.await();
			}
			
			_numLattine--;
			System.out.println("Lattine rimaste: " + _numLattine);
			
		} finally {
			_lock.unlock();
		}
	}

	public void insert() throws InterruptedException {
		_lock.lock();
		try {
			while(_numLattine != 0){
				_empty.await();
			}
			_numLattine = MAX_LATTINE;
			_notEmpty.signal();
		} finally {
			_lock.unlock();
		}
	}

}
