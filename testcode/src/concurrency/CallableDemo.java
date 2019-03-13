package concurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 执行任务获取返回值
 * @author joeyzhou
 *
 */
public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			results.add(exec.submit(new TaskWithResult(i)));
		}
		
		for(Future<String> fs : results) {
			try {
				if (fs.isDone()) {
					System.out.println(fs.get());	
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				exec.shutdown();
			}
		}
	}
	
	/**
	 * 在循环任务时，调用isDone()来获知Future是否已经完成，任务完成时只有一种结果。
	 * 调用get()来获取,如果不先调用isDone()直接获取,get()将会阻塞，直至完成任务
	 */
}
