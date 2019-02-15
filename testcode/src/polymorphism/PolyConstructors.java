package polymorphism;

/**
 * 构造器内部多态方法的行为
 * @author joeyzhou
 *
 * 构造器的复杂继承初始化调用层次结构与清理是相反的，清理时先从根的最底层导出类清理，然后尚上到基类。
 * 按照构造器的调用层次结构，试想一下如果在一个构造器的内部调用，正在构造的对象某构动态绑定方法，会发生什么情况？
 * 
 * 在一般的方法内部，动态绑定的调用在运行时决定，因为对象无法直到它是属于方法所在的类，还是该类的导出类。
 * 如果调用构造器内部的一个动态绑定方法，就要用到方法被覆盖后的定义，这种调用效果可能相当难于预料，
 * 因为被覆盖的方法在对象被完全构造之前就会被调用，这种错误一旦遇到是隐藏的很深的，搞不好会想秃你的小脑瓜子。
 * 
 * 概念上讲，构造器的工作实际上是创建对象，在任何构造器内部，整个内部，整个对象可能只是部分形成----⬇(说来那么多下面来重点)
 * 我们只知道基类的对象已经进行初始化，如果构造器只是在构造对象过程中的一个步骤，并且该对象所属的类是从这个构造器所属的类导出，
 * 那么导出部分在当前构造器正在被调用的时刻仍旧是没有初始化的,(回想一下构造器的复杂继承初始化顺序)
 * 一个动态绑定的方法调用却会向外深入到继承层次结构内部，它可以调用导出类里的方法，如果是在构咋器内部这样做，那么可能在调用某个方法时，
 * 这个方法操纵的成员还未进行初始化。
 * 
 * 举个例子:
 */
class Glyph{
	void draw() { System.out.println("Glyph.draw()"); }
	
	public Glyph() {
		System.out.println("Glyph before draw()");
		draw();
		System.out.println("Glyph after draw()");
	}
}

class RoundGlyph extends Glyph{
	private int redius = 1;
	public RoundGlyph(int r) {
		redius = r;
		System.out.println("RoundGlyph.RoundGlyph().redius = " + redius);
	}
	
	void draw() {
		System.out.println("RoundGlyph.draw().redius = " + redius);
	}
}
public class PolyConstructors {

	public static void main(String[] args) {
		new RoundGlyph(78);
	}
	/**
	 * output:
	 * 
	 	Glyph before draw()
		RoundGlyph.draw().redius = 0
		Glyph after draw()
		RoundGlyph.RoundGlyph().redius = 78
	 */
	
	/**
	 * 案例中可以看到，Glyph方法是可覆盖的，覆盖在RoundGlyph中发生，同时Glyph构造器会调用这个方法，结果是导致来对RoundGlyph.draw的调用。
	 * 如果这样的设计与调用是你期待的目的，那看看输出结果，在Glyph的构造器调用draw()时，redius不是初始化值1，是0。
	 * 
	 * 而产生这一现象的关键所在，可以通过初始化的实际过程说明:
	 * 1)在其他任何事物发生之前，将分配给对象的存储空间初始化成二进制的0，
	 * 2)如案例中那样调用基类构造器，此时，调用被覆盖的draw(),在RoundGlyph初始化之前调用，由于1)的原因，其值=0
	 * 3）按照声明的顺序调用成员的初始化方法。
	 * 4）调用导出类的构造器主体。
	 * 
	 * 在构造器中唯一能够安全调用的方法是声明为final的方法，private方法也同样适用，它自动属于final，这些方法不会被覆盖
	 * 因此不会出现上述问题。
	 */
}
