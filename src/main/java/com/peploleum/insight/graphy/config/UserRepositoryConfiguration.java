/*
 * Copyright 2014-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peploleum.insight.graphy.config;

import com.microsoft.spring.data.gremlin.common.GremlinConfig;
import com.microsoft.spring.data.gremlin.config.AbstractGremlinConfiguration;
import com.microsoft.spring.data.gremlin.repository.config.EnableGremlinRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableGremlinRepositories(basePackages = "com.peploleum.insight.graphy.repository")
@EnableConfigurationProperties(GremlinProperties.class)
public class UserRepositoryConfiguration extends AbstractGremlinConfiguration {

    @Autowired
    private GremlinProperties gremlinProps;
    @Value("${endpoint}")
    private String enpoint;
    @Value("${port}")
    private String port;

    @Override
    public GremlinConfig getGremlinConfig() {
        final GremlinConfig gremlinConfiguration = new GremlinConfig(enpoint, Integer.valueOf(port).intValue(), gremlinProps.getUsername(), gremlinProps.getPassword(), false, false, gremlinProps.getSerializer());
        return gremlinConfiguration;
    }
}
