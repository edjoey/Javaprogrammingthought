package testCode.innerclasses.controller;

/**
 * 内部类的继承中，内部类的构造器必须连接到其外围类对象的引用，
 * 问题在于，指向外围类的对象“秘密的”引用必须被初始化，而在导出类
 * 中不再存在可连接的默认对象，要解决问题，必须使用特殊的语法说明它们之间的关联。
 * @author joeyzhou
 *
 */
class WithInner{
	class Inner{}
}

public class GreenhouseController extends WithInner.Inner{
	   //GreenhouseController只继承自内部类,而不是外围类。但是要生成一个构造器时，
	  //默认的构造器并不算好，而且不能只是传递一个指向外围类对象的引用，必须在构造器内使用下面的语法
	  //wInner.super(); 这样才提供了必要的引用，才能编译通过。
	  public GreenhouseController(WithInner wInner) {
		  wInner.super();
	  }
	public static void main(String[]args) {
		//设置命令行
		String data[] = {"5000"};
		args = data;
		 
		GreenhouseControls gc = new GreenhouseControls();
		gc.addEvent(gc.new Bell(20000));
		Event[] events = {
				gc.new ThermostatNight(0),
				gc.new lightOn(20000),
				gc.new lightOff(40000),
				gc.new waterOn(60000),
				gc.new waterOff(80000),
				gc.new ThermostatDay(95000),
		};
		gc.addEvent(gc.new Restart(30000, events));
		//if (args.length == 1) {
			//gc.addEvent(new GreenhouseControls.Terminate(new Integer(args[0])));
			//gc.addEvent(new GreenhouseControls.Terminate(100000000));
			gc.runner();
			
		//}
	}

	/*output:
	 Bing!!
	 Thermostat is Day Setting
	 Light is on
	 Light is Off
	 Greenhouse water is on
	 Greenhouse water is Off
	 Thermostat is Day Setting
	 Restarting system
	 Terminating
	 */
}
