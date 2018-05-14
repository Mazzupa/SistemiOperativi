package counter;

public class Dec extends Thread {

	private Counter _cont;

	public Dec(Counter c) {
		_cont = c;
	}

	public void run() {
		while (true) {
			try {
				System.out.println(currentThread().getName() + " " + _cont._cont);
				_cont.decrementa();
				sleep((long) (Math.random() * 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
