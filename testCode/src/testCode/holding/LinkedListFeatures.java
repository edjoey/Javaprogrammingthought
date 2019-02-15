package testCode.holding;

import java.util.LinkedList;

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

/**
 * 
 * @author joeyzhou
 *
 */
public class LinkedListFeatures {

	public static void main(String[] args) {
		LinkedList<Pet> pets = new LinkedList<Pet>(Pets.arrayList(5));
	
		System.out.println(pets);
	}
}
