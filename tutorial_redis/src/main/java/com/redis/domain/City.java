package com.redis.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jimmy
 * @date 2019-01-2221:10
 */
@Data
@Table
@Entity(name="city")
public class City implements Serializable {
    private static final long serialVersionUID = 5457507957150215600L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
