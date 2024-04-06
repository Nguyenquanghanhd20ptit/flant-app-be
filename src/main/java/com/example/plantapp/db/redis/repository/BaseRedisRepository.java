package com.example.plantapp.db.redis.repository;

import com.example.plantapp.db.redis.annotation.CacheConfig;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;

@Configuration
public abstract class BaseRedisRepository<ID, V> implements IBaseRedisRepository<ID, V> {
    protected Logger logger = LogManager.getLogger("ite-ecommerce");
    protected Gson gson = new Gson();
    @Autowired
    protected RedisTemplate redisTemplate;
    protected final CacheConfig cacheConfig;
    protected Class<V> vClass;

    public BaseRedisRepository() {
        this.cacheConfig = this.getClass().getAnnotation(CacheConfig.class);
        this.vClass = (Class<V>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected String getRedisKey(ID id) {
        return String.format(cacheConfig.pattern(), id);
    }

    public V getData(ID id) {
        try {
            String redisKey = getRedisKey(id);
            String result = (String) redisTemplate.opsForValue().get(redisKey);
            return gson.fromJson(result, vClass);
        } catch (Exception e) {
            logger.error("Error when get key={} from redis", getRedisKey(id), e);
            return null;
        }
    }

    public V loadData(ID id) {
        try {
            V data = getDataFromDb(id);
            if (data != null) {
                String redisKey = getRedisKey(id);
                int timeExpire = cacheConfig.expireSecond();
                String dataJson = gson.toJson(data);
                redisTemplate.opsForValue().set(redisKey, dataJson, timeExpire, TimeUnit.SECONDS);
            } else {
                logger.error("no data when get from db");
            }
            return data;
        } catch (Exception e) {
            logger.error("Error when load data to redis", e);
            return null;
        }
    }

    public V getOrLoadData(ID id) {
        try {
            V data = getData(id) != null ? getData(id) : loadData(id);
            if (data == null) {
                logger.info("no data when get or load data");
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean pushData(ID id, V data) {
        try {
            String redisKey = getRedisKey(id);
            int timeExpire = cacheConfig.expireSecond();
            String dataJson = gson.toJson(data);
            redisTemplate.opsForValue().set(redisKey, dataJson, timeExpire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            logger.error("Error when push data to redis");
            return false;
        }
    }

    public boolean deleteData(ID id) {
        try {
            String redisKey = getRedisKey(id);
            return Boolean.TRUE.equals(redisTemplate.delete(redisKey));
        } catch (Exception e) {
            logger.error("Error when delete redisKey={}", getRedisKey(id), e);
            return false;
        }
    }

}
