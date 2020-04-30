package com.yxj.theradpool.entity;

public class JobDetail<T, K> {
    private String detailName;
    private T param;
    private K result;

    public JobDetail() {
    }

    public JobDetail(String detailName, T param, K result) {
        this.detailName = detailName;
        this.param = param;
        this.result = result;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public K getResult() {
        return result;
    }

    public void setResult(K result) {
        this.result = result;
    }
}
