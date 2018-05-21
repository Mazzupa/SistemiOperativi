package bar;

public class Locale extends Thread {

	Bar bar;

	public Locale(Bar _bar) {
		bar = _bar;
	}

	@Override
	public void run() {
		while (true) {
			try {
				bar.entraLocale();
				Thread.sleep((long) (Math.random()*50000));
				bar.esceLocale();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
