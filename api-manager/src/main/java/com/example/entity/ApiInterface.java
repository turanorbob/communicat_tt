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
@Table(name = "api", schema = "api")
public class ApiInterface {
    @Id
    private String id;
    @Column(name = "collection_id")
    private String collectionId;
    private String name;
    private String method;
    private String url;
    @Column(name = "has_params")
    private boolean hasParams;
    @Column(name = "has_authorization")
    private boolean hasAuthorization;
    @Column(name = "has_headers")
    private boolean hasHeaders;
    @Column(name = "has_body")
    private boolean hasBody;
    @Column(name = "has_pre_request_script")
    private boolean hasPreRequestScript;
}
