package thread;

import contatore.MyCounter;
import utilities.SleepUtilities;

public class ThreadD implements Runnable {

	private MyCounter mc;

	public ThreadD(MyCounter _mc) {
		this.mc = _mc;
	}

	@Override
	public void run() {
		while (true) {
			mc.decrementa();
			SleepUtilities. nap();
			System.out.println(mc.cont);

		}
	}

}
