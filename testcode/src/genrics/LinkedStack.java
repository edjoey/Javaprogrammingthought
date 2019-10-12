package genrics;

/**
 * 内部链式存储机制
 * @author joeyzhou
 *
 */
public class LinkedStack<T> {
	
	private static class Node<F>{
		F item;
		Node<F> next;
		Node(){
			item = null;
			next = null;
		}
		Node(F item,Node<F> next){
			this.item = item;
			this.next = next;
		}
		
		//末端哨兵机制
		boolean end() {
			return item == null && next == null;
		}
	}
	private Node<T> top = new Node<T>();
	
	public void push(T item) {
		top = new Node<T>(item,top);
	}
	
	public T pop() {
		T result = top.item;
		if (!top.end()) {
			top = top.next;
		}
		return result;
	}
	
	public static void main(String[] args) {
		LinkedStack<String> lss = new LinkedStack<>();
		for (String str : "K C U F".split(" ")) {
			lss.push(str);
		}
		String s;
		while ((s = lss.pop()) != null) {
			System.out.println(s);
		}
	}
	
}
