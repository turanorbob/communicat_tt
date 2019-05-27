package com.example.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/27
 */
@Data
@ApiModel
public class AddApiHeadersVo {
    @ApiModelProperty("所属API")
    private String apiId;
    @ApiModelProperty("key")
    private String key;
    @ApiModelProperty("value")
    private String value;
    @ApiModelProperty("description")
    private String description;
}
