package testcode.holding;

import testcode.holding.util.Stack;

/**
 * 演示Util包的Stock
 * @author joeyzhou
 *
 */
public class StackTest {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		for (String s : ("My dog has fleas").split(" ")) {
			stack.push(s);
		}
		
		while (!stack.empty()) {
			System.out.println(stack.pop() + " ");
		}
	}
}
