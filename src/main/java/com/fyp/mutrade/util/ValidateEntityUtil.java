package com.fyp.mutrade.util;

import java.lang.reflect.Field;

import com.fyp.mutrade.annotion.ValidateEntity;
import com.fyp.mutrade.bean.CodeMsg;

/**
 * Entity validation utility class
 * @author Administrator
 *
 */
public class ValidateEntityUtil {
	
	public static CodeMsg validate(Object object){
		Field[] declaredFields = object.getClass().getDeclaredFields();
		for(Field field : declaredFields){
			ValidateEntity annotation = field.getAnnotation(ValidateEntity.class);
			if(annotation != null){
				if(annotation.required()){
					//Indicates that this field is a required field
					field.setAccessible(true);
					try {
						Object o = field.get(object);
						//First, check if it is empty
						if(o == null){
							CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
							codeMsg.setMsg(annotation.errorRequiredMsg());
							return codeMsg;
						}
						//At this point, it indicates that the variable's value is not null
						//Now, check if it is a String type
						if(o instanceof String){
							//If it is of string type, then check its length
							if(annotation.requiredLeng()){
								if(o.toString().length() < annotation.minLength()){
									CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
									codeMsg.setMsg(annotation.errorMinLengthMsg());
									return codeMsg;
								}
								if(o.toString().length() > annotation.maxLength()){
									CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
									codeMsg.setMsg(annotation.errorMaxLengthMsg());
									return codeMsg;
								}
							}
						}
						//Next, check if it is a number
						if(isNumberObject(o)){
							//Determine whether to check the minimum value as specified
							if(annotation.requiredMinValue()){
								if(Double.valueOf(o.toString()) < annotation.minValue()){
									CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
									codeMsg.setMsg(annotation.errorMinValueMsg());
									return codeMsg;
								}
							}
							//Determine whether to check the maximum value as specified
							if(annotation.requiredMaxValue()){
								if(Double.valueOf(o.toString()) > annotation.maxValue()){
									CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
									codeMsg.setMsg(annotation.errorMaxValueMsg());
									return codeMsg;
								}
							}
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return CodeMsg.SUCCESS;
	}
	
	/**
	 * Check if the object is of numeric type
	 * @param object
	 * @return
	 */
	public static boolean isNumberObject(Object object){
		if(object instanceof Integer)return true;
		if(object instanceof Long)return true;
		if(object instanceof Float)return true;
		if(object instanceof Double)return true;
		return false;
	}
}
