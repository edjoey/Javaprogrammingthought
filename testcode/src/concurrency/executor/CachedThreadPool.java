package concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import concurrency.LiftOff;

/**
 * Executor
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 * 因此CachedThreadPool是Executor的首选,只有当这种方式引发问题时，你才需要考虑使用其他的
 * @author joeyzhou
 */
public class CachedThreadPool {
//	java.util.concurrent包中的执行器( Executor)将为你管理Thread对象,从而简化了并发编程。 
//	Executor在客户端和任务执行之间提供了一个间接层;与客户端直接执行任务不同,
//	这个中介对象将执行任务。Executor允许你管理异步任务的执行,而无须显式地管理线程的生命周期。 
//	Executor在 Java Se5/6中是启动任务的优选方法。
//	我们可以使用 Executor来代替在BasicThreads.MoreBasicThreads()中显示地创建Thread对象--Liftoff
//	对象知道如何运行具体的任务,与命令设计模式一样,它暴露了要执行的单一方法。
//	Executor Service(具有服务生命周期的 Executor,例如关闭)知道如何构建恰当的上下文来执行Runnable对象。
//	在下面的示例中,CachedThreadPool将为每个任务都创建一个线程。注意,ExecutorService对象是使用静态的Executor方法创建的,
//	这个方法可以确定其Executor类型:
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 2; i++) {
			exec.execute(new LiftOff());
		}
		/**
		 * 防止新任务提交给这个Executor,运行在执行shutdown()之前提交的任务。
		 * 在执行完所有的任务后将尽快退出。
		 */
		exec.shutdown();
	}
}
