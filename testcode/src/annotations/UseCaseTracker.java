package annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import annotations.use.PasswordUtils;

/**
 * 反射获取类的方法和注解
 * @author joeyzhou
 *
 */
public class UseCaseTracker {

	public static void trackUseCases(List<Integer> useCases,Class<?> cl) {
		for (Method m : cl.getDeclaredMethods()) {
			UseCase uCase = m.getAnnotation(UseCase.class);
			if (uCase != null) {
				System.out.println("Found Use Case:" + uCase.id() + " " + uCase.description());
				useCases.remove(new Integer(uCase.id()));
			}
		}
		for (Integer i : useCases) {
			System.out.println("Warning Missing use case-" + i);
		}
	}
	
	
	public static void main(String[] args) {
		List<Integer> useCases = new ArrayList<>();
		Collections.addAll(useCases, 47,48,49,50);
		trackUseCases(useCases, PasswordUtils.class);
	}
}
