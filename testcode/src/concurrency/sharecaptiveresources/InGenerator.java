package concurrency.sharecaptiveresources;

/**
 * 消息者任务创建，抽象实现解耦
 * @author joeyzhou
 *
 */
public abstract class InGenerator {

	//对象标示
	private volatile boolean cancled = false;
	
	public abstract int next();
	
	public void cancel() {
		cancled = true;
	}
	
	//确定对象是否已被取消
	public boolean isCanceled() {
		return  cancled;
	}
}
