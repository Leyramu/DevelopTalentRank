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

package com.alibaba.csp.sentinel.dashboard.util;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.csp.sentinel.util.function.Tuple2;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * 设备信息工具类
 *
 * @author Eric Zhao
 */
@NoArgsConstructor
public final class MachineUtils {

    /**
     * 解析机器 ip 的命令端口
     *
     * @param machineIp 机器 IP
     * @return 命令端口
     */
    public static Optional<Integer> parseCommandPort(String machineIp) {
        try {
            if (!machineIp.contains("@")) {
                return Optional.empty();
            }
            String[] str = machineIp.split("@");
            if (str.length <= 1) {
                return Optional.empty();
            }
            return Optional.of(Integer.parseInt(str[1]));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    /**
     * 解析机器 ip 和命令端口
     *
     * @param machineIp 机器 IP
     * @return 命令端口
     */
    public static Optional<Tuple2<String, Integer>> parseCommandIpAndPort(String machineIp) {
        try {
            if (StringUtil.isEmpty(machineIp) || !machineIp.contains("@")) {
                return Optional.empty();
            }
            String[] str = machineIp.split("@");
            if (str.length <= 1) {
                return Optional.empty();
            }
            return Optional.of(Tuple2.of(str[0], Integer.parseInt(str[1])));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
