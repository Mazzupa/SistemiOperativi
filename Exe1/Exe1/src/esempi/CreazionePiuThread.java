package esempi;

import java.util.ArrayList;

public class CreazionePiuThread {
	public static void main(String[] args) {

		ArrayList<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new myThread()));
		}

		for (Thread i : threads) {
			i.start();
		}
	}
}

class myThread implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());

	}

}
