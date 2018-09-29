package com.tutorial.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = CustomValidatorClass.class)
public @interface CustomValidator {
    //验证字段的值,多个值用,隔开
    String values();
    //提示内容
    String message() default "参数验证不通过";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
