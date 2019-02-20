package testcode.innerclasses;

/****************************************************************************
 * 1> 为什么需要使用内部类？
 * 如果只是需要对一个接口的引用，在满足需求的情况下,理应通过外围类的方式来实现。
 * 但是内部类与外部类实现接口的区别在于外部类不是总能享受接口带来的便利性,虽然一个类
 * 可实现多个接口,但是使用内部类能独立继承实现某个接口，无论外围类是否已经继承了某个接口的实现。
 * 2> 内部类有效地实现类多重继承，使得多重继承的解决方案变的更完整，内部类允许继承
 * 更多非接口类型(类或抽象类)
 * 
 * 内部类的特性:
 * a>在内部类中有多个实例，每个实例都有自己的状态信息，且与其外围类对象的信息相互独立
 * b>在单个外围类中，可以让多个内部类以不同的方式实现同个接口或继承同个类，见case2
 * c>创建内部类对象的时刻不依赖外围类对象的创建
 **************************************************************************8/

/*
 * case1
 * 从多层嵌套内部类中访问外部类的成员
 * 一个内部类被嵌套多少层不重要，它能透明的访问所有它所嵌入的外围类的成员
 * @author joeyzhou
 *
 */
class MNA{
	private void f() {System.out.println("fuck MNA");}
	class A{
		private void g() {System.out.println("fuck A");}
		public class B{
			void h() {
				g();
				f();
			}
		}
	}
}


/*
 * case2
 * 在一个类中以某种方式实现两个接口
 * 使用单一类和内部类实现
 */
interface A{ 
	void printA();
}
interface B{
	void printB();
}

class X implements A,B{

	@Override
	public void printB() {
		System.out.println("fuck,X类实现接口B");
	}

	@Override
	public void printA() {
		System.out.println("fuck,X类实现接口A");
	}
}

class Y implements A{
	B makeB() {
		return new B() {
			@Override
			public void printB() {
				System.out.println("fuck,Y内部类实现接口B");
			}};
	}

	@Override
	public void printA() {
		System.out.println("fuck,Y单一实现接口A");
	}
}

/*
 * case3
 * 拥有抽象或具体的类只能使用内部类才能多重继承，不需要解决多重继承问题则用别的方式
 * 使用内部类可获取其他一些特性
 * 1)内部类可以有多个实例，每个实例都有自己的状态信息，并且与其外围类对象的信息相互独立
 * 2)在单个外围类中,可以让多个内部类以不同的方式实现同一个接口，或继承同一个类
 * 3)创建内部类对象的时刻并不依赖于外围类对象的创建
 * 4）内部类是一个独立的实体
 * 
 */
class D {}
abstract class E {}
class Z extends D {
	E makeE() { return new E() {}; }
}




public class MullNestingAccess {
	static void takesA(A a) { a.printA(); }
	static void takesB(B B) { B.printB(); }
	
	static void takesD(D d) {}
	static void takesE(E e) {}
	
	public static void main(String[] args) {
		MNA mna = new MNA();
		MNA.A mnaa = mna.new A();
		MNA.A.B mnab = mnaa.new B();
		mnab.h();
		
		X x = new X();
		Y y = new Y();
		takesA(x);
		takesA(y);
		takesB(x);
		takesB(y.makeB());
		

	}
	
	
}
