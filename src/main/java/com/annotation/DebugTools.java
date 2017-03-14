package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * To assign the method that use for debug
 *
 * @author kamontat
 * @version 1.0
 * @since Tue 14/Mar/2017 - 1:03 PM
 */
@Target(ElementType.METHOD)
public @interface DebugTools {
	
}
