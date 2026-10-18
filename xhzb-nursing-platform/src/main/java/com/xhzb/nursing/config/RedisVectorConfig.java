package com.xhzb.nursing.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.TokenCountBatchingStrategy;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPooled;

@Configuration
public class RedisVectorConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port:6379}")
    private int port;

    @Value("${spring.data.redis.database:0}")
    private int database;

    @Value("${spring.data.redis.password:}")
    private String password;

    @Bean
    public JedisPooled jedisPooled() {
        //复用spring.data.redis的连接配置，避免硬编码地址
        DefaultJedisClientConfig.Builder clientConfig = DefaultJedisClientConfig.builder()
                .database(database);
        if (password != null && !password.isEmpty()) {
            clientConfig.password(password);
        }
        return new JedisPooled(new HostAndPort(host, port), clientConfig.build());
    }

    @Bean
    public VectorStore vectorStore(JedisPooled jedisPooled, OpenAiEmbeddingModel openAiEmbeddingModel) {
        return RedisVectorStore.builder(jedisPooled, openAiEmbeddingModel)
                .indexName("spring-ai-index")                // Optional: defaults to "spring-ai-index"
                .prefix("doc:")                  // Optional: defaults to "embedding:"
                .metadataFields(                         // Optional: define metadata fields for filtering
                        RedisVectorStore.MetadataField.tag("country"),
                        RedisVectorStore.MetadataField.numeric("year"))
                .initializeSchema(false)                  // Optional: false避免启动时依赖RediSearch模块(FT命令)
                .batchingStrategy(new TokenCountBatchingStrategy()) // Optional: defaults to TokenCountBatchingStrategy
                .build();
    }

}