package concurrency.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * 使用ThreadFactory定制由Executor创建的线程属性
 * @author joeyzhou
 *
 */
public class DaemonThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		return thread;
	}
}
