package testcode.holding.queue;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 队列-典型的先进先出容器，从容器一端进入，另一端取出
 * 并且放入容器的顺序与取出的顺序是相同的。在并发编程中常使用。
 * @author joeyzhou
 *
 */
public class QueueDemo {

	/**
	 * LinkedList提供了方法来支持队列的行为，并且实现来Queue接口，
	 * 因此可把LinkedList用作Queue的一种实现，将LinkedList向上转型
	 */
	 public static void peintQ(Queue<?> queue) {
		 while (queue.peek() != null) {
			 System.out.print(queue.remove() + " ");
			 System.out.println();
		}
	 }
	 
	 /**
	  * peek()和element()都将在不移除都情况下返回队头，peek为空返回null，
	  * element()抛出NosuchElementException异常
	  * 
	  * poll()和remove()将移除并返回队头，poll()为空返回null，
	  * remove()抛出NosuchElementException
	  * @param args
	  */
	 public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		Random random = new Random(47);
		for (int i = 0; i < 10; i++) {
			 //将一个元素插入到队尾
			queue.offer(random.nextInt(i + 10));
		}
		System.out.println(queue);
		
		Queue<Character> qc = new LinkedList<>();
		for (char c : "Brontosaurus".toCharArray()) {
			qc.offer(c);
		}
		System.out.println(qc);
	 }
}
