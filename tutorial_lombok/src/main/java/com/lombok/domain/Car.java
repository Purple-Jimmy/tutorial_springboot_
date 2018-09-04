package com.lombok.domain;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Author: jimmy
 * @Date: 2018/9/4
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
