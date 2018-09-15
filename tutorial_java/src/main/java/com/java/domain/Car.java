package com.java.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: jimmy
 * @Date: 2018/8/7
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
public class Car implements Serializable{
    private static final long serialVersionUID = 4403335633735558138L;

    private Long id;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 价格
     */
    private Long price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(price, car.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, price);
    }
}
