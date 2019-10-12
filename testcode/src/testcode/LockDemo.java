package testcode;

import java.util.stream.Stream;

import testcode.interfaceClass.TestLock;

class Timer implements TestLock{
private static int num = 0;
private String temp = "13721027893 | 17602135060";
public synchronized void add(String name){
    num++;
    try {
        Thread.sleep(1);
    } catch (InterruptedException e) {

    }
    String at = Stream.of(temp.replaceAll("\\s*", "").split("\\|")).reduce("", (a, b) -> a + "@" + b);
    System.out.println(at.trim());
    System.out.println(name +":你是第"+num+"使用timer的线程");
}
}

public class LockDemo implements Runnable{
	Timer timer = new Timer();
	public static void main(String[] args) {
		LockDemo t = new LockDemo();
	    Thread t1 = new Thread(t);
	    Thread t2 = new Thread(t);
	    t1.setName("t1");
	    t2.setName("t2");
	    t1.start();
	    t2.start();
	}
	public void run(){
		timer.add(Thread.currentThread().getName());
	}

}
