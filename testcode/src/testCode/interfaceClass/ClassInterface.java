package testCode.interfaceClass;

/**
 * 接口内部的类
 * 嵌套类可作为接口的一部分，放到接口中任何的类都默认的为public，static
 * 因为类是static的，只是将嵌套类放到接口的命名空间内，不违反接口规则
 * @author joeyzhou
 *
 */
public interface ClassInterface {
	void howdy();
	
	//内部类实现其外围接口
	class Test implements ClassInterface{

		@Override
		public void howdy() {
			System.out.println("Howdy");
			
		}
		
		public static void main(String[] args) {
			new Test().howdy();
		}
		
	}

}
