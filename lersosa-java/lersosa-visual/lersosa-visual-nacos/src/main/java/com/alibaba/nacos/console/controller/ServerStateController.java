/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package com.alibaba.nacos.console.controller;

import com.alibaba.nacos.common.model.RestResult;
import com.alibaba.nacos.common.model.RestResultUtils;
import com.alibaba.nacos.console.paramcheck.ConsoleDefaultHttpParamExtractor;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import com.alibaba.nacos.sys.env.EnvUtil;
import com.alibaba.nacos.sys.module.ModuleState;
import com.alibaba.nacos.sys.module.ModuleStateHolder;
import com.alibaba.nacos.sys.utils.DiskUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.nacos.common.utils.StringUtils.*;

/**
 * 服务器状态控制器
 *
 * @author xingxuechao
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.2.0
 * @since 2024/7/31
 */
@RestController
@RequestMapping("/v1/console/server")
@ExtractorManager.Extractor(httpExtractor = ConsoleDefaultHttpParamExtractor.class)
public class ServerStateController {

    /**
     * 公告文件名
     */
    private static final String ANNOUNCEMENT_FILE = "announcement.conf";

    /**
     * 引导文件名
     */
    private static final String GUIDE_FILE = "console-guide.conf";

    /**
     * 获取当前服务器的服务器状态
     *
     * @return 状态 JSON
     * @apiNote 获取当前服务器的服务器状态
     */
    @GetMapping("/state")
    public ResponseEntity<Map<String, String>> serverState() {
        Map<String, String> serverState = new HashMap<>(4);
        for (ModuleState each : ModuleStateHolder.getInstance().getAllModuleStates()) {
            each.getStates().forEach((s, o) -> serverState.put(s, null == o ? null : o.toString()));
        }
        return ResponseEntity.ok().body(serverState);
    }

    /**
     * 获取公告
     *
     * @param language 语言
     * @return 公告
     * @apiNote 获取公告
     */
    @GetMapping("/announcement")
    public RestResult<String> getAnnouncement(
            @RequestParam(required = false, name = "language", defaultValue = "zh-CN") String language) {
        String file = ANNOUNCEMENT_FILE.substring(0, ANNOUNCEMENT_FILE.length() - 5) + "_" + language + ".conf";
        if (file.contains(TOP_PATH) || file.contains(FOLDER_SEPARATOR) || file.contains(WINDOWS_FOLDER_SEPARATOR)) {
            throw new IllegalArgumentException("Invalid filename");
        }
        File announcementFile = new File(EnvUtil.getConfPath(), file);
        String announcement = null;
        if (announcementFile.exists() && announcementFile.isFile()) {
            announcement = DiskUtils.readFile(announcementFile);
        }
        return RestResultUtils.success(announcement);
    }

    /**
     * 获取控制台引导信息
     *
     * @return 控制台引导信息
     * @apiNote 获取控制台引导信息
     */
    @GetMapping("/guide")
    public RestResult<String> getConsoleUiGuide() {
        File guideFile = new File(EnvUtil.getConfPath(), GUIDE_FILE);
        String guideInformation = null;
        if (guideFile.exists() && guideFile.isFile()) {
            guideInformation = DiskUtils.readFile(guideFile);
        }
        return RestResultUtils.success(guideInformation);
    }
}