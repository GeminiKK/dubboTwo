package com.debug.wust.dubbo.two.server.data;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DubboRecordResponse implements Serializable {

    private Integer code;

    private  String msg;

    private  Integer data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
