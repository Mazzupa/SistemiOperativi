package esempi;

public class Main {
	public static void main(String[] args) {

		System.out.println(Thread.currentThread().getName());

		MyThread0 t;
		Thread t2;

		t = new MyThread0();
		t.start();

		t2 = new Thread(new MyThread2());
		t2.start();

	}

}

class MyThread0 extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}

class MyThread2 extends Main implements Runnable {

	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}
