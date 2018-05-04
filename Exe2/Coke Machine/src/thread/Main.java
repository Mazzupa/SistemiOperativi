package thread;

import java.util.ArrayList;

import distributore.*;

public class Main {
	public static void main(String[] args) {
		
		//Distributore m = new CokeMachineV1();
		//Distributore m = new CokeMachineV2();
		Distributore m = new CokeMachineV3();
		
		Fornitore f = new Fornitore(m);
		
		ArrayList<Consumatore> threads = new ArrayList<>();
		
		for(int i = 0; i<5; i++){
			threads.add(new Consumatore(m, "Thread " + i));
			threads.get(i).start();
		}
		
		f.start();
	}
}
