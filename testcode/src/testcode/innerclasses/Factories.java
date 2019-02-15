package testCode.innerclasses;

/**
 * 工厂方法搭配匿名内部类
 * @author joeyzhou
 *
 */
interface Service{
	void methodO();
	void methodT();
}

interface ServiceFactory{
	Service getService();
}

class ImplementationO implements Service{
	private  ImplementationO() {};
	@Override
	public void methodO() {
		System.out.println("Fuck,ImplementationO methodO");
	}

	@Override
	public void methodT() {
		System.out.println("FUck,ImplementationO methodT");
	}
	
	public static ServiceFactory factory =
			new ServiceFactory() {
				@Override
				public Service getService() {
					return new ImplementationO();
				}
			};
}

class ImplementationT implements Service{
	private ImplementationT() {};
	@Override
	public void methodO() {
		System.out.println("Fuck,ImplementationT methodO");
		
	}

	@Override
	public void methodT() {
		System.out.println("Fuck,ImplementationT methodT");
	}
	
	public static ServiceFactory factory =
			new ServiceFactory() {
				
				@Override
				public Service getService() {
					return new ImplementationT();
				}
			};
}

public class Factories {
	
	public static void ServiceConsumer (ServiceFactory fact) {
		Service s = fact.getService();
		s.methodO();
		s.methodT();
	}
	
	public static void main(String[] args) {
		ServiceConsumer(ImplementationO.factory);
		ServiceConsumer(ImplementationT.factory);
	}

}
