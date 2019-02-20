package testcode.innerclasses;

import testcode.interfaceClass.Contents;
import testcode.interfaceClass.Destination;

/**
 * 内部类-向上转型
 * @author joeyzhou
 *
 */
class Pracel4{
	private class PContents implements Contents{
		private int i = 11;
		
		@Override
		public int value() { return i; }
	}
	protected class PDestination implements Destination{
		private String label;
		
		private PDestination(String whereTo) {
			label = whereTo;
		}
		
		@Override
		public String readLabel() { return label; }
		
	}
	public Destination destination(String s) {
		return new PDestination(s);
	}
	public Contents contents() {
		return new PContents();
	}
}
public class TestParcel {
	
	public static void main(String[] args) {
		Pracel4 pracel4 = new Pracel4();
		Contents contents = pracel4.contents();
		Destination destination = pracel4.destination("Fuck,Tasmain");
		System.out.println(contents.value());
		System.out.println(destination.readLabel());
	}
}
