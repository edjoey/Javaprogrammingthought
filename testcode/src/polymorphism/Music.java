package polymorphism;

/**
 * 基于多态机制实现可拓展性
 * @author joeyzhou
 *
 */
class Wind extends Instrument{
	void play(Note n) {
		System.out.println("Wind.play() " + n);
	}
	
	String waht() { return "Wind"; }
	void adjust() {System.out.println("Adjusting Wind");}
}

class Percussion extends Instrument{
	void play(Note n) {
		System.out.println("Percussion.play() " + n);
	}
	
	String waht() { return "Percussion"; }
	void adjust() {System.out.println("Adjusting Percussion");}
}

class Stringed extends Instrument{
	void play(Note n) {
		System.out.println("Stringed.play() " + n);
	}
	
	String waht() { return "Stringed"; }
	void adjust() {System.out.println("Adjusting Stringed");}
}

class Brass extends Wind{
	void play(Note n) {
		System.out.println("Brass.play() " + n);
	}
	void adjust() {System.out.println("Adjusting Brass");}
}

class WoodWind extends Wind{
	void play(Note n) {
		System.out.println("WoodWind.play() " + n);
	}
	String waht() { return "WoodWind"; }
}

public class Music {
	/**
	 * tune()不关心类型，新的类型依然可以添加正常运行。
	 * 所有继承自Instrument的代码修改，不会破坏到tune(),
	 * 这正是多态具有的特性。而所有基于Instrument的导出类
	 * 都能按虽期望那般产生正确的行为-向上转型.归功于动态绑定实现多态。
	 * @param i
	 */
	public static void tune(Instrument i) {
		i.play(Note.MIDDLE_C);
	}
	public static void tuneAll(Instrument[] e) {
		for(Instrument i : e) {
			tune(i);
		}
	}
	
	public static void main(String[] args) {
		Instrument[] orchestra = {
				new Wind(),
				new Percussion(),
				new Stringed(),
				new Brass(),
				new WoodWind()
		};
		tuneAll(orchestra);
	}
}
