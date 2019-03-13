package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 优先级
 * 
 * 优先级将线程的重要性传递给调度器，尽管cpu处理现有的线程集顺序是不确定的。
 * 但是调度器倾向于优先级高的线程先执行，优先级较低的线程执行频率也较低。
 * （线程的优先级不会导致死锁),多数的情况下线程都是按默认的优先级顺序运行。
 * @author joeyzhou
 */
public class SimplePriorities implements Runnable{
	private int countDown = 5;
	private volatile double d;
	private int priority;
	
	public SimplePriorities(int priority) {
		this.priority = priority;
	}
	public String toString() {
		return Thread.currentThread() + ":" + countDown;
	}
	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);
		while (true) {
			//开销高的可中断操作
			for (int i = 0; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double)i;
				if (i % 1000 ==0) 
					Thread.yield();
			}
			System.out.println(this);
			if (--countDown ==0)
				return;
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
			exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));	
		}
		exec.shutdown();
	}
}
