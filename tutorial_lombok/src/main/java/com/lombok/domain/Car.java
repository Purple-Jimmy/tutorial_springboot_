package com.lombok.domain;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Author: jimmy
 * @Date: 2018/9/4
 * @EqualsAndHashCode(callSuper = false)  默认仅使用该类中定义的属性且不调用父类的方法
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class Car {

    private Long id;
    private String brand;

}
