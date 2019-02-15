package testCode.innerclasses;

import testCode.interfaceClass.Contents;
import testCode.interfaceClass.Destination;

/**
 * 内部类-局部内部类
 * @author joeyzhou
 *
 */
public class Parcel5 {
	
	public  Destination destination (String fuck) {
		class PDestination  implements Destination{
			private String label;
			private PDestination(String whereTo){
				label = whereTo;
			}
			@Override
			public String readLabel() {	
				return label;
			}
		}
		//这里PDestination尚上转型为其爸爸destination
		return new PDestination(fuck);
	}
	
	@SuppressWarnings("unused")
	private void internalTracking(boolean b) {
		if (b) {
			@SuppressWarnings("unused")
			class TrackingSlip{
			 	private String id;
				TrackingSlip(String s) {
					id = s;
				}
				String getSlip() {return id; }
			}
			TrackingSlip tSlip = new TrackingSlip("Fuck slip");
			String  sv = tSlip.getSlip();
			System.out.println(sv);
		}
	}
	
	public void track() {internalTracking(true);}
	
	//匿名内部类
	public Contents contents() {
		return new Contents() {
			private int fuckNum = 11;
			@Override
			public int value() {
				return fuckNum;
			}
		};
	}

	//匿名类基类参数构造器
	public Wrapping wrapping(int x) {
		return new Wrapping(x) {
			public int value() {
				return super.value() * 47;
			}
			};
	}
	
	//实例初始化
	public Destination anonymousDest(final String dest,final float price) {
		return new Destination() {
			private int cost;
			//Instance initalization for each object
			{
				cost = Math.round(price);
				if (cost > 100) {
					System.out.println("Over budget!");
				}
			}
			private String label = dest;
			@Override
			public String readLabel() {
				return label;
			}
		};
	}
	

	public static void main(String[] args) {
		Parcel5 p = new Parcel5();
		Destination d = p.destination("fuck,这是局部内部类的用法,通常用来协助方法解决一些复杂问题,"
						+ "PDestination是destination的一部分，不属于Parcel5"
						+ "现在返回的是destination的引用,因为destination是PDestination的爸爸");
		System.out.println(d.readLabel());
		p.track();
		
		System.out.println("匿名类基本参数构造器实现:"+p.wrapping(22));
		
		System.out.println(p.anonymousDest("fuck anonymousDest", 101.395F).readLabel());
	}
}
