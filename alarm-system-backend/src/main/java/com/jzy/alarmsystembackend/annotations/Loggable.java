package com.jzy.alarmsystembackend.annotations;

import com.jzy.alarmsystembackend.service.log.MyLog;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Loggable {
    Class<? extends MyLog> value();

    String[] args() default {};
}
