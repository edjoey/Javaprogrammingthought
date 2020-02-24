package testcode.innerclasses;

import testcode.interfaceClass.Incrementable;

/**
 * 内部类闭包与回调Case
 * @author joeyzhou
 *
 */

class Callee1 implements Incrementable{
	private	int i = 0;	
	@Override
	public void increment() {
		i++;
		System.out.println(i);
	}
	
}

class MyIncrement{
	public void increment() { System.out.println("Other operation"); }
	static void f(MyIncrement mIncrement) {
		mIncrement.increment();
	}
	   protected void addCriterion(String condition) {
           if (condition == null) {
               throw new RuntimeException("Value for condition cannot be null");
           }
           System.out.println("OK");
       }
}

/**
 * 如果类必须以其他方式实现increment()。必须使用内部类,Callee2已经继承MyIncrement
 * 就不能为了Incrementable的用途而覆盖increment()方法，于是只能使用内部类独立实现
 * Incrementable。
 * 在Calle2中除了getCallbackReference外，所有的成员都是private的，通过
 * inteface Incrementable建立与外部的连接。
 */
class Callee2 extends MyIncrement{
	private int i = 0;
	public void increment() {
		super.increment();
		i++;
		System.out.println(i);
		
	}
	/**
	 * Closure实现Incrementable,提供返回一个Calle2的钩子，而且是一个安全的钩子，
	 * 无论谁获得了Incrementable的引用，都只能调用increment
	 */
	private class Closure implements Incrementable{
		@Override
		public void increment() {
			//指定外部方法，将会得到一个无限递归
			Callee2.this.increment();
		}	 
	 }
	 Incrementable getCallbackReference(){
		 return new Closure();
	 }
}

/**
 * Caller的构造器需要一个Incrementable的引用作为参数，然后在某个时刻，
 * Caller对象可以使用此引用回调Caller
 */
class Caller{
	private Incrementable callbackReference;
	public Caller(Incrementable cbh) {
		callbackReference = cbh;
	}
	void go() { callbackReference.increment(); }
}

public class Callbacks {

	public static void main(String[] args) {
		Callee1 c1 = new Callee1();
		Callee2 c2 = new Callee2();
		MyIncrement.f(c2);
//		Caller callee1 = new Caller(c1);
//		Caller callee2 = new Caller(c2.getCallbackReference());
//		callee1.go();
//		callee1.go();
//		callee2.go();
//		callee2.go();
	}
	
}
