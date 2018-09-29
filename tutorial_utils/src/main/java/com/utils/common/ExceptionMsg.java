package com.utils.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: jimmy
 * @Date: 2018/9/29
 * 异常信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ExceptionMsg {

    private String errorCode;
    private String errorMsg;
    private String requestUrl;
    private Object object;
}
