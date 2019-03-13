package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 休眠
 * 
 * 影响任务简单的一种方式就是调用sleep()，这会使任务中止执行给定的时间
 * 重写LiftOff中的run()方法，将yeild()替换成sleep()，可以明显的看到输出的差别
 * @author joeyzhou
 *
 */
public class SleepingTask extends LiftOff{
	
	public void run() {
		try {
			while (countDown-- > 0) {
				System.out.println(status());
				//JDKSE5前的休眠写法
				Thread.sleep(100);
				//TimeUnit.MICROSECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println("Interrupted");
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SleepingTask());
		}
		exec.shutdown();
	}
	
	/**
	 * 对sleep的调用会抛出InterruptedExceptio异常，异常不能跨线程传播回Main(),只能在本地进行处理。
	 * 每一个输出语句执行后，线程都会睡眠(阻塞),使得线程调度器可以切换到另一个线程，驱动其他任务，
	 * 输出的顺序依赖于底层的线程机制，这种机制在不同的OS直接是有差异的。因此这种不能依赖它去控制任务执行顺序。
	 * 如果想控制任务执行顺序，最好的方法就是使用同步控制。
	 */
}
