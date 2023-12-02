package ferrari_chris.serie05.soluzioniEs2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

@Target({METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface MarkdownDocIgnore {
    // Intentionally empty
}
