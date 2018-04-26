package thread;

import contatore.MyCounter;
import utilities.SleepUtilities;

public class ThreadI implements Runnable {

	private MyCounter mc;

	public ThreadI(MyCounter _mc) {
		this.mc = _mc;
	}

	@Override
	public void run() {
		while (true){
			mc.incrementa();
			SleepUtilities.nap();
			System.out.println(mc.cont);

		}
	}

}
