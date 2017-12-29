package com.architecture.bocom.sdk.http;

/**
 * Created by Su on 2016/11/11.
 * 若每个返回的对象中都有状态码code与error_message
 *
 * {"code":0,"desc":"success","content":{} }
 */

public class BaseCallModel<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
