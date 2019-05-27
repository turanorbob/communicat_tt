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
public class AddApiBodyVo {
    @ApiModelProperty("所属api")
    private String apiId;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("内容")
    private String content;
}
