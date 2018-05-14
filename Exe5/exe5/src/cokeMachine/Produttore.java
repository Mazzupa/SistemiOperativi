package cokeMachine;

public class Produttore extends Thread {

	private Distributore _distributore;

	public Produttore(Distributore d) {
		_distributore = d;
	}

	@Override
	public void run() {
		while (true) {
			try {
				_distributore.insert();
				System.out.println("\n" + currentThread().getName() + " RIFORNIMENTO" + "\n");
				sleep((long) (Math.random() * 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
