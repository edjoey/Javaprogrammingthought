package polymorphism;

/**
 * 构造器和多态
 * @author joeyzhou
 *
 */

class Meal{
	public Meal() {
		System.out.println("Fuck Meal");
	}
}

class Bread{
	public Bread() {
		System.out.println("Fuck Bread");
	}
}

class Cheese {
	public Cheese() {
		System.out.println("Fuck Cheese");
	}
}

class CheeseSon extends Cheese{
	public CheeseSon() {
		System.out.println("Fuck CheeseSon");
	}
}

//SandWich此时是三层继承关系(如果把来自Object的隐含继承也算在内，就是四层)以及三个成员对象。
public class SandWich extends CheeseSon{
	/**
	 * 尽管构造器不具有多态性，但还是有必要理解构造器怎么通过 多态在复杂的层次结构中运作。
	 * 1-基类的构造器总是在导出类的构造过程中被调用，而且按照继承顺序向上链接，
	 * 确保每一个构造器都能得到调用.构造器都有一项特殊任务--检查对象是否被正确的构造，
	 * 
	 */
	private Bread b = new Bread();
	private Cheese c = new Cheese();
	public SandWich() {
		System.out.println("Fuck SandWich");
	}
	public static void main(String[] args) {
		 new SandWich();
	}
	
	/**
	 * output:
	 	Fuck Cheese
		Fuck CheeseSon
		Fuck Bread
		Fuck Cheese
		Fuck SandWich
	 */
	
	/**
	 * 从输出结果可以看出，复杂对象调用构造器的遵循顺序如下
	 * 1）调用基类构造器，这个步骤会不断的反复递归下去，首先书构造这种层次结构的根，然后是导出类，直到最底层的导出类。
	 * 2）按声明顺序调用成员的初始化方法。
	 * 3）调用导出类构造器的主体。
	 */
	
	
}

