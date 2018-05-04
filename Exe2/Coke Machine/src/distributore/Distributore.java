package distributore;

public interface Distributore {

	static final int NUM_MAX_LATTINE = 10;
	
	public void preleva() throws InterruptedException;
	public void rifornisci() throws InterruptedException;
}
