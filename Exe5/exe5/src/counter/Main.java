package counter;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Counter c = new Counter(0);
		
		ArrayList<Thread> t = new ArrayList<>();
		for(int i = 0; i<10; i++){
			t.add(new Inc(c));
			t.get(i).setName("Inc " + i);
			t.add(new Dec(c));
			t.get(i).setName("Dec " + i);
		}
		
		for(Thread i : t)
			i.start();
	}
}
