package com.yxj.theradpool;

import com.yxj.theradpool.entity.JobDetail;
import com.yxj.theradpool.entity.JobInfo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MyJobExecutePool {

    private ConcurrentHashMap<String, JobInfo> totalTaskMap = new ConcurrentHashMap<>();

    private List<JobInfo> runningJobInfo = null;


}
