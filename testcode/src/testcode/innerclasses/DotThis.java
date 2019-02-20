package testcode.innerclasses;

/**
 * 内部类-.this .new
 * 
 * @author joeyzhou
 *
 */
public class DotThis {
	void f() {System.out.println("Dothis.f()");}

	public class Inner{
		public DotThis outer() {
			return DotThis.this;
		}
	}
	
	public class InnerA{
		public void wel() {
			System.out.println("fuck.New");
		}
	}
	
	public Inner inner() { return new Inner();}
	
	public static void main(String[] args) {
		DotThis dThis = new DotThis();
		DotThis.Inner dtInner = dThis.inner();
		dtInner.outer().f();
		DotThis.InnerA dInnerA = dThis.new InnerA();
		dInnerA.wel();
	}
	/**
	 * Output:
	 * Dothis.f()
	 * fuck.New
	 */
}
