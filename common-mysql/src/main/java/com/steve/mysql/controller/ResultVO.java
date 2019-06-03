package com.steve.mysql.controller;

import com.steve.enums.ResultStatus;
import lombok.Data;

@Data
public class ResultVO<T> {

    private Integer code;
    private String message;
    private T data;

    public ResultVO() {
        this(ResultStatus.OK);
    }

    public ResultVO(T data) {
        this();
        this.data = data;
    }

    public ResultVO(ResultStatus ResultStatus) {
        this.code = ResultStatus.getCode();
        this.message = ResultStatus.getMessage();
    }

    public ResultVO(String message, ResultStatus ResultStatus) {
        this.code = ResultStatus.getCode();
        this.message = message;
    }

    public ResultVO(ResultStatus ResultStatus, T data) {
        this(ResultStatus);
        this.data = data;
    }

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
