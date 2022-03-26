package com.javaservertest.javaservertest.DataBase;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Entity
@Table(name = "nodes")
// @EntityListeners(AuditingEntityListener.class)
public class Nodes{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    

    @Column(name = "parentId", nullable = true)
    private Integer parentId;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "ip", nullable = true)
    private String ip;
    @Column(name = "port", nullable = true)
    private Integer port;

    public Nodes(){}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


}
