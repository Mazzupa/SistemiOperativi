package cokeMachine;

public class Consumatore extends Thread {

	private Distributore _distributore;

	public Consumatore(Distributore d) {
		_distributore = d;
	}

	@Override
	public void run() {
		while (true) {
			try {
				_distributore.remove();
				System.out.println(currentThread().getName() + " Preleva lattina");
				sleep((long) (Math.random() * 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
