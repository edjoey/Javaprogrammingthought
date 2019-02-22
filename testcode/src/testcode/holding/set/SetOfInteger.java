package testcode.holding.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Set
 * 
 * 存储元素方式:
 * HashSet——散列函数(无序)
 * TreeSet——红黑数结构(有序)
 * LinkeHashSet-散列函数，使用了链表来维护插入顺序
 * 
 * Set不保存重复的元素，将相同的对象的多个实例添加到set中，它会阻止这种重复现象
 * Set最常被使用的是测试归属性，你可以很容易的查询某个对象是否在某个Set中,查找也是Set中最重要的操作
 * HashSet专门对快速查找做了优化
 * 
 * Set具有与Collectin完全一样的接口，因此没有额外的功能,实际上set就是Collection，只是行为不同
 * 这也是继承与多态思想的典型应用-----表现不同的行为。
 * Set基于对象的值来确定归属性。
 * @author joeyzhou
 *
 */
public class SetOfInteger {
	public static void main(String[] args) {
		Random random = new Random(47);
		Set<Integer> intSet = new HashSet<>();
		for (int i = 0; i < 10000; i++) {
			intSet.add(random.nextInt(30));
		}
		/**
		 * 输出结果中没有重复的数字
		 * 且因为HashSet出于速度的考虑，使用了散列所以输出顺序没有任何规律可循
		 * 但是在JDK1.8中去运行看到的输出结果是有序的,下方链接可以解释原因
		 * https://www.zhihu.com/question/28414001/answer/40733996
		 */
		System.out.println(intSet);
	}
}
