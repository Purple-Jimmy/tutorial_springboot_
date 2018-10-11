package com.tutorial.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jimmy. 2018/1/24  14:59
 * @GeneratedValue(generator = "paymentableGenerator")
 * @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
 * 由程序设置主键值
 */
@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 3498205413471236405L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /* @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")*/
    private Long id;

   // @Formula(value = "(SELECT t.programset_name from search_program_set t where t.id = PROGRAM_SERIES_ID)")
    private String name;
}
