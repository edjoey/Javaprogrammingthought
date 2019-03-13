package concurrency;

import java.util.concurrent.Callable;

/**
 * 创建一个简单带返回值的任务并调用
 * @author joeyzhou
 *
 */
public class TaskWithResult implements Callable<String>{
	
	private int id;
	
	public TaskWithResult(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		return "result of TaskWithResult " + id;
	}

}
