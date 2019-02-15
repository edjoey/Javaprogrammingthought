package polymorphism;

/**
 * 基于多态机制实现可拓展性-Instrunment做接口
 * @author joeyzhou
 *
 */
public class Instrument {

	void play(Note n) {
		System.out.println("Instrument.play()");
	}
	
	String what() {
		return "Wind";
	}
	
	void adjust() {
		System.out.println("Adjusting Instrment");
	}
}
