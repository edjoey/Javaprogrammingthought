package concurrency.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 调用isDaemon确认线程是否后台线程
 * 如果是，那它创建的任何线程都自动设为后台线程 
 * @author joeyzhou
 *
 */

class Daemon implements Runnable{
	private Thread[] t = new Thread[10];
	public void run() {
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("DaemonSpwan " + i + " started. ");
		}
		
		for (int i = 0; i < t.length; i++) {
			System.out.println("t[" + i + "].isDaemon() = " +
					t[i].isDaemon() + ".");
		}
		while (true) {
			Thread.yield();
		}
	}
}

class DaemonSpawn implements Runnable{
	@Override
	public void run() {
		while (true) {
			Thread.yield();
		}
	}
}

public class Daemons {
	public static void main(String[] args) throws Exception{
		Thread dThread = new Thread(new Daemon());
		dThread.setDaemon(true);
		dThread.start();
		System.out.println("d.isDaemon() = " + dThread.isDaemon() + ". ");
		TimeUnit.SECONDS.sleep(1);
	}
}

/**
 * Daemon线程被设置为后台模式，然后派生出许多子线程，这些线程没有被显示设置为后台模式
 * 但确是后台线程，接着，Daemon线程进入死循环，在循环中调用yield()把控制权交给其他进程。
 */
