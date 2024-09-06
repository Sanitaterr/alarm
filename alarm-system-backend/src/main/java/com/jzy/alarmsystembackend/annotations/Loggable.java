package com.jzy.alarmsystembackend.annotations;

import com.jzy.alarmsystembackend.service.log.ILogImpl;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Loggable {
    Class<? extends ILogImpl> value();

    String[] args() default {};
}
