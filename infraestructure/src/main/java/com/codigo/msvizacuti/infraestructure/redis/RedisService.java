package com.codigo.msvizacuti.infraestructure.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor

public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public void saveInRedis(String key, String value, int exp){
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, exp, TimeUnit.MINUTES);
    }
    public String getFromRedis(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public void deleteKey(String key){
        redisTemplate.delete(key);
    }
}
