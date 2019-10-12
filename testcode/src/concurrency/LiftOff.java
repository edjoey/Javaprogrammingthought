package concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import testcode.DemoModel;

/**
 * 定义一个简单的任务
 * @author joeyzhou
 *
 */
public class LiftOff implements Runnable{
	protected int countDown = 10;
	private static int taskCount = 0;
	
	//区分任务的多个实例
	private final int id = taskCount++;
	
	List<List<DemoModel>> batchList = new ArrayList<>();
	

	public LiftOff() {
	   DemoModel model1 = new DemoModel();
	   model1.setId("aaa");
	   model1.setName("mok");
	   DemoModel model2 = new DemoModel();
	   model2.setId("bbb");
	   model2.setName("joey");
	   DemoModel model3 = new DemoModel();
	   model3.setId("aaa");
	   model3.setId("mok");
	   
	   batchList.add(Arrays.asList(model1));
	   batchList.add(Arrays.asList(model2));
	   batchList.add(Arrays.asList(model3));
	}
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	
	public String status() {
		return "#"+ id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "). ";
	}
	
	@Override
	public void run() {
		while(countDown-- > 0) {
			//System.out.println(status());
			System.out.println(countDown);
			/**
			 * 通知线程调度器，已执行完当前生命周期中重要部分，建议切换给其他任务执行一段时间。(这对线程调度器来说是可选择性的)
			 */
			Thread.yield();
		}
	}

}
