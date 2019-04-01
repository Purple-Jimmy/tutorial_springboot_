package com.redis.configurer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: jimmy
 * @Date: 2019/4/1
 */
@Component
@Data
public class SentinelProperties {

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;

    private Set<String> hosts;

    @PostConstruct
    public void hosts() {
        hosts = new HashSet<>();
        hosts.addAll(Arrays.asList(nodes.split(",")));
    }

}
