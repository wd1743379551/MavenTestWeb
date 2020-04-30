package com.yxj.theradpool.entity;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class JobInfo<T,K> {

    private String jobId;
    private List<JobDetail<T,K>> jobDetailList;
    private AtomicInteger totalJobSize;
    private AtomicInteger completeJobSize;

    public JobInfo() {
    }

    public JobInfo(String jobId, List<JobDetail<T, K>> jobDetailList, AtomicInteger totalJobSize, AtomicInteger completeJobSize) {
        this.jobId = jobId;
        this.jobDetailList = jobDetailList;
        this.totalJobSize = totalJobSize;
        this.completeJobSize = completeJobSize;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public List<JobDetail<T, K>> getJobDetailList() {
        return jobDetailList;
    }

    public void setJobDetailList(List<JobDetail<T, K>> jobDetailList) {
        this.jobDetailList = jobDetailList;
    }

    public AtomicInteger getTotalJobSize() {
        return totalJobSize;
    }

    public void setTotalJobSize(AtomicInteger totalJobSize) {
        this.totalJobSize = totalJobSize;
    }

    public AtomicInteger getCompleteJobSize() {
        return completeJobSize;
    }

    public void setCompleteJobSize(AtomicInteger completeJobSize) {
        this.completeJobSize = completeJobSize;
    }
}
