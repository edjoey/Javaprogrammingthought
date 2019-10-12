package concurrency;

/**
 * 	join()
 * 	一个线程可以再其他线程之上调用join()方法,其效果是等待一段时间直到第二个线程结束，才继续执行。
 * 	如果某个线程在另一个线程t上调用t.join(),此线程将被挂起，直到目标线程t结束才恢复。
 *	在调用join()时还可带上一个超时参数，这样如果目标线程在这段时间到期时还没结束，join()总能返回。
 *	join()的调用可以被中断,做法是在调用线程上调用Interrupt()方法.
 *
 * @author joeyzhou
 */

/**
 * Sleeper为Thread类型,它会休眠一段时间，这个时间内通过构造器传进来的参数所指定的。
 * 在run()中，sleep()方法有可能在指定的时间期满时返回，也可能在被中断。catch语句中，
 * 根据isInterrupted()这个方法来报告这个中断，当另一个线程在该线程上调用interrupt()时,
 * 将给该线程设定一个标志,表明这个线程已经被中断。但是在异常被捕获时，这个标志又会被清理。
 * 所以在catch语句中，这个标志一直为false。
 * 
 * @author joeyZhou
 */
class Sleeper extends Thread{
	private int duration;
	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}
	
	public void run() {
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			//输出线程是否被中断的信息
			System.out.println(getName() + "was interrupted" + 
					"isInterrupted():" + isInterrupted());
		}
		System.out.println(getName() + " has awakened");
	}
}

/**
 * Joiner
 * 通过在Sleep对象上调用join()方法来等待Sleep醒来，在main中
 * 每个Sleep都有一个Joiner，在输出结果中可以发现，Sleeper要是被中断了或者正常结束
 * Joiner会和Sleeper一起结束
 * @author joeyZhou
 *
 */
class Joiner extends Thread{
	private Sleeper sl;
	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sl = sleeper;
		start();
	}
	
	public void run() {
		try {
			sl.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		System.out.println(getName() + " join completed");
	}
}

public class Joining {
	public static void main(String[] args) {
		Sleeper 
		sleepy = new Sleeper("Sleepy", 1500),
		grumpy = new Sleeper("Grumpy", 1500);
		Joiner
		dopey = new Joiner("Dopey", sleepy),
		doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();
	}

}
