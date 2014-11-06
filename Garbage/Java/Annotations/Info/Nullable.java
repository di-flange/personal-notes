import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker that target element can be a null.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
@javax.annotation.Nullable
public @interface Nullable {
    
}
