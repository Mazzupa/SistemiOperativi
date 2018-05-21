package bar;

public class Ospite extends Thread {

	Bar bar;

	public Ospite(Bar _bar) {
		bar = _bar;
	}

	@Override
	public void run() {
		while (true) {
			try {
				bar.entraOspite();
				Thread.sleep((long) (Math.random() * 50000));
				bar.esceOspite();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
