package com.thebook.thebookapi.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPool;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
//@EnableRedisRepositories(basePackageClasses = { ScriptureRepository.class })
public class JedisConfig implements DisposableBean {

    @Value("${redis.kjv.hostname}")
    public String kjvHostname;

    @Value("${redis.kjv.port}")
    public int kjvPort;

    @Value("${redis.kjv.password}")
    public String kjvPassword;

    public static JedisPool jedisPool;

    @Bean
    RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(kjvHostname, kjvPort);
        config.setPassword(kjvPassword);
        return config;
    }

    @Bean
    public RedisConnectionFactory connectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Override
    public void destroy() throws Exception {
        if (jedisPool != null) {
            jedisPool.destroy();
        }
    }

}
