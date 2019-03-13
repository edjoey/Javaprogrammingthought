package concurrency.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程在不执行finally子句的情况下就会终止其run()
 * 记住在程序中的非后台线程都终止时，程序会杀掉所有的后台线程结束
 * @author joeyzhou
 *
 */

class ADaemon implements Runnable{
	public void run() {
		try {
			System.out.println("Starting ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			System.out.println("Exiting via InterruptedException");
		} finally {
			System.out.println("This should always run?");
		}
	}
}

public class DaemonsDontRunFinally {
	public static void main(String[] args) {
		Thread thread = new Thread(new ADaemon());
		thread.setDaemon(true);
		thread.start();
	}
	
	/**
	 * main()运行时,finally子句不会执行，如果注释掉setDaemon(),可以看到finally的执行输出
	 * 这种行为是正确的发生，即使你使用了finally，不希望出现这种情况，但它仍旧如此。当最后一个
	 * 非后台线程结束时，后台线程会突然停止，退出main(),JVM会立即关闭所有的后台进程，不会出现你所期望的结果。
	 * 关闭后台线程的方法使用非后台的Executor是一种更加优雅的方式，因为Executor控制的所有任务将同时关闭
	 */
}
