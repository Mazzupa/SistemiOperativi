package bar;

import java.util.concurrent.locks.*;

public class BarStadio implements Bar {

	public static final int MAX = 25;

	private final Lock _lock = new ReentrantLock();
	private final Condition _barEmpty = _lock.newCondition();
	private final Condition _barNotFull = _lock.newCondition();
	private final Condition _barOpen = _lock.newCondition();
	private final Condition _sqadraOppostaInside = _lock.newCondition();

	private int _numOspiti = 0;
	private int _numLocali = 0;

	private boolean _isOpen = true;

	@Override
	public void entraOspite() {
		_lock.lock();
		try {
			while (!_isOpen)
				_barOpen.await();
			while(_numLocali != 0)
				_sqadraOppostaInside.await();
			while (_numOspiti + _numLocali == MAX)
				_barNotFull.await();

			_numOspiti++;
			System.out.println(Thread.currentThread().getName() + " Entra nel bar, Ospiti presenti: " + _numOspiti + " Locali presenti: " + _numLocali);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			_lock.unlock();
		}
	}

	@Override
	public void esceOspite() {
		_lock.lock();
		try {
			_numOspiti--;
			System.out.println(Thread.currentThread().getName() + " Esce dal bar, Ospiti presenti: " + _numOspiti + " Locali presenti: " + _numLocali);

			if (_numLocali + _numOspiti == 0) {
				_barEmpty.signal();
			}
			
			if(_numOspiti == 0)
				_sqadraOppostaInside.signal();

			_barNotFull.signal();

		} finally {
			_lock.unlock();
		}
	}

	@Override
	public void entraLocale() {
		_lock.lock();
		try {
			while (!_isOpen)
				_barOpen.await();
			while(_numOspiti != 0)
				_sqadraOppostaInside.await();
			while (_numOspiti + _numLocali == MAX)
				_barNotFull.await();

			_numLocali++;
			System.out.println(Thread.currentThread().getName() + " Entra nel bar, Ospiti presenti: " + _numOspiti + " Locali presenti: " + _numLocali);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			_lock.unlock();
		}
	}

	@Override
	public void esceLocale() {
		_lock.lock();
		try {
			_numLocali--;
			System.out.println(Thread.currentThread().getName() + " Esce dal bar, Ospiti presenti: " + _numOspiti + " Locali presenti: " + _numLocali);
			if (_numOspiti + _numLocali == 0) {
				_barEmpty.signal();
			}
			
			if(_numLocali == 0)
				_sqadraOppostaInside.signal();
			
			_barNotFull.signal();
		} finally {
			_lock.unlock();
		}
	}

	@Override
	public void chiudeBar() {
		_lock.lock();
		
		_isOpen = false;
		
		try {
			while (_numOspiti + _numLocali != 0) {
				_barEmpty.await();
			}

			System.out.println("chiude il bar");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			_lock.unlock();
		}
	}

	@Override
	public void apriBar() {
		_lock.lock();
		try {
			_isOpen = true;
			System.out.println("apre il bar");
			_barOpen.signal();
		} finally {
			_lock.unlock();
		}
	}
}
