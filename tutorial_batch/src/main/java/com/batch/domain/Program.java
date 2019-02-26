package com.batch.domain;

import io.searchbox.annotations.JestId;
import lombok.Data;

/**
 * @author jimmy
 * @date 2019-02-2621:28
 */
@Data
public class Program {
    @JestId
    private String id;

    private String name;
}
