package genrics.coffee;

import java.util.Iterator;
import java.util.Random;

import genrics.Generator;

/**
 * 
 * @author joeyzhou
 *
 */
public class CoffeeGenerator implements Generator<Coffee>,Iterable<Coffee>{
	
	private Class[] types = {Latte.class,Breve.class,Cappuccino.class,Mocha.class};
	private static Random rd = new Random(47);
	private int size = 0;
	
	public CoffeeGenerator() {}
	
	public CoffeeGenerator(int sz) {
		size = sz;
	}
	
	@Override
	public Coffee next() {
		try {
			return (Coffee)types[rd.nextInt(types.length)].newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	class CoffeeIterator implements Iterator<Coffee>{
		int count = size;
		
		@Override
		public boolean hasNext() {
			return count > 0;
		}

		@Override
		public Coffee next() {
			count--;
			return CoffeeGenerator.this.next();
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public Iterator<Coffee> iterator() {
		return new CoffeeIterator();	
	}
	
	public static void main(String[] args) {
//		CoffeeGenerator gen = new CoffeeGenerator();
//		System.out.println(gen.next());
		
		for (Coffee coffee : new CoffeeGenerator(5)) {
			System.out.println(coffee);
		}
		 
		
	}
}
