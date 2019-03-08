package concurrency;

//线程类最基本的用法
public class BasicThreads {

	public static void main(String[] args) {
		
		//使用Thread来驱动LiftOff对象
//		Thread t = new Thread(new LiftOff());
//		t.start();
//		System.out.println("Waiting for LiftOff");
		/**
		 * Output:
		   Waiting for LiftOff
			#0(9). 
			#0(8). 
			#0(7). 
			#0(6). 
			#0(5). 
			#0(4). 
			#0(3). 
			#0(2). 
			#0(1). 
			#0(LiftOff!). 
		 * 
		 */
		
		/**
		 *	Thread构造器只需要一个 Runnable对象。调用Thread对象的start()方法为该线程执行必需的初始化操作,
		 *	然后调用Runnable的run0方法,以便在这个新线程中启动该任务。尽管start()看起来是产生了一个对长期运行方法的调用,
		 *	但是从输出中可以看到,start()迅速地返回了,因为WaitingforLiftoFf消息在倒计时完成之前就出现了。
		 *	实际上,你产生的是对LiftOff.run()的方法调用,并且这个方法还没有完成,但是因为 LiftOff.run()是由不同的线程执行的,
		 *	因此你仍旧可以执行main()线程中的其他操作(这种能力并不局限于 maino线程,任何线程都可以启动另一个线程)。
		 *	因此,程序会同时运行两个方法,main()和LiftOff.run()是程序中与其他线程“同时"执行的代码。
		 */
		
		
		//当然还可以很容易地添加更多的线程去驱动更多的任务,下面的Case,你可以看到所有任务彼此之间的呼应。
		MoreBasicThreads();
	}
	
	public static void MoreBasicThreads() {
		for (int i = 0; i < 5; i++) {
			new Thread(new LiftOff()).start();
			System.out.println("Waiting for LiftOff");
		}
		
		/**
		 * 执行后看到的输出结果说明不同任务的执行在线程被换进换出时混在了一起,这种交换是由线程调度器自动控制。
		 * 如果在你的机器上有多个处理器时，线程调度区会在这些处理器直接默默的发放线程
		 * 每次运行的输出结果都可能不同，因为线程调度机制是非确定性的
		 * 如果用较早的jdk版本与jdk1.7以上的版本进行比较，输出的结果差异会很大。
		 */
		
	}
}
