package concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import concurrency.LiftOff;

/**
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
 * @author joeyzhou
 *
 */
public class FixedThreadPool {
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
	
	/**
	 * FixedThreadPool,可以一次性预先执行高昂代价的线程分配，限制线程数量。
	 * 这可以节省时间，因为你不用为每个任务都固定的付出创建线程的开销，在事件驱动的系统中
	 * 需要线程的事件处理器，通过直接从池中获取线程，也可以如你所愿尽快得到服务，
	 * 也不会滥用可获得的资源，因为FixedThreadPool使用的Thread对象是有界的。
	 */
	
	/**
	 * 在任何线程池中，现有的线程在可能的情况下，都会被自动复用。
	 */
}
