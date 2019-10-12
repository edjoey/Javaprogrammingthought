package concurrency.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程(daemon)
 * 
 * 在程序运行的时候在后台提供一种通用服务的线程，这种线程在程序中不是必须存在的
 * 因此，当所有非后台线程结束时，程序也就终止了，同时干掉所有进程中的后台线程，
 * 反过来啰嗦一遍，就是只要有任何非后台线程还在运行，程序就不会终止。
 * 比如，执行一个main()就是一个非后台线程。
 * @author joeyzhou
 */
public class SimpleDaemons implements Runnable{
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (Exception e) {
			System.out.println("sleep() interrupted");
		}
	}

	public static void main(String[] args) throws Exception{
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(200);
	}
}
