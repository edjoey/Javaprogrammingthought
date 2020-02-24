package testcode.holding.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * 先进后出的容器-栈(Stock)
 * 有时栈也被成为叠加栈，因为最后一根压入栈的元素，第一个弹出
 * LinkedList具有能够直接实现栈的所有功能方法，因此可以直接将LinkedList作为栈使用。
 * @author joeyzhou
 * @param <T>
 *
 */
public class Stack<T> {
	
	/**
	 * 定义一个持有泛型T对象的Stock,用LinkedList实现
	 */

	private LinkedList<T> storage = new LinkedList<T>();
	
	/**
	 * 接受一个T类型对象
	 * @param vT
	 */
	public void push(T vT) {
		storage.addFirst(vT);
	}
	
	/**
	 * 返回栈顶元素，并不移除
	 * @return
	 */
	public T peek() {
		return storage.getFirst();
	}
	
	/**
	 * 返回并移除栈顶元素
	 * @return
	 */
	public T pop() {
		return storage.removeFirst();
	}
	
	public boolean empty() {
		return storage.isEmpty();
	}
	
	public String toString() {
		return storage.toString();
	}
}
