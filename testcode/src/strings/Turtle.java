package strings;

import java.io.PrintStream;
import java.util.Formatter;

/**
 * 创建Formatter对象使用
 * @author joeyzhou
 *
 */
public class Turtle {

	private String name;
	private Formatter f;
	
	/**
	 * 传递信息给Formatter的构造器,结果的输出位置
	 * @param name
	 * @param f
	 */
	public Turtle(String name,Formatter f) {
		this.name = name;
		this.f = f;
	}
	
	public void move(int x,int y) {
		f.format("%s The Turtle is at (%d,%d)\n", name,x,y);
	}
	
	public static void main(String[] args) {
		PrintStream outAias = System.out;
		Turtle tommy = new Turtle("Tommy", new Formatter(System.out));
		Turtle terry = new Turtle("Terry", new Formatter(outAias));
		tommy.move(0, 0);
		terry.move(4, 8);
	}
}
