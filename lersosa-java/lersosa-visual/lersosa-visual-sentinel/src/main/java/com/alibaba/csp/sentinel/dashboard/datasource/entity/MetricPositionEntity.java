/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package com.alibaba.csp.sentinel.dashboard.datasource.entity;

import lombok.Data;

import java.util.Date;

/**
 * 度量位置实体
 *
 * @author leyou
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/9/3
 */
@Data
public class MetricPositionEntity {

    /**
     * 主键
     */
    private long id;

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
     * IP 地址
     */
    private String ip;

    /**
     * Sentinel在该应用上使用的端口
     */
    private int port;

    /**
     * 机器名，冗余字段
     */
    private String hostname;

    /**
     * 上一次拉取的最晚时间戳
     */
    private Date lastFetch;

    /**
     * 转换为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return "MetricPositionEntity{" +
               "id=" + id +
               ", gmtCreate=" + gmtCreate +
               ", gmtModified=" + gmtModified +
               ", app='" + app + '\'' +
               ", ip='" + ip + '\'' +
               ", port=" + port +
               ", hostname='" + hostname + '\'' +
               ", lastFetch=" + lastFetch +
               '}';
    }
}
