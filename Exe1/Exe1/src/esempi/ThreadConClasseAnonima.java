package esempi;

public class ThreadConClasseAnonima {
	public static void main(String[] args) {

		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				try {
					for(int i =0; i<5; i++){
						System.out.println(i);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		};

		t.start();
		
		try {
			t.join();
			System.out.println("join");
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}
}
