package polymorphism;

/**
 * 向下转型-运行时类型识别
 * @author joeyzhou
 *
 */

/**
 * 继承关系中的导出类拥有其基类的接口，也可认为是一种对于基类的纯替代关系，通过导出类的向上转型，
 * 永远不需要关系正在处理对象的确切类型。在导出类基于基类的再次拓展时，导出类的拓展部分不能被基类所访问，
 * 也可看出向上转型是绝对安全的，但是为了解决导出类的拓展部分在转型时不能为访问的问题，
 * 可以用一种确保向下转型能知道正确类型的方式。
 * 在Java中所有的转型都会得到检查，以确保是我们所希望的那种类型， 不会出现想要个西瓜来个苹果的情况。
 * 
 * 在RTTI中可以看到注释A-1中，试图调用一个不存在于Userul的u(),u()只存在于Userul的导出类中，这时会编译错误。
 * 注释代码A-2中，已经确保了要转的类型，这时的向下转型是没问题的。
 *
 */
class Userul{
	void f() {}
	void g() {}
}

class MoreUseful extends Userul{
	void f() {}
	void g() {}
	void u() {}
	void e() {}
}
public class RTTI {

	public static void main(String[] args) {
		Userul[] ux = {
				new Userul(),
				new MoreUseful()
		};
		ux[0].f();
		ux[1].g();
		
		
		/**
		 * A-1
		 * 编辑时报错:Userul中找不到u
		 * ux[1].u();
		 */
		
		/**
		 *  A-2
		 *  
		 *   在知道转型类型，并确定没问题的时候进行向下转型
		 * 	((MoreUseful)ux[1]).u();
		 */
	
		/**
		 * 
		 * ClassCastException: polymorphism.Userul cannot be cast to polymorphism.MoreUseful
		 * ((MoreUseful)ux[0]).u();
		 */
	
	
	}
}
