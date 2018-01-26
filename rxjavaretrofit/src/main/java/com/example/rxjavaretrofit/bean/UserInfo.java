package com.example.rxjavaretrofit.bean;

import java.util.List;

/**
 * Created by zhaoke on 2018/1/26.
 */

public class UserInfo {
    private List<RWBeanHS> rwBeanHS;
    private LoGinHuangS loGinHuangS;

    public UserInfo(List<RWBeanHS> rwBeanHS, LoGinHuangS loGinHuangS) {
        this.rwBeanHS = rwBeanHS;
        this.loGinHuangS = loGinHuangS;
    }

    public List<RWBeanHS> getRwBeanHS() {
        return rwBeanHS;
    }

    public void setRwBeanHS(List<RWBeanHS> rwBeanHS) {
        this.rwBeanHS = rwBeanHS;
    }

    public LoGinHuangS getLoGinHuangS() {
        return loGinHuangS;
    }

    public void setLoGinHuangS(LoGinHuangS loGinHuangS) {
        this.loGinHuangS = loGinHuangS;
    }
}
