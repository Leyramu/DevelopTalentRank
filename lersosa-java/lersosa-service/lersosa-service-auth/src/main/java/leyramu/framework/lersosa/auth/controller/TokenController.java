/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package leyramu.framework.lersosa.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import leyramu.framework.lersosa.auth.form.LoginBody;
import leyramu.framework.lersosa.auth.form.RegisterBody;
import leyramu.framework.lersosa.auth.service.SysLoginService;
import leyramu.framework.lersosa.common.core.domain.R;
import leyramu.framework.lersosa.common.core.utils.JwtUtils;
import leyramu.framework.lersosa.common.core.utils.StringUtils;
import leyramu.framework.lersosa.common.security.auth.AuthUtil;
import leyramu.framework.lersosa.common.security.service.TokenService;
import leyramu.framework.lersosa.common.security.utils.SecurityUtils;
import leyramu.framework.lersosa.system.api.model.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * token 控制
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/10/19
 */
@RestController
@RequiredArgsConstructor
public class TokenController {

    /**
     * 令牌服务
     */
    private final TokenService tokenService;

    /**
     * 登录服务
     */
    private final SysLoginService sysLoginService;

    /**
     * 登录接口
     *
     * @param form 登录参数
     * @return 响应结果
     * @apiNote 登录接口
     */
    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) {
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        return R.ok(tokenService.createToken(userInfo));
    }

    /**
     * 退出登录
     *
     * @param request 请求
     * @return 响应结果
     * @apiNote 退出登录
     */
    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtils.getUserName(token);
            AuthUtil.logoutByToken(token);
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    /**
     * 刷新令牌接口
     *
     * @param request 请求
     * @return 响应结果
     * @apiNote 刷新令牌接口
     */
    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    /**
     * 注册接口
     *
     * @param registerBody 注册参数
     * @return 响应结果
     * @apiNote 注册接口
     */
    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody) {
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }
}
