/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

/**
 * Authority rule service.
 */
angular.module('sentinelDashboardApp').service('AuthorityRuleService', ['$http', function ($http) {
    this.queryMachineRules = function(app, ip, port) {
        var param = {
            app: app,
            ip: ip,
            port: port
        };
        return $http({
            url: '/authority/rules',
            params: param,
            method: 'GET'
        });
    };

    this.addNewRule = function(rule) {
        return $http({
            url: '/authority/rule',
            data: rule,
            method: 'POST'
        });
    };

    this.saveRule = function (entity) {
        return $http({
            url: '/authority/rule/' + entity.id,
            data: entity,
            method: 'PUT'
        });
    };

    this.deleteRule = function (entity) {
        return $http({
            url: '/authority/rule/' + entity.id,
            method: 'DELETE'
        });
    };

    this.checkRuleValid = function checkRuleValid(rule) {
        if (rule.resource === undefined || rule.resource === '') {
            alert('资源名称不能为空');
            return false;
        }
        if (rule.limitApp === undefined || rule.limitApp === '') {
            alert('流控针对应用不能为空');
            return false;
        }
        if (rule.strategy === undefined) {
            alert('必须选择黑白名单模式');
            return false;
        }
        return true;
    };
}]);