package testcode.innerclasses;

/**
 * 内部类是否可覆盖？
 * @author joeyzhou
 *
 */

//废话不多说，直接上代码看结果
class Egg{
	private Yolk y;
	protected class Yolk{
		public Yolk() {
			System.out.println("Egg.Yolk()");
		}
	}
	public Egg() {
		System.out.println("New Egg");
		y = new Yolk();
	}
	
//	public void insertYolk(Yolk yolk) {
//		y = yolk;
//	}
}

public class BigEgg extends Egg{

//	public class Yolk extends Egg.Yolk{
//		public Yolk() {
//			System.out.println("BigEgg.Yolk()");
//		}
//	}
	
	public class Yolk {
		public Yolk() {
			System.out.println("BigEgg.Yolk()");
		}
	}
//	public BigEgg() {
//		insertYolk(new Yolk());
//	}
	public static void main(String[] args) {
		/**
		 * 在执行new BigEgg前，思考一个问题。
		 * 默认的构造器是编译器自动生成的，这里是调用基类的默认构造器。
		 * 既然创建了BigEgg的对象，那么使用的到底是覆盖后Yolk吗？
		 * 如果不是，是为什么？
		 */
		new BigEgg();
	}
	
	/* output：
	 * 
	 * New Egg
	 * Egg.Yolk()
	 */
	
	/**
	 * 看到输出结果后，应该已经知道了，两个内部类是完全独立的，分别处于在不同的命名空间内。
	 * 但是这并不能说明内部类是不可以覆盖，想覆盖内部类需要进行特定的实现，
	 * 
	 * 注意一下，被注释掉的那三段代码，会发现BigEgg中被注释的Yolk，明确的继承了Egg.Yolk。
	 * 并且覆盖了其中的方法，insertYolk()方法允许BigEgg2将它自己的Yolk对象向上转型为Egg2
	 * 的引用y。这时被覆盖的新版Yolk会被执行。
	 * 
	 * output：
	 * New Egg
	 * Egg.Yolk()
	 * Egg.Yolk()
	 * BigEgg.Yolk()
	 */
}
