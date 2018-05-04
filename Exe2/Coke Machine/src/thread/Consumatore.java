package thread;

import distributore.Distributore;

public class Consumatore extends Thread {

	private Distributore _d;

	public Consumatore(Distributore d, String name) {
		this._d = d;
		setName(name);
	}

	@Override
	public void run() {
		while (true) {
			try {
				_d.preleva();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				sleep((int) (Math.random() * 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
