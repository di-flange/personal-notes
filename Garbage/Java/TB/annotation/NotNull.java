package eu.aiskov.toolbox.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker that target element can't be null.
 */
@SuppressWarnings("UnusedDeclaration")
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
@javax.annotation.Nonnull
public @interface NotNull {
    
}