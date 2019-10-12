package genrics;

import java.util.ArrayList;
import java.util.Random;

public class RandomList<T> {
	
	private ArrayList<T> storage = new ArrayList<>();
	private Random random = new Random(47);
	public void add(T item){	storage.add(item);	}
	public T select() {
		return storage.get(random.nextInt(storage.size()));
	}
	
	public static void main(String[] args) {
		RandomList<String> randomList = new RandomList<>();
		for (String str : ("使|用|泛|型|创|建|一|个|持|有|特|定|对|象|的|列|表|随|机|返|回|一|个|元|素").split("|")) {
			randomList.add(str);
		}
		
		for (int i = 0; i < 30; i++) {
			System.out.print(randomList.select() + " ");
		}
	}

}
