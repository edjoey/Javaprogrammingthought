package annotations.sql;

/**
 * 包含其它的
 * @author joeyzhou
 *
 */
public @interface Uniqueness {

	Constraints constraints() default @Constraints(unique = true);
}
