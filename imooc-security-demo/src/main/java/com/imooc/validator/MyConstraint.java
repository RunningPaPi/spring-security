
package com.imooc.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author mjf
 *
 */
//@Target() 声明注解可以标注在什么元素上面
@Target({ElementType.METHOD,ElementType.FIELD})
//@Retention定义被它所注解的注解保留多久，生命周期
@Retention(RetentionPolicy.RUNTIME)
//当前的注解要用一个什么样的类去校验
@Constraint(validatedBy=MyConstraintValidator.class)
public @interface MyConstraint {
	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
