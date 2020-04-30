package com.yxj.theradpool.handler;

public interface JobHandler<T,K> {

    K handlerJob(T t);
}
