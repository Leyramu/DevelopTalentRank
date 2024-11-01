/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package leyramu.framework.lersosa.gateway.api.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import leyramu.framework.lersosa.common.core.utils.ServletUtils;
import leyramu.framework.lersosa.common.core.utils.StringUtils;
import leyramu.framework.lersosa.gateway.api.config.properties.CaptchaProperties;
import leyramu.framework.lersosa.gateway.api.service.ValidateCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 验证码过滤器
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/10/18
 */
@Component
@RequiredArgsConstructor
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {

    /**
     * 验证码匹配链接
     */
    private final static String[] VALIDATE_URL = new String[]{"/auth/login", "/auth/register"};

    /**
     * 验证码服务
     */
    private final ValidateCodeService validateCodeService;

    /**
     * 验证码开关
     */
    private final CaptchaProperties captchaProperties;

    /**
     * 验证码key
     */
    private static final String CODE = "code";

    /**
     * 验证码key
     */
    private static final String UUID = "uuid";

    /**
     * 验证码过滤器
     *
     * @param config 配置信息
     * @return 验证码过滤器
     */
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!StringUtils.equalsAnyIgnoreCase(request.getURI().getPath(), VALIDATE_URL) || !captchaProperties.getEnabled()) {
                return chain.filter(exchange);
            }

            return resolveBodyFromRequest(request)
                    .flatMap(rspStr -> {
                        try {
                            JSONObject obj = JSON.parseObject(rspStr);
                            validateCodeService.checkCaptcha(obj.getString(CODE), obj.getString(UUID));
                            return chain.filter(exchange);
                        } catch (Exception e) {
                            return ServletUtils.webFluxResponseWriter(exchange.getResponse(), e.getMessage());
                        }
                    });
        };
    }

    /**
     * 获取请求Body
     *
     * @param serverHttpRequest 请求
     * @return body
     */
    private Mono<String> resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        return DataBufferUtils.join(serverHttpRequest.getBody())
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    return new String(bytes, StandardCharsets.UTF_8);
                });
    }
}
