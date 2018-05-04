package thread;

import distributore.Distributore;

public class Fornitore extends Thread{

	private Distributore _d;
	
	public Fornitore(Distributore d){
		this._d = d;
	}
	
	@Override
	public void run(){
		while(true){
			try {
				_d.rifornisci();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
