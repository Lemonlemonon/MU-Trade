package com.fyp.mumarket.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Entity validation custom annotation class. 
 * It checks whether the values of each field of the entity are within the limits specified by our custom annotations.
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateEntity {
	
	public boolean required() default false;//if validate not null
	
	public boolean requiredLeng() default false;//if required length
	
	public boolean requiredMaxValue() default false;//if required a max value
	
	public boolean requiredMinValue() default false;//if required a minimum value
	
	public int maxLength() default -1;//Max length
	
	public int minLength() default -1;//Min Length
	
	public long maxValue() default -1;//Max value
	
	public long minValue() default -1;//Min value
	
	public String errorRequiredMsg() default "";//error msg for not null requirement
	
	public String errorMinLengthMsg() default "";//error msg for not satisfy min length
	
	public String errorMaxLengthMsg() default "";//error msg for not satisfy max length
	
	public String errorMinValueMsg() default "";//error msg for not satisfy min value
	
	public String errorMaxValueMsg() default "";//error msg for not satisfy max length
}
