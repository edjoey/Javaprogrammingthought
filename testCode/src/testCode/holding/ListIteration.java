package testCode.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

/**
 * Listlterator -- Iterator的子类型
 * 用于各种list的访问，支持双向移动
 * 产生相对于迭代器在列表中指向当前位置的前一个和后一个索引
 * @author joeyzhou
 *
 */
public class ListIteration {
	public static void main(String[] args) {
		List<Pet> pets = Pets.arrayList(8);
		ListIterator<Pet> iListIteration = pets.listIterator();
		//forward
		while(iListIteration.hasNext()) {
			System.out.print(iListIteration.next() 
					+ ":" + iListIteration.nextIndex() 
					+ "," + iListIteration.previousIndex() + " : ");
			System.out.println();
				
		}
		// BackWards
		while (iListIteration.hasPrevious()) {
			System.out.print(iListIteration.previous().id() + "");
			System.out.println();
			System.out.println(pets.toArray());	
		}
		
		
		iListIteration = pets.listIterator(3);
		while (iListIteration.hasNext()) {
			iListIteration.next();
			iListIteration.set(Pets.randomPet());
		}
		System.out.println(pets);
		System.out.println();
		System.out.println();
		
		//12-3
		List<Integer> lista = 
				new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));		
		ListIterator<Integer> iterator = lista.listIterator();
		
		List<Integer> listb = new ArrayList<>(lista);
		ListIterator<Integer> iterator2 = listb.listIterator(listb.size());
		while (iterator.hasNext()) {
			Integer tNumer = iterator.next();
			iterator2.previous();
			iterator2.set(tNumer);
		}

		System.out.println(lista);
		System.out.println(listb);

	}
}
