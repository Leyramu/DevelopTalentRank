/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import lombok.Data;

import java.util.Objects;

/**
 * {@link GatewayParamFlowItem} 的实体
 *
 * @author cdfive
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.7.0
 * @since 2024/9/3
 */
@Data
public class GatewayParamFlowItemEntity {

    /**
     * 解析策略
     */
    private Integer parseStrategy;

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 匹配模式
     */
    private String pattern;

    /**
     * 匹配策略
     */
    private Integer matchStrategy;

    /**
     * 是否相等
     *
     * @param o 比较对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GatewayParamFlowItemEntity that = (GatewayParamFlowItemEntity) o;
        return Objects.equals(parseStrategy, that.parseStrategy) &&
               Objects.equals(fieldName, that.fieldName) &&
               Objects.equals(pattern, that.pattern) &&
               Objects.equals(matchStrategy, that.matchStrategy);
    }

    /**
     * 哈希码
     *
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(parseStrategy, fieldName, pattern, matchStrategy);
    }

    /**
     * 字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return "GatewayParamFlowItemEntity{" +
               "parseStrategy=" + parseStrategy +
               ", fieldName='" + fieldName + '\'' +
               ", pattern='" + pattern + '\'' +
               ", matchStrategy=" + matchStrategy +
               '}';
    }
}
