/**
 * @author Mazzucchetti Patrick
 * @matricola 1053212
 */
package es1;

/**
 * BigInteger per la gestione dei grandi numeri di Fibonacci
 * HashMap come cache
 */
import java.math.BigInteger;
import java.util.HashMap;

/**
 * Classe che permette il calcolo dell'n-esimo numero di Fibonacci e la stampa
 * della sequenza fino all'n-esimo numero di Fibonacci In maniera classica
 * ricorsiva e multiThreading
 */
public class Fibonacci {
	/**
	 * Cache introdotta per diminuire il numero di chiamate ricorsive identiche
	 * (Programmazione dinamica)
	 */
	private static HashMap<Integer, BigInteger> cache = new HashMap<>();

	/**
	 * Stampa a video la sequenza fino all'n-esimo numero di Fibonacci (versione
	 * ricorsiva classica)
	 * 
	 * @param n
	 *            L'ultimo numero di Fibonacci da stampare
	 */
	public static void stampaSequenza(int n) {
		for (int i = 1; i <= n; i++)
			System.out.print(get(i) + " ");
	}

	/**
	 * Stampa a video la sequenza fino all'n-esimo numero di Fibonacci (versione
	 * multiThreading)
	 * 
	 * @param n
	 *            L'ultimo numero di Fibonacci da stampare
	 */
	public static void _stampaSequenza(int n) {
		for (int i = 1; i <= n; i++)
			System.out.print(_get(i) + " ");
	}

	/**
	 * Versione ricorsiva classica
	 * 
	 * @param n
	 *            Numero da calcolare
	 * @return n-esimo numero
	 */
	public static BigInteger get(int n) {
		if (n == 1)
			return BigInteger.ZERO;
		if (n == 2)
			return BigInteger.ONE;
		else {
			if (cache.containsKey(n)) // Uso della programmazione dinamica (Se il numero è già stato calcolato, è
				// inutle calcolarlo di nuovo)
				return cache.get(n);
			else {

				BigInteger n1 = get(n - 1);
				BigInteger n2 = get(n - 2);
				BigInteger num = n1.add(n2);

				cache.put(n, num); // Inserisco il numero calcolato nella cache per non ricalcolarlo di nuovo
				return num;
			}

		}
	}

	/**
	 * Versione multiThreading
	 * 
	 * @param n
	 *            Numero da calcolare
	 * @return n-esimo numero
	 */
	public static BigInteger _get(int n) {
		if (n == 1)
			return BigInteger.ZERO;
		if (n == 2)
			return BigInteger.ONE;

		if (cache.containsKey(n))
			return cache.get(n);
		else {

			// Creazione dei due thread per calcolare l'n-1 esimo e l'n-2 esimo di Fibonacci
			MyThread t1 = new MyThread(n - 1);
			MyThread t2 = new MyThread(n - 2);

			try {
				// Spawning dei Thread
				t1.start();
				t2.start();
				/**
				 * Per evitare eccezioni di OutOfMemoryError per via dei troppi thread creati Se
				 * il numero di Thread spawnati supera il limite massimo consentito dal sistema
				 * operativo passa alla versione ricorsiva classica
				 */
			} catch (java.lang.OutOfMemoryError e) {
				return get(n);
			}

			try {
				// Sync dei Thread per calcolare il risultato finale
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			BigInteger risultato = t1.getRisultato().add(t2.getRisultato());
			cache.put(n, risultato);
			return risultato;
		}
	}

}

/**
 * Classe per gestire i Thread per il calcolo della sequenza di Fibonacci
 */
class MyThread extends Thread {
	/**
	 * Numero di Fibonacci da calcolare
	 */
	private int n;
	/**
	 * n-esimo numero di Fibonacci calcolato
	 */
	private BigInteger risultato;
	/**
	 * Costruttore classe
	 * 
	 * @param _n
	 *            Numero di Fibonacci da calcolare
	 */
	public MyThread(int _n) {
		this.risultato = new BigInteger("0");
		this.n = _n;
	}

	/**
	 * Override della funzione run() della classe Thread Assegna a risultato
	 * l'n-esimo numero di Fibonacci calcolato
	 */
	@Override
	public void run() {
		risultato = Fibonacci._get(n);
	}

	/**
	 * @return n-esimo numero calcolato di Fibonacci
	 */
	public BigInteger getRisultato() {
		return this.risultato;
	}
}
