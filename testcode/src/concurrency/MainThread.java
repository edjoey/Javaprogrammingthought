package concurrency;

public class MainThread {
	
	/**
	 * 由分配给main()的线程驱动任务
	 * @param args
	 */
	public static void main(String[] args) {
		LiftOff liftOff = new LiftOff();
		liftOff.run();
	}
}
