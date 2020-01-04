package com.leyou.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zmq
 * Date: 2020/1/3
 */
@ConfigurationProperties(prefix = "leyou.filter")
public class FilterProperties {
    private List<String> allowPaths;

    public List<String> getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }
}
