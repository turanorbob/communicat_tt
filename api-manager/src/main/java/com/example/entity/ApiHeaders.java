package com.example.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/24
 */
@Data
@Entity
@Table(name = "api_headers", schema = "api")
public class ApiHeaders {
    @Id
    private String id;
    @Column(name="api_id")
    private String apiId;
    private String key;
    private String value;
    private String description;
}
