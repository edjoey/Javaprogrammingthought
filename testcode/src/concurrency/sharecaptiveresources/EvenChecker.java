package concurrency.sharecaptiveresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试InGenerator
 * @author joeyzhou
 *
 */
public class EvenChecker implements Runnable{
	private InGenerator generator;
	private final int id;
	
	public EvenChecker(InGenerator g,int ident) {
		generator = g;
		id = ident;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + "not even!");
				generator.cancel(); //取消所有EvenChecker
			}
		}
	}
	
	public static void test(InGenerator gp,int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EvenChecker(gp, i));
		}
		exec.shutdown();
	}
	
	public static void test(InGenerator gp) {
		test(gp,100);
	}
}
