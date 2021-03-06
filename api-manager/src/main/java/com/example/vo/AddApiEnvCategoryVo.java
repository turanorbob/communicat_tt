package com.example.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/27
 */
@Data
@ApiModel
public class AddApiEnvCategoryVo {
    @ApiModelProperty("所属目录")
    private String categoryId;
    @ApiModelProperty("name")
    private String name;
    @ApiModelProperty("description")
    private String description;
}
