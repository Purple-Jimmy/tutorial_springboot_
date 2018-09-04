package com.lombok.domain;

import lombok.*;

/**
 * @Author: jimmy
 * @Date: 2018/9/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true,exclude = "price")
@EqualsAndHashCode(callSuper = true)
public class Lexus extends Car{

    private Float price;

}
