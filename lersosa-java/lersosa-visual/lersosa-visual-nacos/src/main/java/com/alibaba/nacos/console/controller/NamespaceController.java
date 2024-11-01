/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package com.alibaba.nacos.console.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.auth.annotation.Secured;
import com.alibaba.nacos.common.model.RestResult;
import com.alibaba.nacos.common.model.RestResultUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.console.paramcheck.ConsoleDefaultHttpParamExtractor;
import com.alibaba.nacos.core.namespace.model.Namespace;
import com.alibaba.nacos.core.namespace.repository.NamespacePersistService;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import com.alibaba.nacos.core.service.NamespaceOperationService;
import com.alibaba.nacos.plugin.auth.constant.ActionTypes;
import com.alibaba.nacos.plugin.auth.impl.constant.AuthConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 命名空间服务
 *
 * @author Nacos
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.2.0
 * @since 2024/7/31
 */
@RestController
@RequestMapping("/v1/console/namespaces")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ExtractorManager.Extractor(httpExtractor = ConsoleDefaultHttpParamExtractor.class)
public class NamespaceController {

    /**
     * 命名空间 ID 最大长度
     */
    private static final int NAMESPACE_ID_MAX_LENGTH = 128;

    /**
     * 命名空间 ID 正则表达式
     */
    private final NamespacePersistService namespacePersistService;

    /**
     * 命名空间名称正则表达式
     */
    private final NamespaceOperationService namespaceOperationService;

    /**
     * 命名空间 ID 正则表达式
     */
    private final Pattern namespaceIdCheckPattern = Pattern.compile("^[\\w-]+");

    /**
     * 命名空间名称正则表达式
     */
    private final Pattern namespaceNameCheckPattern = Pattern.compile("^[^@#$%^&*]+$");

    /**
     * 获取命名空间列表
     *
     * @return 命名空间列表
     * @apiNote 获取命名空间列表
     */
    @GetMapping
    public RestResult<List<Namespace>> getNamespaces() {
        return RestResultUtils.success(namespaceOperationService.getNamespaceList());
    }

    /**
     * 按命名空间 ID 获取命名空间所有信息
     *
     * @param namespaceId 命名空间 ID
     * @return 命名空间所有信息
     * @throws NacosException 命名空间不存在异常
     * @apiNote 按命名空间 ID 获取命名空间所有信息
     */
    @GetMapping(params = "show=all")
    public Namespace getNamespace(@RequestParam("namespaceId") String namespaceId) throws NacosException {
        return namespaceOperationService.getNamespace(namespaceId);
    }

    /**
     * 创建命名空间
     *
     * @param namespaceName 命名空间名称
     * @param namespaceDesc 命名空间描述
     * @return 是否创建确定
     * @apiNote 创建命名空间
     */
    @PostMapping
    @Secured(resource = AuthConstants.CONSOLE_RESOURCE_NAME_PREFIX + "namespaces", action = ActionTypes.WRITE)
    public Boolean createNamespace(
            @RequestParam("customNamespaceId") String namespaceId,
            @RequestParam("namespaceName") String namespaceName,
            @RequestParam(value = "namespaceDesc", required = false) String namespaceDesc) {
        if (StringUtils.isBlank(namespaceId)) {
            namespaceId = UUID.randomUUID().toString();
        } else {
            namespaceId = namespaceId.trim();
            if (!namespaceIdCheckPattern.matcher(namespaceId).matches()) {
                return false;
            }
            if (namespaceId.length() > NAMESPACE_ID_MAX_LENGTH) {
                return false;
            }
            if (namespacePersistService.tenantInfoCountByTenantId(namespaceId) > 0) {
                return false;
            }
        }
        if (!namespaceNameCheckPattern.matcher(namespaceName).matches()) {
            return false;
        }
        try {
            return namespaceOperationService.createNamespace(namespaceId, namespaceName, namespaceDesc);
        } catch (NacosException e) {
            return false;
        }
    }

    /**
     * 检查命名空间 ID 是否存在
     *
     * @param namespaceId 命名空间 ID
     * @return 如果存在，则为 true，否则为 false
     * @apiNote 检查命名空间 ID 是否存在
     */
    @GetMapping(params = "checkNamespaceIdExist=true")
    public Boolean checkNamespaceIdExist(@RequestParam("customNamespaceId") String namespaceId) {
        if (StringUtils.isBlank(namespaceId)) {
            return false;
        }
        return (namespacePersistService.tenantInfoCountByTenantId(namespaceId) > 0);
    }

    /**
     * 编辑命名空间
     *
     * @param namespace         命名空间
     * @param namespaceShowName 命名空间显示名称
     * @param namespaceDesc     命名空间描述
     * @return 是否编辑确定
     * @apiNote 编辑命名空间
     */
    @PutMapping
    @Secured(resource = AuthConstants.CONSOLE_RESOURCE_NAME_PREFIX + "namespaces", action = ActionTypes.WRITE)
    public Boolean editNamespace(
            @RequestParam("namespace") String namespace,
            @RequestParam("namespaceShowName") String namespaceShowName,
            @RequestParam(value = "namespaceDesc", required = false) String namespaceDesc) {
        if (!namespaceNameCheckPattern.matcher(namespaceShowName).matches()) {
            return false;
        }
        return namespaceOperationService.editNamespace(namespace, namespaceShowName, namespaceDesc);
    }

    /**
     * 按 ID 删除命名空间
     *
     * @param namespaceId 命名空间 ID
     * @return 是否删除确定
     * @apiNote 按 ID 删除命名空间
     */
    @DeleteMapping
    @Secured(resource = AuthConstants.CONSOLE_RESOURCE_NAME_PREFIX + "namespaces", action = ActionTypes.WRITE)
    public Boolean deleteNamespace(@RequestParam("namespaceId") String namespaceId) {
        return namespaceOperationService.removeNamespace(namespaceId);
    }
}
