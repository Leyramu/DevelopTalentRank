/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.csp.sentinel.dashboard.datasource.entity;

import lombok.Data;

import java.util.Date;

/**
 * 度量实体
 *
 * @author leyou
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/9/3
 */
@Data
public class MetricEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 应用名称
     */
    private String app;

    /**
     * 监控信息的时间戳
     */
    private Date timestamp;

    /**
     * 资源名称
     */
    private String resource;

    /**
     * 通过 QPS
     */
    private Long passQps;

    /**
     * 成功 QPS
     */
    private Long successQps;

    /**
     * 被限流 QPS
     */
    private Long blockQps;

    /**
     * 异常 QPS
     */
    private Long exceptionQps;

    /**
     * 所有成功退出 QPS 的 RT 总结
     */
    private double rt;

    /**
     * 本次聚合的总条数
     */
    private int count;

    /**
     * 资源名称的哈希值，用于快速比较，避免字符串比较
     */
    private int resourceCode;

    /**
     * 复制一个 MetricEntity
     *
     * @param oldEntity 原 MetricEntity
     * @return 新 MetricEntity
     */
    public static MetricEntity copyOf(MetricEntity oldEntity) {
        MetricEntity entity = new MetricEntity();
        entity.setId(oldEntity.getId());
        entity.setGmtCreate(oldEntity.getGmtCreate());
        entity.setGmtModified(oldEntity.getGmtModified());
        entity.setApp(oldEntity.getApp());
        entity.setTimestamp(oldEntity.getTimestamp());
        entity.setResource(oldEntity.getResource());
        entity.setPassQps(oldEntity.getPassQps());
        entity.setBlockQps(oldEntity.getBlockQps());
        entity.setSuccessQps(oldEntity.getSuccessQps());
        entity.setExceptionQps(oldEntity.getExceptionQps());
        entity.setRt(oldEntity.getRt());
        entity.setCount(oldEntity.getCount());
        return entity;
    }

    /**
     * 增加通过 QPS
     *
     * @param passQps 增加的 QPS
     */
    public synchronized void addPassQps(Long passQps) {
        this.passQps += passQps;
    }

    /**
     * 增加被限流 QPS
     *
     * @param blockQps 增加的 QPS
     */
    public synchronized void addBlockQps(Long blockQps) {
        this.blockQps += blockQps;
    }

    /**
     * 增加异常 QPS
     *
     * @param exceptionQps 增加的 QPS
     */
    public synchronized void addExceptionQps(Long exceptionQps) {
        this.exceptionQps += exceptionQps;
    }

    /**
     * 增加本次聚合的总条数
     *
     * @param count 增加的条数
     */
    public synchronized void addCount(int count) {
        this.count += count;
    }

    /**
     * 增加 RT 和成功 QPS
     *
     * @param avgRt      average rt of {@code successQps}
     * @param successQps 成功 QPS
     */
    public synchronized void addRtAndSuccessQps(double avgRt, Long successQps) {
        this.rt += avgRt * successQps;
        this.successQps += successQps;
    }

    /**
     * {@link #rt} = {@code avgRt * successQps}
     *
     * @param avgRt      average rt of {@code successQps}
     * @param successQps 成功 QPS
     */
    public synchronized void setRtAndSuccessQps(double avgRt, Long successQps) {
        this.rt = avgRt * successQps;
        this.successQps = successQps;
    }

    /**
     * 设置资源名称
     *
     * @param resource 资源名称
     */
    public void setResource(String resource) {
        this.resource = resource;
        this.resourceCode = resource.hashCode();
    }

    /**
     * 获取资源名称的哈希值
     *
     * @return 资源名称的哈希值
     */
    @Override
    public String toString() {
        return "MetricEntity{" +
               "id=" + id +
               ", gmtCreate=" + gmtCreate +
               ", gmtModified=" + gmtModified +
               ", app='" + app + '\'' +
               ", timestamp=" + timestamp +
               ", resource='" + resource + '\'' +
               ", passQps=" + passQps +
               ", blockQps=" + blockQps +
               ", successQps=" + successQps +
               ", exceptionQps=" + exceptionQps +
               ", rt=" + rt +
               ", count=" + count +
               ", resourceCode=" + resourceCode +
               '}';
    }
}
