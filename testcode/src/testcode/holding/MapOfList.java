package testcode.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import typeinfo.pets.Cat;
import typeinfo.pets.Cymric;
import typeinfo.pets.Dog;
import typeinfo.pets.Mutt;
import typeinfo.pets.Person;
import typeinfo.pets.Pet;
import typeinfo.pets.Pug;
import typeinfo.pets.Rat;

/**
 * 使用Map假设跟踪拥有多个宠物的人
 * @author joeyzhou
 *
 */
public class MapOfList {
	
	public static Map<Person, List<? extends Pet>>
			petPeople = new HashMap<Person, List<? extends Pet>>();
	
	static {
		petPeople.put(new Person("Oawn"), 
				Arrays.asList(new Cymric("Molly"),new Mutt("Spot")));
		petPeople.put(new Person("Joey"), 
				Arrays.asList(new Cat("Elsie May"),new Dog("Margrett")));
		petPeople.put(new Person("Marilyn"),
				Arrays.asList(
						new Pug("Louie"),
						new Cat("stanford"),
						new Cat("pinkola")));
		petPeople.put(new Person("Luke"),
				Arrays.asList(new Rat("Fuzzy"),new Rat("Fizzy")));
		petPeople.put(new Person("Isaac"),Arrays.asList(new Rat("Freckly")));
	}

	public static void main(String[] args) {
		System.out.println("People:" + petPeople.keySet());
		System.out.println("Pets:" + petPeople.values());
		
		for (Person person : petPeople.keySet()) {
			System.out.println(person + "has:");
			for (Pet pet : petPeople.get(person)) {
				System.out.println("   " + pet);
			}
		}
	}
}
