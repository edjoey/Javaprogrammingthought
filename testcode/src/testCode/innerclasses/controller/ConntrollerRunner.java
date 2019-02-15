package testCode.innerclasses.controller;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法调度运行
 * @author joeyzhou
 *
 */
public class ConntrollerRunner {

	private List<Event> eventList = new ArrayList<>();
	
	/**
	 * 追加调度的方法
	 * @param event
	 */
	public void addEvent(Event event) {
		eventList.add(event);
	}
	
	/**
	 * 循环调度的方法，找到准备就绪的开始执行
	 */
	public void runner() {
		while(eventList.size( ) > 0){
			for (Event e : new ArrayList<>(eventList)) {
				if (e.ready()) {
					System.out.println(e);
					e.action();
					eventList.remove(e);
				}
			}	
		}
	}
	
	/**
	 * 在目前为止,并不知道Event到底会做什么，这正是内部类控制框架的设计关键，
	 * "使变化的事物与不变的事物相互分离","变化向量"就是各种Event对象所具有的不同行为。
	 * 通过创建不同的Event子类来表现不同的行为，这正是内部类做的东西，内部类允许:
	 * 
	 * 1)控制框架的完整实现是由单个的类创建的，从而使实现的细节被封装起来,内部类用来表示
	 * 解决问题所必需的不同action()
	 * 2)如果简单的了解过内部类的话，应该会知道内部类可以很容易的访问外围类的任意成员。
	 */
}
