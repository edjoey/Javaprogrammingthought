package testCode.innerclasses;

interface U{
	void a();
	void b();
	void c();
}

//类A方法返回匿名内部类
class UA{
	U aA() {
		return new U() {
			
			public void c() { System.out.println("U.c();"); }
			
			
			public void b() { System.out.println("U.b();"); }
			
			
			public void a() { System.out.println("U.a();"); }
		};
	}
}

class UB{
	private U[] uarr;
	
	public UB(int i) {
		uarr = new U[i];
	}
	
	void addUarr(U source,int i) {
		uarr[i] = source;
	}
	
	void setUarrNull(int i) {
		uarr[i] = null;
	}
	
	void forUarr() {
		for (U u : uarr) {
			u.a();
			u.b();
			u.c();
		}
	}
}

public class practice23 {
	public static void main(String[] args) {
		int size = 5;
		UA ua = new UA();
		UB ub = new UB(size);
		for (int i = 0; i < size; i++) {
			ub.addUarr(ua.aA(), i);
		}
		ub.forUarr();
		ub.setUarrNull(2);
	}
}
