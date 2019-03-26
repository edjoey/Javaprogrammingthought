package testcode.holding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 容器的打印
 * @author joeyzhou
 *
 */
@SuppressWarnings("rawtypes")
public class PrintintContainers {

	/**
	 * Collection容器若一个坑来保存元素的话，它每个坑只能保存一个元素，
	 * 包括:List-特定顺序保存，Set-元素不可重复的保存,Queue 只允许在容器中一端插入对象，另一端移除对象。
	 * @param collection
	 * @return
	 */
	static Collection fill(Collection<String> collection) {
		collection.add("rat");
		collection.add("cat");
		collection.add("dog");
		collection.add("dog");
		return collection;
	}
	
	/**
	 * Map在每个坑里都有两个对象，即Key-Value之间关联的值,不必指定或考虑Map的尺寸，它会自动调整
	 * @param map
	 * @return
	 */
	static Map fill (Map<String, String> map) {
		map.put("rat", "Fuzzy");
		map.put("cat", "Rags");
		map.put("dog", "Bosco");
		map.put("dog", "Spot");
		return map;
	}
	
	public static void main(String[] args) {
		
		/**
		 * 以特定的顺序保存一组元素，从输出可以看出，元素都按照被插入的顺序保存
		 * ArrayList与LinkedList之间的不同不仅在于执行某些类型操作时的性能，
		 * LinkedList包含的操作也是多于LinkedList
		 */
		System.out.println(fill(new ArrayList<String>()));
		System.out.println(fill(new LinkedList<String>()));
		
		//HashSet的存储较复杂，也是最快的获取元素方式
		System.out.println(fill(new HashSet<String>()));
		//TreeSer按照比较结果的升序来保存对象
		System.out.println(fill(new TreeSet<String>()));
		//LinkedHashSet按照添加的顺序保存
		System.out.println(fill(new LinkedHashSet<String>()));
		
		//Key-Value存入HashMap的顺序并不是插入的顺序,它使用了自己的算法来控制顺序，也是最快的查找技术
		System.out.println(fill(new HashMap<String,String>()));
		//按照比较结果的升序保存键
		System.out.println(fill(new TreeMap<String,String>()));
		//LinkedHashMap按照插入顺序保存Key，同时保留来HashMap的查询速度
		System.out.println(fill(new LinkedHashMap<String,String>()));
	
	
	}
}
