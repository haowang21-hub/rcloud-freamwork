package com.rcloud.springboot.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "richway.dynamic")
public class DynamicDataSourceRepository {
    private Map<String, DruidDbProperties> datasource = new HashMap<>();

    public Map<String, DruidDbProperties> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, DruidDbProperties> datasource) {
        this.datasource = datasource;
    }

    @PostConstruct
    public void init() {
        datasource.forEach((id, config) -> {
            if (config.getId() == null) {
                config.setId(id);
            }
            else if (!config.getId().equals(id)) {
                datasource.put(config.getId(), config);
            }
        });
    }
}
