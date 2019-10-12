package annotations.sql;

/**
 * 
 * @author joeyzhou
 *
 */
public @interface SQLInteger {
	
	String name() default "";
	
	Constraints constraints() default @Constraints;
}
