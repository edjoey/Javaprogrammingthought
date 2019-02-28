package testcode.innerclasses;

import testcode.interfaceClass.Selector;

/**
 * 内部类链接外部类
 * @author joeyzhou
 *
 */
public class Sequence {
	private Object[] items;
	private int next = 0;
	
	//构造器初始化items
	public Sequence(int size) {items = new Object[size];}
	
	public void add(Object x) {
		if (next < items.length) {
			items[next++] = x;
		}
	}
	
	private class SequenceSelector implements Selector{
		private int num = 0;
		
		@Override
		public boolean end() { return num == items.length; }

		@Override
		public Object current() { return items[num];}

		@Override
		public void next() {
			if (num < items.length) {
				num++;
			}
		}
		
	}
	
	class toString{
		private String val = "fuckString";
		
		public void ShowToString() {
		  System.out.println("Tostring():"+val.toString());
		}	
		public void addItems() {
			add(val);
		}
	}
	
	public Selector selector(){
		return new SequenceSelector();
	}
	
	public toString showToString() {
		return new toString();
	}
	
	public static void main(String[] args) {
		Sequence sequence = new Sequence(11);
		for (int i = 0; i < 10; i++) {
			sequence.add(Integer.toString(i));
		}
		toString tString = sequence.showToString();
		tString.addItems();
		tString.ShowToString();
		Selector selector = sequence.selector();
		while (!selector.end()) {
			System.out.println(selector.current() + " ");
			selector.next();
		}	
	}
	
}
