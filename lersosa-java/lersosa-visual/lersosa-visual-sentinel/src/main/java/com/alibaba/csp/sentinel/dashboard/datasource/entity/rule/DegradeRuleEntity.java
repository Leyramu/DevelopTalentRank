/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package com.alibaba.csp.sentinel.dashboard.datasource.entity.rule;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import lombok.Data;

import java.util.Date;

/**
 * 降级规则实体
 *
 * @author leyou
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 0.2.1
 * @since 2024/9/3
 */
@Data
public class DegradeRuleEntity implements RuleEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 应用名称
     */
    private String app;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 资源名称
     */
    private String resource;

    /**
     * 限流应用名称
     */
    private String limitApp;

    /**
     * 阈值
     */
    private Double count;

    /**
     * 时间窗口
     */
    private Integer timeWindow;

    /**
     * 降级策略
     */
    private Integer grade;

    /**
     * 最小请求数
     */
    private Integer minRequestAmount;

    /**
     * 慢调用比例阈值
     */
    private Double slowRatioThreshold;

    /**
     * 统计时间窗口
     */
    private Integer statIntervalMs;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 将 DegradeRule 转换为 DegradeRuleEntity
     *
     * @param app  应用名称
     * @param ip   IP 地址
     * @param port 端口
     * @param rule DegradeRule
     * @return DegradeRuleEntity
     */
    public static DegradeRuleEntity fromDegradeRule(String app, String ip, Integer port, DegradeRule rule) {
        DegradeRuleEntity entity = new DegradeRuleEntity();
        entity.setApp(app);
        entity.setIp(ip);
        entity.setPort(port);
        entity.setResource(rule.getResource());
        entity.setLimitApp(rule.getLimitApp());
        entity.setCount(rule.getCount());
        entity.setTimeWindow(rule.getTimeWindow());
        entity.setGrade(rule.getGrade());
        entity.setMinRequestAmount(rule.getMinRequestAmount());
        entity.setSlowRatioThreshold(rule.getSlowRatioThreshold());
        entity.setStatIntervalMs(rule.getStatIntervalMs());
        return entity;
    }

    /**
     * 获取 IP 地址
     *
     * @return IP 地址
     */
    @Override
    public String getIp() {
        return ip;
    }

    /**
     * 获取端口
     *
     * @return 端口
     */
    @Override
    public Integer getPort() {
        return port;
    }

    /**
     * 获取主键
     *
     * @return 主键
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取应用名称
     *
     * @return 应用名称
     */
    @Override
    public String getApp() {
        return app;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    @Override
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 转换为 DegradeRule
     *
     * @return DegradeRule
     */
    @Override
    public DegradeRule toRule() {
        DegradeRule rule = new DegradeRule();
        rule.setResource(resource);
        rule.setLimitApp(limitApp);
        rule.setCount(count);
        rule.setTimeWindow(timeWindow);
        rule.setGrade(grade);
        if (minRequestAmount != null) {
            rule.setMinRequestAmount(minRequestAmount);
        }
        if (slowRatioThreshold != null) {
            rule.setSlowRatioThreshold(slowRatioThreshold);
        }
        if (statIntervalMs != null) {
            rule.setStatIntervalMs(statIntervalMs);
        }

        return rule;
    }
}
