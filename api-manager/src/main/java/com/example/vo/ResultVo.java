package com.example.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/27
 */
@Data
public class ResultVo<T> {
    private int code;
    private String message;
    private T object;

    public ResultVo<T> success(){
        this.code = HttpStatus.OK.value();
        this.message = "success";
        return this;
    }
}
