package testCode.innerclasses;

/**
 *内部类及.new
 *@author joeyzhou
 */
public class Parcel {
	private String testV = "fuck";
	
	class Contents{
		private int i = 11;
		public int value() { return i;}
		public int addValue() {return i + 1;}
	}
	
	class Destination{
		private String label;
		public Destination(String whereTo) {
			label = whereTo;
		}
		//内部类拥有其外围类所有成员及元素的访问权
		String readLabel() {return testV+" "+label;}
	}
	
	public Destination destination(String s) {
		return new Destination(s);
	}
	
//	public Contents contents() {
//		return new Contents();
//	}
	
	//使用内部类
	public void ship(String dest) {
		Contents c = new Contents();
		Destination d = new Destination(dest);
		System.out.println(d.readLabel());
		System.out.println(c.value());
 	}

	public static void main(String[] args) {
		Parcel p = new Parcel();
		p.ship("Tasmania");
		Parcel p1 = new Parcel();
		//Parcel.Contents c = p1.contents();	
		//使用.new来创建Contents的引用
		Parcel.Contents c = p.new Contents();
		Parcel.Destination d = p1.destination("Hi Joey");
		
		System.out.println(d.label);
		System.out.println(c.addValue());
	}
}
