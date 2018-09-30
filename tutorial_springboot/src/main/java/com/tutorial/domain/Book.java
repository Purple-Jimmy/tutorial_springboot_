package com.tutorial.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Jimmy. 2018/1/24  14:59
 */
@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 3498205413471236405L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
