package cokeMachine;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Distributore d = new Distributore();

		// Thread p = new Produttore(d);
		// p.setName("Produttore");
		// p.start();

		// ArrayList<Thread> cs = new ArrayList<Thread>();

		// for (int i = 0; i < 50; i++) {
		// cs.add(new Consumatore(d));
		// cs.get(i).setName("Consumatore " + i);
		// cs.get(i).start();
		// }

		Runnable c = () -> {
			while (true) {
				try {
					System.out.println(Thread.currentThread().getName() + " Preleva lattina");
					d.remove();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable p = () -> {
			while (true) {
				try {
					System.out.println("\nRIFORNIMENTO \n");
					d.insert();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Thread prod = new Thread(p);
		ArrayList<Thread> cons = new ArrayList<>();

		prod.start();
		for (int i = 0; i < 5; i++) {
			cons.add(new Thread(c));
			cons.get(i).setName("Consumatore " + i);
			cons.get(i).start();
		}

	}
}