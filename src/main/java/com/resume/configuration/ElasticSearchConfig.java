package com.resume.configuration;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableElasticsearchRepositories("com.resume.repository.search")
@EnableTransactionManagement
public class ElasticSearchConfig {
    @Value("${elasticsearch.home}")
    private String elasticHome;

    @Bean
    public Node node() {
        return new NodeBuilder()
                .local(true)
                .settings(Settings.builder().put("path.home", elasticHome))
                .node();
    }

    @Bean
    public ElasticsearchOperations elasticsearchOperations() {
        return new ElasticsearchTemplate(node().client());
    }
}
