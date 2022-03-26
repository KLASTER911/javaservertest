package com.javaservertest.javaservertest.DataBase;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NodesRepository extends CrudRepository<Nodes, Integer> {

    @Query("SELECT t FROM Nodes t WHERE t.parentId = ?1") //можно и без аннотации Query
    Optional<Nodes> findByParentId(Object parentId);

    @Query("SELECT t FROM Nodes t WHERE t.name = ?1") //можно и без аннотации Query
    Optional<Nodes> findByName(String name);

    @Query("SELECT t FROM Nodes t WHERE t.ip = ?1") //можно и без аннотации Query
    Optional<Nodes> findByIp(String ip);

    @Query("SELECT t FROM Nodes t WHERE t.port = ?1") //можно и без аннотации Query
    Optional<Nodes> findByPort(Integer port);

    void deleteByParentId(Integer parentId);
}
