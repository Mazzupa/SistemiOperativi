package bar;

import java.util.ArrayList;

public class Stadio {

	public static void main(String[] args) {

		BarStadio bar = new BarStadio();
		
		ArrayList<Thread> tifosiOspiti = new ArrayList<Thread>();
		ArrayList<Thread> tifosiLocali = new ArrayList<Thread>();
		Barista barista = new Barista(bar);
		barista.start();

		
		for (int i = 0; i < 10; i++) {
			tifosiLocali.add(new Locale(bar));
			tifosiLocali.get(i).setName("Locale " + i);
			tifosiOspiti.add(new Ospite(bar));
			tifosiOspiti.get(i).setName("Ospite " + i);
		}

		for (Thread i : tifosiLocali)
			i.start();
		for (Thread i : tifosiOspiti)
			i.start();

	}

}
