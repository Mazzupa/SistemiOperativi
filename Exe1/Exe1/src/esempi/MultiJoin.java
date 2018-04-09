package esempi;

import java.util.ArrayList;

public class MultiJoin {
	public static void main(String[] args) {

		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		for(int i = 0; i<10; i++)
			threads.add(new Thread(new tr()));
		
		for(Thread i : threads)
			i.start();
		
		 for(Thread i : threads)
			try {
				i.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		System.out.println("after join");
		
		
	}
}

class tr implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + (i+1));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}