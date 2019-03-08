package concurrency;

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

	public LiftOff() {}
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	
	public String status() {
		return "#"+ id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "). ";
	}
	
	@Override
	public void run() {
		while(countDown-- > 0) {
			System.out.print(status());
			/**
			 * 通知线程调度器，已执行完当前生命周期中重要部分，建议切换给其他任务执行一段时间。(这对线程调度器来说是可选择性的)
			 */
			Thread.yield();
		}
	}

}
