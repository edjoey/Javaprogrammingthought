package concurrency.runnablechange;

import java.util.concurrent.TimeUnit;

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
			return getName() + " > " + countDown;
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
				while(true) {
					System.out.println(this);
					if (--countDown == 0) return;
					//sleep(100);
				}
			}
			public String toString() {
				return getName() + "------" + countDown;
			}
		};
		t.start();
	}
}

//命名的Runnable实现
class InnerRunnable{
	private int countDown = 5;
	private Inner inner;
	private class Inner implements Runnable{
		Thread thread;
		Inner(String name) {
			thread = new Thread(this,name);
			thread.start();
		}
		@Override
		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						return;
					}
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("Sleep() interrupted"); 
			}
		}
		public String toString() {
			return thread.getName() + ":" + countDown;
		}
	}
	
	public InnerRunnable(String name) {
		inner = new Inner(name);
	}
}

//匿名的Runnable实现
class InnerAnonymousRunnable{
	private int countDown = 5;
	private Thread t;
	public InnerAnonymousRunnable(String name) {
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {
						System.out.println(this);
						if (--countDown == 0) {
							return;
						}
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println("Sleep() interrupted");
				}
			}
			public String toString() {
				return Thread.currentThread().getName() + 
						": " + countDown;
			}
		},name);
		t.start();
	}
}

//在方法内部创建线程
class ThreadMethod{
	private int countDown = 5;
	private Thread thread;
	private String name;
	public ThreadMethod(String name) {
		this.name = name;
	}
	public void runTask() {
		if (thread == null) {
			thread = new Thread(name) {
				public void run() {
					try {
						while (true) {
							System.out.println(this);
							if (--countDown == 0) {
								return;
							}
							sleep(10);
						}
					} catch (InterruptedException e) {
						System.out.println("Sleep() interrupted");
					}
				}
				public String toString() {
					return getName() + ": " + countDown;
				}
			};
			thread.start();
		}
	}
}


public class ThreadVariations {
	public static void main(String[] args) {
		//new InnerNamedThread("fk,NamedThread");
		//new InnerAnonymousThread("fk,AnonymousThread");
		new InnerRunnable("fk,InnerRunnable");
	}

}
