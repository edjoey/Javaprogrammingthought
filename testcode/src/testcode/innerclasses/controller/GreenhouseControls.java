package testcode.innerclasses.controller;

/**
 * 一个特定的实现，控制灯光，水，温度调节器的开关，响玲及重新启动
 * 每个行为都是完全不同，控制框架使的分离这些代码很容易，使用内部类
 * 可在单一类里产生对同一个爸爸类(基类)Event的多种版本导出。
 * 
 * @author joeyzhou
 *
 */
@SuppressWarnings(value = { "all" })
public class GreenhouseControls extends ConntrollerRunner{
	
	/**
	 * 接下来每一个不同的行为，都将继承一个新的Event内部类，在要实现的action()中编写实现代码
	 */
	private boolean light = false;
	public class lightOn extends Event{
		private String OnMsg = "Light is on";
		public lightOn(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			//控制Code在这实现
			//Open light
			light = true;
		}
		
		public String toString () {return OnMsg; }
	}
	
	public class lightOff extends Event{
		private String offMsg = "Light is Off";
		public lightOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			light = false;
		}
		public String toString () {return offMsg;}
	}
	
	//水
	private boolean water = false;
	public class waterOn extends Event{
		private String OnMsg = "Greenhouse water is on";
		public waterOn(long delayTime) {
			super(delayTime);
		}
		@Override
		public void action() {
			//控制Code在这实现
			//Open light
			water = true;
		}
		
		public String toString () {return OnMsg; }
	}
	
	public class waterOff extends Event{
		private String offMsg = "Greenhouse water is Off";
		public waterOff(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			water = false;
		}
		public String toString () {return offMsg;}
	}
	
	//温度调节
	private String thermostat = "Day";
	public class ThermostatNight extends Event{
		private String offMsg = "Thermostat on Night Setting";
		public ThermostatNight(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			thermostat = "Night";
		}
		public String toString () {return offMsg;}
	}
	public class ThermostatDay extends Event{
		private String offMsg = "Thermostat on Day Setting";
		public ThermostatDay(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			thermostat = "Day";
		}
		public String toString () {return offMsg;}
	}
	
	//响铃
	public class Bell extends Event{

		public Bell(long delayTime) {
			super(delayTime);
		}

		@Override
		public void action() {
			//访问外围类的基本方法，追加事件调度
			//new one of itself into the event list
			//Bell控制响铃
			addEvent(new Bell(delayTime));
		}
		
		public String toString () {return "Bing!!";}
	}
	
	//重新启动
	public class Restart extends Event{
		private Event[] eventList;
		public Restart(long delayTime,Event[] eventList) {
			super(delayTime);
			this.eventList = eventList;
			for (Event e : eventList) {
				addEvent(e);
			}
		}

		@Override
		public void action() {
			for (Event e : eventList) {
				e.start(); //返回每个事件
				addEvent(e);
			}
			start();//return this Event
			//将Restart添加进去，进行有规律的重启
			addEvent(this);
		}
		
		public String toString() {
			return "Restarting system";
		}
	}
	
	//终止
	public static class Terminate extends Event{
		public Terminate(long delayTime) { 
			super(delayTime);
		}

		@Override
		public void action() {
			System.exit(0);
		}
		public String toString() {return "Terminating!";}
	}
}
