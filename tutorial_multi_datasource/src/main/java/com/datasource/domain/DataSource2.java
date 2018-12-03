package com.datasource.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jimmy
 * @date 2018/12/321:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table
@Entity(name="data_source_2")
public class DataSource2 implements Serializable {
    private static final long serialVersionUID = 1320099570942181300L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
}
