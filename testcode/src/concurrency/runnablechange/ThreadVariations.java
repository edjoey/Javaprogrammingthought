package concurrency.runnablechange;


/**
 * 有时通过使用内部类将线程代码隐藏在类中会很有用，像这样
 * @author joeyzhou
 *
 */

//使用命名的内部类
class InnerNamedThread{
	private int countDown = 5;
	private Inner inner;
	private class Inner extends Thread{
		public Inner(String name) {
			super(name);
			start();
		}
		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) 
						return;
					sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}
		public String toString() {
			return getName() + "> " + countDown;
		}
	}
	public InnerNamedThread(String name) {
		inner = new Inner(name);
	}	
}

//使用匿名内部类
class InnerAnonymousThread{
	private int countDown = 5;
	private Thread t;
	public InnerAnonymousThread(String name) {
		t = new Thread(name) {
			public void run() {
				try {
					while(true) {
						System.out.println(this);
						if (--countDown == 0) return;
						sleep(100);
					}
				} catch (InterruptedException e) {
					System.out.println("sleep() interrupted");
				}
			}
			public String toString() {
				return getName() + "------" + countDown;
			}
		};
		t.start();
	}
}


public class ThreadVariations {
	public static void main(String[] args) {
		InnerAnonymousThread innerAnonymousThread = new InnerAnonymousThread("fuck,run()");
	}

}
