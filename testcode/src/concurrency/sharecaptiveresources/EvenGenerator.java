package concurrency.sharecaptiveresources;

public class EvenGenerator extends InGenerator{
	private int currentEvenValue = 0;

	@Override
	public int next() {
		++currentEvenValue;
		//测试更快的发现问题;
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			EvenChecker.test(new EvenGenerator());
		}
	}
}
