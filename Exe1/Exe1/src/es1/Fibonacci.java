package es1;

import java.util.HashMap;

public class Fibonacci {

	private static HashMap<Integer, Double> cache = new HashMap<>();

	public static void stampaSequenza(int n) {
		for (int i = 1; i <= n; i++)
			System.out.println(get(i));
	}

	public static void _stampaSequenza(int n) {
		for (int i = 1; i <= n; i++)
			System.out.println(_get(i));
	}

	public static Double get(int n) {
		if (n == 1)
			return 0.0;
		if (n == 2)
			return 1.0;
		else {
			if (cache.containsKey(n))
				return cache.get(n);
			else {
				Double num = get(n - 1) + get(n - 2);
				cache.put(n, num);
				return num;
			}

		}
	}

	public static Double _get(int n) {
		if (n == 1)
			return 0.0;
		if (n == 2)
			return 1.0;

		MyThread t1 = new MyThread(n - 1);
		MyThread t2 = new MyThread(n - 2);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return t1.risultato + t2.risultato;
	}

}

class MyThread extends Thread {

	int n;
	double risultato;

	public MyThread(int _n) {
		this.n = _n;
	}

	@Override
	public void run() {
		risultato = Fibonacci._get(n);

	}
}
