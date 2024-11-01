/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package com.alibaba.csp.sentinel.dashboard.domain.cluster.state;

import lombok.Getter;

/**
 * 群集客户端状态包装器
 *
 * @author Eric Zhao
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.4.1
 * @since 2024/9/3
 */
@Getter
public class AppClusterClientStateWrapVO {

    /**
     * 客户端的唯一标识符
     */
    private String id;

    /**
     * 客户端的命令端口
     */
    private Integer commandPort;

    /**
     * 客户端的 IP 地址
     */
    private String ip;

    /**
     * 客户端的状态信息
     */
    private ClusterClientStateVO state;

    /**
     * 设置客户端的 ID。
     *
     * @param id 客户端的 ID 设置
     * @return AppClusterClientStateWrapVO 的当前实例进行链式调用
     */
    public AppClusterClientStateWrapVO setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 设置客户端的 IP 地址
     *
     * @param ip 客户端的 IP 地址进行设置
     * @return AppClusterClientStateWrapVO 的当前实例进行链式调用
     */
    public AppClusterClientStateWrapVO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    /**
     * 设置客户端的状态
     *
     * @param state 要设置的客户端状态信息
     * @return 用于链调用的 AppClusterClientStateWrapVO 的当前实例
     */
    public AppClusterClientStateWrapVO setState(ClusterClientStateVO state) {
        this.state = state;
        return this;
    }

    /**
     * 设置客户端的命令端口
     *
     * @param commandPort 客户端的命令端口设置
     * @return AppClusterClientStateWrapVO 的当前实例进行链式调用
     */
    public AppClusterClientStateWrapVO setCommandPort(Integer commandPort) {
        this.commandPort = commandPort;
        return this;
    }

    /**
     * 重写 toString 方法，以便于调试和记录客户端的状态信息
     *
     * @return Client 端状态信息的字符串表示形式
     */
    @Override
    public String toString() {
        return "AppClusterClientStateWrapVO{" +
               "id='" + id + '\'' +
               ", commandPort=" + commandPort +
               ", ip='" + ip + '\'' +
               ", state=" + state +
               '}';
    }
}
