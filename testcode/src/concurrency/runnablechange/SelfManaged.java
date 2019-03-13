package concurrency.runnablechange;

/**
 * 自管理来实现Runnable
 * @author joeyzhou
 *
 */
public class SelfManaged implements Runnable{
	private int countDown = 5;
	private Thread t = new Thread(this);
	public SelfManaged() {
		t.start();
	}
	public String toString() {
		return Thread.currentThread().getName() + "(" + countDown + ").";
	}

	@Override
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
			new SelfManaged();
		}
	}
	
	/**
	 * 这个示例的写法与SimpleThread的Thread继承并没有什么特别的差异,但是实现接口可以让你多继承一个类
	 * 在这个示例中,start()是在构造器中调用的，这个示例很简单，看起来可能是安全的操作。
	 * 但应该意识到，在构造器中启动线程可能会有问题，因为另一个任务可能会在构造器结束前开始执行，
	 * 这意味着任务可能访问不稳定的对象，这样是为什么优选Executor而不是显示的创建Thread对象的另一个原因。
	 */
}
