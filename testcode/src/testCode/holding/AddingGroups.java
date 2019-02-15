package testCode.holding;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


class Snow{}
class Powder extends Snow{}
class Light  extends Powder{}
class Heavy  extends Powder{}
class Crusty extends Snow{}
class Slush  extends Snow{}
public class AddingGroups {
	
	
	public static void main(String[] args) {
		Collection<Integer> collection = 
				new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6)); 
		
		Integer[] moreInts = {7,8,9};
		//addAll运行起来快很多,且很方便。
		collection.addAll(Arrays.asList(moreInts));
		
		Collections.addAll(collection, moreInts);
		
		/**
		 * Arrays.asList在这种情况下其底层是数组
		 */
		List<Integer> list = Arrays.asList(10,11,12,13,14);
		list.set(1, 99); // modify an element
		
		/**
		 * 运行时错误，因为基础数组无法调整大小
		 */
		//list.add(21);
		//list.remove(1);
		
//		GreenhouseControls gControls = new GreenhouseControls();
//		lightOn lOn =   gControls.new lightOn(2000);
//		System.out.println(lOn);
		
		List<Snow> snow1 = Arrays.asList(
				new Slush(),new Crusty(),new Powder());
		
		//发现:java.util.List<Powder>
		//需求:java.util.List<Snow>
		List<Snow> snow2 = Arrays.asList(new Light(),new Heavy());
		
		List<Snow> snow3 = new ArrayList<Snow>();
		Collections.addAll(snow3, new Light(),new Heavy());
		
		//显示参数类型说明
		List<Snow> snow4 = Arrays.<Snow>asList(new Light(),new Heavy());
	}

}
