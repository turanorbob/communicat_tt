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
@Table(name = "api_env", schema = "api")
public class ApiEnv {
    @Id
    private String id;
    @Column(name = "category_id")
    private String categoryId;
    private String variable;
    @Column(name = "initial_value")
    private String initialValue;
    @Column(name = "current_value")
    private String currentValue;
}
