package testCode.holding;

import java.util.LinkedList;

/**
 * 
 * @author joeyzhou
 *
 */
public class LinkedListFeatures {

	public static void main(String[] args) {
		LinkedList<MyPet> pets = new LinkedList<MyPet>(MyPet.arrayList(5));
	
		System.out.println(pets);
	}
}
