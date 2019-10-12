package annotations.use;

import annotations.ExtractInterface;

@ExtractInterface("IMultiplier")
public class Multipier {

	public int Multiply(int x,int y) {
		int total = 0;
		for (int i = 0; i < x; i++) {
			total = add(total, y);
		}
		return total;
	}
	
	private int add(int x,int y) {
		return x + y;
	}
	
	public static void main(String[] args) {
		Multipier m = new Multipier();
		System.out.println("11*16 = " +m.Multiply(11, 16));
	} 
}
