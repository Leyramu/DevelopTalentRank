/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package com.alibaba.csp.sentinel.dashboard.auth;

import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 简单的 Web 身份验证服务实施
 *
 * @author cdfive
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.6.0
 * @since 2024/9/3
 */
public class SimpleWebAuthServiceImpl implements AuthService<HttpServletRequest> {

    /**
     * 默认的 Web 身份验证会话键
     */
    public static final String WEB_SESSION_KEY = "session_sentinel_admin";

    /**
     * 获取当前用户信息
     *
     * @param request 请求
     * @return 用户信息
     */
    @Override
    public AuthUser getAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object sentinelUserObj = session.getAttribute(SimpleWebAuthServiceImpl.WEB_SESSION_KEY);
        if (sentinelUserObj instanceof AuthUser) {
            return (AuthUser) sentinelUserObj;
        }

        return null;
    }

    /**
     * 默认的 Web 身份验证用户实现
     */
    @AllArgsConstructor
    public static final class SimpleWebAuthUserImpl implements AuthUser {

        /**
         * 用户名
         */
        private final String username;

        /**
         * 检查目标权限
         *
         * @param target        要检查的目标
         * @param privilegeType 要检查的权限类型
         * @return 如果目标具有特定权限，则返回 true，否则返回 false
         */
        @Override
        public boolean authTarget(String target, PrivilegeType privilegeType) {
            return true;
        }

        /**
         * 是否为超级用户
         *
         * @return 如果为超级用户，则返回 true，否则返回 false
         */
        @Override
        public boolean isSuperUser() {
            return true;
        }

        /**
         * 获取用户昵称
         *
         * @return 用户昵称
         */
        @Override
        public String getNickName() {
            return username;
        }

        /**
         * 获取用户登录名
         *
         * @return 用户登录名
         */
        @Override
        public String getLoginName() {
            return username;
        }

        /**
         * 获取用户ID
         *
         * @return 用户ID
         */
        @Override
        public String getId() {
            return username;
        }
    }
}
