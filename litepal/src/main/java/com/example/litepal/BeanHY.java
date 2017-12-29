package com.example.litepal;

import org.litepal.crud.DataSupport;

/**
 * Created by zhaoke on 2017/12/29.
 */

public class BeanHY extends DataSupport {
    private String name;
    private String shijian;
    private String zhut;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }

    public String getZhut() {
        return zhut;
    }

    public void setZhut(String zhut) {
        this.zhut = zhut;
    }
}
