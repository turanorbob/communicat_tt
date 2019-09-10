package com.example.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * description
 * </p>
 *
 * @author Legendl
 * @date 2019-09-03 18:30
 */
@Data
@Entity
@Table(name = "test", schema = "api")
public class Test {
    @Id
    private Long id;
    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "value")
    private String value;
}
