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
public class AddApiVo {
    @ApiModelProperty(value = "所属集合，默认-1", example = "-1")
    protected String collectionId="-1";
    @ApiModelProperty("名称")
    protected String name;
    @ApiModelProperty("方法")
    protected String method;
    @ApiModelProperty("URL")
    protected String url;
}
