package com.example.vo;

import io.swagger.annotations.Api;
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
public class AddApiEnvVo {
    @ApiModelProperty("所属目录")
    private String categoryId;
    @ApiModelProperty("变量")
    private String variable;
    @ApiModelProperty("初始值")
    private String initialValue;
    @ApiModelProperty("当前值")
    private String currentValue;
}
