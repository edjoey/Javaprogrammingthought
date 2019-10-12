package concurrency;

class UnresponsiveUI{
	//volatile:值变更时的线程间的可见性
	private volatile double d = 1;
	public UnresponsiveUI() throws Exception {
		while (d > 0) {
			d = d + (Math.PI + Math.E) / d;
			System.in.read();
		}
	}
}

public class ResponsiveUI extends Thread{
	private static volatile double d = 1;
	
	public ResponsiveUI(){
		setDaemon(true);
		start();
	}

	public void run() {
		while (true) {
			d = d + (Math.PI + Math.E) / d;
		}
	}
	
	public static void main(String[] args) throws Exception{
		new ResponsiveUI();
		System.in.read();
		System.out.print(d);
	}
}
