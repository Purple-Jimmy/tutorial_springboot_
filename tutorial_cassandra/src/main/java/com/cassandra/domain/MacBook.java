package com.cassandra.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author jimmy
 * @date 2018-12-1822:10
 */
@Data
@Table
@Entity(name="mac_book")
public class MacBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner")
    private String owner;
}
