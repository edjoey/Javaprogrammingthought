package testcode.innerclasses;

//Counter.class
interface Counter{
	int next();
}

/**
 * 局部内部类与匿名内部类的比较
 * @author joeyzhou
 * 重温一下，内部类可在代码块中创建，典型的用法是在一个方法内创建
 * 局部内部类不能有访问说明符，因为不是外围类的一部分，但可访问
 * 当前代码块内的常量，以及外围内的所有成员。
 */
@SuppressWarnings(value = { "all" })
public class LocalInnerClass {
	private int count = 0;
	
	Counter getCounter(final String name) {
		//local inner class
		//标示符---LocalInnerClass&LocalCounter.class
		class LocalCounter implements Counter {
			public LocalCounter() {
				System.out.println("LocalCounter()");
			}
			
			public LocalCounter(String name) {
				System.out.println(name);
			}
			@Override
			public int next() {
				System.out.println(name);
				return count++;
			}	
		}
		
		//return new LocalCounter("Hi Joey");
		return new LocalCounter();
	}
	
	//anonymous inner class
	Counter anonymousGetCounter(final String name) {
		/**
		 * 匿名内部类编译后，生成的class文件为LocalInnerClass&1.class
		 * 其外围类的名字加上&符和一个简单的数字作为其标示符
		 */
		return new Counter() {
			// 匿名内部类无命名，只有一个实例初始化器
			{
				System.out.println("Counter()");
			}
			@Override
			public int next() {
				System.out.println(name);
				return count++;
			}
		};
	}
	
	public static void main(String[] args) {
		LocalInnerClass lic = new LocalInnerClass();
		Counter
			c1 = lic.getCounter("Local inner "),
			c2 = lic.anonymousGetCounter("Anoymous inner ");
		for (int i = 0; i < 5; i++) {
			System.out.print(c1.next());
		}
		for (int i = 0; i < 5; i++) {
			System.out.print(c2.next());
		}
	}
	
	/**
	 * 以上用两个不同的方式实现了相同的功能，为什么使用局部内部类而不是匿名内部类？
	 * 当你需要一个已命名当构造器时，或想重载构造器时你需要使用局部内部类，
	 * 在需要不只一个该内部类对象时也同理。
	 * 前面提过了,匿名内部类只能用于实例初始化。
	 */
	
}
