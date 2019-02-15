package testCode.innerclasses.controller;

/**
 * 控制事件通用方法
 * @author joeyzhou
 */
public abstract class Event {
	private long eventTime;
	protected final long delayTime;
	//允许Event，构造器捕获时间(从开始创建的时刻开始)
	public Event(long delayTime) {
		this.delayTime = delayTime;
	}
	
	//生成触发事件时间
	public void start() { //允许重新启动
		eventTime = System.nanoTime() + delayTime;
	}
	
	//通知是否可以启用action()方法
	public boolean ready() {
		return System.nanoTime() >= eventTime;
	}
	public abstract void action();
}
