package concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import concurrency.LiftOff;

/**
 * 创建一个单线程化的线程池,它只会用唯一的工作线程来执行任务，
 * 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
 * @author joeyzhou
 *
 */
public class SingleThreadExecutor {

	/**
	 * 如果向SingleThreadExecutor提交多个任务，这些任务将排队执行，
	 * 每个任务都将在下个任务开始之前结束，所有任务使用相同的线程，
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
	
	/**
	 * 执行后的输出结果可以看到，每个任务都是按提交的顺序执行输出。并且是在下一个任务
	 * 开始之前完成。因此SingleThreadExecutor会序列化所有提交给它的任务，
	 * 并会维护它自己(隐藏)的悬挂任务队列
	 * 
	 * 作为另一个示例，假设你有大量的线程，它们运行的任务将使用文件系统，你可以用
	 * SingleThreadExecutor来运行这些线程，以确保任意时刻在任何线程中都只有唯一的任务运行，
	 * 这种方式，使你不需要在共享资源上处理同步，当然有时更好的解决方案是在资源上同步，
	 * 但是SingleThreadExecutor可以让你省去只是为了维持某些事物的原型而做的各种协调努力，
	 * 通过序列化任务，你可以消除对序列化对象的需求
	 */
}
