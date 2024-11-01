/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package leyramu.framework.lersosa.system.controller;

import jakarta.servlet.http.HttpServletResponse;
import leyramu.framework.lersosa.common.core.utils.StringUtils;
import leyramu.framework.lersosa.common.core.utils.poi.ExcelUtil;
import leyramu.framework.lersosa.common.core.web.controller.BaseController;
import leyramu.framework.lersosa.common.core.web.domain.AjaxResult;
import leyramu.framework.lersosa.common.core.web.page.TableDataInfo;
import leyramu.framework.lersosa.common.log.annotation.Log;
import leyramu.framework.lersosa.common.log.enums.BusinessType;
import leyramu.framework.lersosa.common.security.annotation.RequiresPermissions;
import leyramu.framework.lersosa.common.security.utils.SecurityUtils;
import leyramu.framework.lersosa.system.api.domain.SysDictData;
import leyramu.framework.lersosa.system.service.ISysDictDataService;
import leyramu.framework.lersosa.system.service.ISysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/10/19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict/data")
public class SysDictDataController extends BaseController {

    /**
     * 字典数据服务
     */
    private final ISysDictDataService dictDataService;

    /**
     * 字典类型服务
     */
    private final ISysDictTypeService dictTypeService;

    /**
     * 查询字典数据列表
     *
     * @param dictData 字典
     * @return 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("system:dict:list")
    public TableDataInfo list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    /**
     * 导出字典数据列表
     *
     * @param response 响应
     * @param dictData 字典
     * @apiNote 导出字典数据列表
     */
    @PostMapping("/export")
    @RequiresPermissions("system:dict:export")
    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    public void export(HttpServletResponse response, SysDictData dictData) {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<>(SysDictData.class);
        util.exportExcel(response, list, "字典数据");
    }

    /**
     * 查询字典数据详细
     *
     * @param dictCode 字典ID
     * @return 字典数据
     * @apiNote 查询字典数据详细
     */
    @GetMapping(value = "/{dictCode}")
    @RequiresPermissions("system:dict:query")
    public AjaxResult getInfo(@PathVariable Long dictCode) {
        return success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 字典数据
     * @apiNote 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<>();
        }
        return success(data);
    }

    /**
     * 新增字典类型
     *
     * @param dict 字典信息
     * @return 结果
     * @apiNote 新增字典类型
     */
    @PostMapping
    @RequiresPermissions("system:dict:add")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    public AjaxResult add(@Validated @RequestBody SysDictData dict) {
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     *
     * @param dict 字典信息
     * @return 结果
     * @apiNote 修改保存字典类型
     */
    @PutMapping
    @RequiresPermissions("system:dict:edit")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(@Validated @RequestBody SysDictData dict) {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     *
     * @param dictCodes 字典ID
     * @return 结果
     * @apiNote 删除字典类型
     */
    @DeleteMapping("/{dictCodes}")
    @RequiresPermissions("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    public AjaxResult remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }
}
