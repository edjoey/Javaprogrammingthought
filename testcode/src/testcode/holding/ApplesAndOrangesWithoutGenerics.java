package testCode.holding;

import java.util.ArrayList;

class Apples{
	private static Long count = 0L;
	private static Long id = count++; 
	public Long getId() {
		return id;
	}
	
}

class Orange{
	
}

public class ApplesAndOrangesWithoutGenerics {

	@SuppressWarnings("all")
	public static void main(String[] args) {
		
		ArrayList apples = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			apples.add(new Apples());
			//not prevented from adding an Orange to apples
			apples.add(new Orange());
		}
		
		for (int i = 0; i < 3; i++) {
			((Apples) apples.get(i)).getId();
		}
	}
	
	/**
	 * run time:
	 * Exception in thread "main" java.lang.ClassCastException: 
	 * testCode.holding.Orange cannot be cast to testCode.holding.Apples
	 * at testCode.holding.ApplesAndOrangesWithoutGenerics.main(ApplesAndOrangesWithoutGenerics.java:30)
	 */
}
