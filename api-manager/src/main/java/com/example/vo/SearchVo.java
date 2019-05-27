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
public class SearchVo {
    @ApiModelProperty(value = "page, default 0", example = "0")
    protected int page = 0;
    @ApiModelProperty(value = "pagesize, default 15", example = "15")
    protected int pagesize = 15;
}
