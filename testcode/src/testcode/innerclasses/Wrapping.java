package testcode.innerclasses;


public class Wrapping  extends Parcel5{
	 String textS = "456"; 

	
	public Wrapping() {

	};
	
	private int i ;
	
	public Wrapping(int x) {
		i = x;
	}
	
	public int value() {
		return i;
	}
	
	public static void main(String[] args) {
		
		new Wrapping();
	}
}
