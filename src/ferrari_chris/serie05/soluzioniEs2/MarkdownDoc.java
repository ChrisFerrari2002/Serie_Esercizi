package ferrari_chris.serie05.soluzioniEs2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MarkdownDoc {

    boolean parentClass() default true;

    boolean interfaces() default true;

    boolean fields() default true;

    boolean constructors() default true;

    boolean methods() default true;

}
