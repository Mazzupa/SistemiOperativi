package esempi;

import java.util.ArrayList;
import java.util.List;

public class SommaArray {

	public static final int MAX_ELEM = 100000;

	public static void main(String[] args) {

		ArrayList<Integer> valori = new ArrayList<Integer>();
		ArrayList<MyInteger> somma = new ArrayList<MyInteger>();

		for (int i = 0; i < MAX_ELEM / 10; i++)
			somma.add(new MyInteger());

		for (int i = 0; i < MAX_ELEM; i++)
			valori.add(i + 1);

		ArrayList<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < MAX_ELEM; i += 10) {
			threads.add(new Thread(new Somma(valori.subList(i, i + 10), somma, i / 10)));
		}

		for (Thread i : threads)
			i.start();

		for (Thread i : threads)
			try {
				i.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		int finalSomma = 0;
		for (MyInteger i : somma)
			finalSomma += i.getRisultato();

		System.out.println(finalSomma);

	}
}

class Somma implements Runnable {

	List<Integer> valori;
	ArrayList<MyInteger> somma;
	int id;

	public Somma(List<Integer> _valori, ArrayList<MyInteger> _somma, int _id) {
		this.valori = _valori;
		this.somma = _somma;
		this.id = _id;
	}

	@Override
	public void run() {
		int _somma = 0;
		for (int i : valori) {
			_somma += i;
		}
		System.out.println(_somma);
		MyInteger in = new MyInteger();
		in.setRisultato(_somma);
		this.somma.set(id, in);
	}
}

class MyInteger {
	int x;

	public int getRisultato() {
		return x;
	}

	public void setRisultato(int _x) {
		this.x = _x;
	}

}
