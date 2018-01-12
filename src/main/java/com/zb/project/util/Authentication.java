package com.zb.project.util;

import java.lang.annotation.*;

/**
 * Created by tangshiwen on 2016/11/8.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authentication {
    boolean validate() default true;
}
