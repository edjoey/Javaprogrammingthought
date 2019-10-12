package annotations.sql;


/**
 * 指定字段类型
 * @author joeyzhou
 *
 */
public @interface SQLString {
	int value() default 0;
	
	String name() default "";
	
	Constraints constraints()default @Constraints;
}
