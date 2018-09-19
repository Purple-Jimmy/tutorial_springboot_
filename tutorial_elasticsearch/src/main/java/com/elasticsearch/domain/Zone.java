package com.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jimmy. 2018/1/29  14:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zone {
    private String country;
    private String city;
}
