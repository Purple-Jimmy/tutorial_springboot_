package com.tutorial.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义注解实现类
 */
public class CustomValidatorClass implements ConstraintValidator<CustomValidator,Object>{
    /**
     * 临时变量,保存字段值列表
     */
    private String values;

    @Override
    public void initialize(CustomValidator constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }

    /**
     * 实现验证逻辑
     * @param value
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String[] strArray = values.split(",");
        Boolean flag = false;
        //遍历比对有效值
        for(int i=0;i<strArray.length;i++){
            if(strArray[i].equals(value)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
