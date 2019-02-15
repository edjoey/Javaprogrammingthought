package testCode.holding;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * 
 * @author joeyzhou
 *
 */
public class LinkedListFeatures {

	public static void main(String[] args) {
		LinkedList<Pet> pets = new LinkedList<Pet>(Pet.arrayList(5));
	
		System.out.println(pets);
	}
}
