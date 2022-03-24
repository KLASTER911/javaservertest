package com.javaservertest.javaservertest.DataBase;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Entity
@Table(name = "nodes")
// @EntityListeners(AuditingEntityListener.class)
public class Nodes{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    

    @Column(name = "parentId", nullable = true)
    private int parentId;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "ip", nullable = true)
    private String ip;
    @Column(name = "port", nullable = true)
    private int port;

    public Nodes(){}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int parentId) {
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

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }


}
