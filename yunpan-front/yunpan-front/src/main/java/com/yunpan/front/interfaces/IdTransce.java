package com.yunpan.front.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @ClassName: IdTransce
 * @Description: 标识型注解，用来标识setid的方法
 * @author duanjigui
 * @date 2017年4月9日 下午12:08:09
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface IdTransce {
	Type value() default Type.INTEGER ;
}
