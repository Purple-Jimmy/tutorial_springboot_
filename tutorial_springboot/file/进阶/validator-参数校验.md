### validator 参数校验
springBoot的Web组件内部集成了hibernate-validator,无需再导入包.

#### 内置校验
* @AssertTrue:用于boolean字段,意为该字段必须为true
* @AssertFalse:用于boolean字段,意为该字段必须为false
* @DecimalMax:小于等于该值    
* @DecimalMin:大于等于该值     
* @Max:小于等于该值
* @Min:大于等于该值  
* @Null:能为null   
* @NotNull:不能为null
* @NotBlank:String,不能为空,检查时忽略空格
* @NotEmpty:String,Collection,Map and arrays
* @Future:是否属于未来日期
* @Past:是否属于过去日期
* @Length(min=, max=):字符串长度是否在min和max之间
* @Size(min=, max=):字段size是否在min和max之间,可以是字符串,数组,集合,map等
* @URL(protocol=, host=, port=, regexp=, flags=):是否是一个有效的url
* @Email:是否是有效email地址
* @Range(min=, max=):BigDecimal,BigInteger,String, byte,short, int,long
* @NotEmptyPattern:在字符串不为空的情况下,验证是否匹配正则表达式
* @ListStringPattern:验证集合中的字符串是否满足正则表达式
* @DateValidator:验证日期格式是否满足正则表达式,Local为ENGLISH
* @DateFormatCheckPattern:验证日期格式是否满足正则表达式,Local为自己手动指定
* @Pattern(regex=, flag=):String
* @Digits(integer=, fraction=):被注释的元素必须是一个数字,其值必须在可接受的范围内,interger指定整数精度,fraction指定小数精度  
* @Valid:用于一个字段包含其他对象的集合、map、数组的字段,或该字段直接为一个对象的引用,则检查当前对象时也会检查该字段所引用的对象


#### 自定义校验
1. 定义注解:validatedBy = CustomValidatorClass.class为校验实现类
```
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
```

2. 定义校验逻辑实现类
```
public class CustomValidatorClass implements ConstraintValidator<CustomValidator,Object>{
    //临时变量,保存字段值列表
    private String values;
    @Override
    public void initialize(CustomValidator constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }

    //实现验证逻辑
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
```

3. 注解使用
```
@CustomValidator(values = "1,2,3")
private String password;
```

4. 测试校验:@Valid 注解
```
@RequestMapping("/account")
public String saveAccount(@Valid Account account, BindingResult result){
    if(result.hasErrors()){
        StringBuilder str = new StringBuilder();
        //获取错误字段集合
        List<FieldError> fieldErrors = result.getFieldErrors();
        //获取本地Local,zh_CN
        Locale local = LocaleContextHolder.getLocale();
        //遍历错误字段获取错误信息
        for(FieldError error:fieldErrors){
             //获取错误信息
            String errorMsg = messageSource.getMessage(error,local);
            str.append(error.getField()+":"+errorMsg+",");

        }
        return str.toString();
    }
        return Constants.SUCCESS;
}
```