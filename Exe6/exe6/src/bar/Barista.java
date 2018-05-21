package bar;

public class Barista extends Thread{

	Bar bar;
	
	public Barista(Bar _bar){
		bar = _bar;
	}
	
	@Override
	public void run(){
		while(true){
			try {
				bar.apriBar();
				Thread.sleep(1500);
				bar.chiudeBar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
