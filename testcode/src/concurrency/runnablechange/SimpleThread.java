package concurrency.runnablechange;

/**
 * 之前的例子中所有的任务类都实现了Runnable
 * 在非常简单的情况下，可以直接从Thread继承这种可替换的方式
 * @author joeyzhou
 *
 */
public class SimpleThread extends Thread{
	private int countDown = 5;
	private static int threadCount = 0;
	public SimpleThread() {
		//将线程名存下来
		super(Integer.toString(++threadCount));
		start();
	}
	public String toString() {
		return "#" + getName() + "(" + countDown + ").";
	}
	
	public void run() {
		while (true) {
			System.out.println(this);
			if (--countDown == 0) {
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new SimpleThread();
		}
	}
}
