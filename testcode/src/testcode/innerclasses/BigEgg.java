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
	
	public void insertYolk(Yolk yolk) {
		y = yolk;
	}
}

public class BigEgg extends Egg{

	public class Yolk extends Egg.Yolk{
		public Yolk() {
			System.out.println("BigEgg.Yolk()");
		}
	}
	
//	public class Yolk {
//		public Yolk() {
//			System.out.println("BigEgg.Yolk()");
//		}
//	}
//	
	public BigEgg() {
		insertYolk(new Yolk());
	}
	public static void main(String[] args) {
		/**
		 * 在执行new BigEgg前，思考一个问题。
		 * 默认的构造器是编译器自动生成的，这里是调用基类的默认构造器。
		 * 既然创建了BigEgg的对象，那么使用的到底是覆盖后Yolk吗？
		 * 如果不是，是为什么？
		 */
		new BigEgg();
	}
	
	
}
