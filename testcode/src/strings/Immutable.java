package strings;

/**
 * 不可变String
 * @author joeyzhou
 *
 */
public class Immutable {
	
	public static String upcase(String  str) {
		return str.toUpperCase();
	}

	public static void main(String[] args) {
		String q = "howdy";
		System.out.println(q);
		
		/**
		 * 将q传递给upcase时，实际传递的是引用的一个copy，每当把String对象作为方法参数时都会复制一份引用，
		 * 该引用所指向的对象一直未动
		 * 
		 * 再看upcase()的定义,引用进入upcase()时有了名字str,当upcase()运行时,局部引用str才存在,
		 * 运行结束str就消失,返回的也是一个最终结果的引用,该引用指向一个新的对象.
		 */
		String q2 = upcase(q);
		
		System.out.println(q2);
		System.out.println(q);
	}
}
