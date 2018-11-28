package com.tutorial.configurer;

import com.tutorial.util.CustomException;
import com.utils.common.ExceptionMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @Author: jimmy
 * @Date: 2018/9/29
 * 全局统一异常
 * 当请求处理出现异常时,会根据异常处理器的配置顺序依次尝试异常匹配和处理
 * 使用@ControllerAdvice处理异常也有一定的局限性,只有进入Controller层的错误,才会由@ControllerAdvice处理
 * 拦截器抛出的错误以及访问错误地址的情况@ControllerAdvice处理不了,由Spring Boot默认的异常处理机制处理
 *
 * 1.捕获返回json格式
 * 2.捕获返回页面
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionConfigurer {

    /**
     * 添加全局异常处理流程,根据需要设置需要处理的异常
     * 此处以MethodArgumentNotValidException为例
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidHandler(HttpServletRequest request,
                                                MethodArgumentNotValidException exception) throws Exception {
        //exception.printStackTrace();
        //按需重新封装需要返回的错误信息
        //  List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        ExceptionMsg resultMsg = new ExceptionMsg();
        resultMsg.setRequestUrl(request.getRequestURI());
        //  List<ObjectError> errors = e.getBindingResult().getAllErrors();
        //解析原错误信息,封装后返回.此处返回非法的字段名称,原始值,错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
          /*  ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);*/
            System.out.println("field:"+error.getField());
            System.out.println("value:"+error.getRejectedValue());
            System.out.println("msg:"+error.getDefaultMessage());
            resultMsg.setErrorCode("301");
            resultMsg.setErrorMsg(error.getDefaultMessage());
            // resultMsg.setErrorMsg(error.getDefaultMessage());
        }
        return resultMsg;
    }



    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object validExceptionHandler(ConstraintViolationException exception) {
        ExceptionMsg resultMsg = new ExceptionMsg();
        resultMsg.setErrorMsg(exception.getLocalizedMessage());
        return resultMsg;
    }



    @ExceptionHandler(value = CustomException.class)
    public Object customExceptionHandler(HttpServletRequest request, CustomException e){
        ExceptionMsg resultMsg = new ExceptionMsg();
       /* resultMsg.setRequestUrl(request.getRequestURL().toString());
        resultMsg.setErrorCode("301");
        resultMsg.setErrorMsg("自定义异常:参数不合法");*/
        resultMsg.setRequestUrl(request.getRequestURL().toString())
                 .setErrorCode(String.valueOf(e.getErrorCode()))
                 .setErrorMsg(e.getMessage());
        return resultMsg;
    }


    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception{
        ExceptionMsg resultMsg = new ExceptionMsg();
        resultMsg.setErrorMsg(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            resultMsg.setErrorCode(HttpStatus.NOT_FOUND.toString());
        } else {
            resultMsg.setRequestUrl(request.getRequestURL().toString())
                    .setErrorCode("500")
                    .setErrorMsg(e.getMessage());
        }
        return resultMsg;
    }

}
