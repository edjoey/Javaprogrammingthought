package testcode.innerclasses;


/**
 * 匿名内部类-使用外部定义对象
 * 通过实例初始化创建构造器效果
 * @author joeyzhou
 *
 */

/******************************************************************
 * 
 * 1.匿名内部类属于局部内部类,局部内部类的限制对匿名内部类同样生效
 * 2.匿名内部类可以用来拓展类，也可实现接口，但只能继承一个类或实现一个接口
 * 3.匿名内部类中使用外部参数为了保持一致性必须使用final确保行参的不可变
 * 4.匿名内部类中没有构造器，可以使用初始化来实现
 * 5.匿名内部类继承和实现的方法不可是抽象的，必须实现抽象方法
 * 6.匿名内部类中不允许使用static方法和成员,
 * 
 *@author joeyzhou
 ******************************************************************/
abstract class Base{

	public Base() {
		super();
	}
	public Base(int i) {
		System.out.println("Base constructor.i="+i);
	}
	public abstract void f();
}
public class AnonymousConstructor {

	/*
	 * getBase的i不要求是final，因为i被传递给匿名类的基本构造器
	 * 不会在匿名类内部使用，如果在内部使用则要求是final
	 */
	public static Base getBase(int i) {
		return new Base(i) {	
			{System.out.println("Inside instance initalizer");}
			@Override
			public void f() {
				System.out.println("In anonymous f()");
			}
		};
	}
	
	public static void main(String[] args) {
		Base base = getBase(88);
		base.f();
	}
	/**
	 * Output:
	 * Base constructor.i=88
	 * Inside instance initalizer
	 * In anonymous f()
	 */
}
