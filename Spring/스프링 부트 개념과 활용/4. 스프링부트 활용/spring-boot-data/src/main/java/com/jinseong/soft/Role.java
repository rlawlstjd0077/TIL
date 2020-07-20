package com.jinseong.soft;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;

@NodeEntity
public class Role {
    @Id @GeneratedValue
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
