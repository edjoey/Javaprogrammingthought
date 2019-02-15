package testCode.innerclasses;

import testCode.interfaceClass.Contents;
import testCode.interfaceClass.Destination;

/**
 * 嵌套类
 * 在不需要内部类对象与外围类对象之间有联系时，将内部类声明为static，称为嵌套类
 * @author joeyzhou
 * 1-创建嵌套类的对象，并不需要其外围类的对象
 * 2-不能从静态类的嵌套对象中访问其外围类非静态的对象
 * 区别:普通内部类的字段与方法，只能放在类的外部层次上，
 * 所以普通的内部类不能有static数据和字段，也不能包含嵌套类，但嵌套类可以包含所有这些。
 */
public class ParCel11 {

	private static class ParcelContents implements Contents{
		private int i = 11;
		@Override
		public int value() {
			return i;
		}
	}
	
	protected static class ParcelDestination implements Destination{
		private String label;
		
		private ParcelDestination(String whereTo) {
			label = whereTo;
			
		}

		@Override
		public String readLabel() {
			return label;	
		}
		
		//嵌套类可以包含其他的静态元素
		public static void f() {};
		static int x = 10; 
		static class AnotherLevel{
			public static  void f() {}
			static int x = 10;
		}
	}
	
	public static Destination destination(String s) {
		return new ParcelDestination(s);
		//ParcelDestination.AnotherLevel cLevel = new ParcelDestination.AnotherLevel();
	}
	
	public static Contents contents() {
		return new ParcelContents();
	}
	
	public static void main (String[] args) {
		Contents c = contents();
		Destination d = destination("fuck");
		System.out.println(d.readLabel());
		System.out.println(c.value());
		
	}
}
