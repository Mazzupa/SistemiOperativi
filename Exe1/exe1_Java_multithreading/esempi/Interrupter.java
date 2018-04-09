package esempi;

public class Interrupter {
	public static void main(String[] args) {
		
		Thread worker = new Thread(new InterruptibleThread());
		worker.start();

		System.out.println("Thread main: terminazione differita del thread figlio...");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		worker.interrupt();
	}
}
