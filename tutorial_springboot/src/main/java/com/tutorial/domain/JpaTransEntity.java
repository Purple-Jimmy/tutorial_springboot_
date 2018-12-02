package com.tutorial.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jimmy
 * @date 2018/12/219:38
 * jpa 事务
 */
@Data
@Entity
@Table(name = "jpa_trans_entity")
public class JpaTransEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
