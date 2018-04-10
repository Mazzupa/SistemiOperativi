/**
 * @author Mazzucchetti Patrick
 * @matricola 1053212
 */
package es1;

/**
 * Classe contente il Main() per testare la classe Fibonacci
 * 
 * ATTENZIONE: MAX Numero Calcolabile = 6998 (Per evitare StakOverflow)
 */
public class CalcolaFibonacci {
	public static void main(String[] args) {

		System.out.println(Fibonacci.get(6998));
		System.out.println(Fibonacci._get(6998));
		
		//Fibonacci.stampaSequenza(7);

	}
}
