package com.example.plantapp.db.redis.repository;

public interface IBaseRedisRepository<ID, V> {
    V getData(ID id);
    V loadData(ID id);
    V getOrLoadData(ID id);
    V getDataFromDb(ID id);
    boolean pushData(ID id, V data);
    boolean deleteData(ID id);

}
