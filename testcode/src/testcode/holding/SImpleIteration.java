package testcode.holding;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

/**
 * 迭代器
 * 1-轻量级对象
 * 2-只能单向移动(向前)
 * 3-将遍历序列的操作与序列底层的结构分离，统一对容器的访问方式。
 * @author joeyzhou
 *
 */
public class SImpleIteration {

	public static void main(String[] args) {
		List<Pet> pets = Pets.arrayList(1);
		HashSet<Pet> petsHS = new HashSet<>(pets);
		
		
		display(pets.iterator());
		display(petsHS.iterator());
	}
	
	public static void display(Iterator<Pet> iterator) {
		while(iterator.hasNext()) {
			Pet pet = iterator.next();
			System.out.print(pet.id() + ":" + pet + " ");
		}
		System.out.println();
	}
}
