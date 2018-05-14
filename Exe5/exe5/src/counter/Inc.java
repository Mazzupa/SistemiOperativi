package counter;

public class Inc extends Thread {

	private Counter _cont;

	public Inc(Counter c) {
		_cont = c;
	}

	public void run() {
		while (true) {
			try {
				System.out.println(_cont._cont);
				_cont.incrementa();
				sleep((long) (Math.random() * 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
