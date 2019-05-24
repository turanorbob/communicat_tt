package com.example.entity;

import lombok.Data;

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
@Table(name = "api_env_category", schema = "api")
public class ApiEnvCategory {
    @Id
    private String id;
    private String name;
    private String description;
}
