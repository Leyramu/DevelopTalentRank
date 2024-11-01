/*
 * Copyright (c) 2024 Leyramu. All rights reserved.
 * This project (Lersosa), including its source code, documentation, and any associated materials, is the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical reviews and certain other noncommercial uses permitted by copyright law.
 * For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at 2038322151@qq.com.
 * The author disclaims all warranties, express or implied, including but not limited to the warranties of merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any special, incidental, indirect, or consequential damages arising from the use of this software.
 * By using this project, users acknowledge and agree to abide by these terms and conditions.
 */

package leyramu.framework.lersosa.common.core.utils.uuid;

import leyramu.framework.lersosa.common.core.utils.DateUtils;
import leyramu.framework.lersosa.common.core.utils.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 序列生成类
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/10/19
 */
public class Seq {

    /**
     * 通用序列类型
     */
    public static final String COMM_SEQ_TYPE = "COMMON";

    /**
     * 上传序列类型
     */
    public static final String UPLOAD_SEQ_TYPE = "UPLOAD";

    /**
     * 通用接口序列数
     */
    private static final AtomicInteger COMM_SEQ = new AtomicInteger(1);

    /**
     * 上传接口序列数
     */
    private static final AtomicInteger UPLOAD_SEQ = new AtomicInteger(1);

    /**
     * 机器标识
     */
    private static final String MACHINE_CODE = "A";

    /**
     * 获取通用序列号
     *
     * @return 序列值
     */
    public static String getId() {
        return getId(COMM_SEQ_TYPE);
    }

    /**
     * 默认16位序列号 yyMMddHHmmss + 一位机器标识 + 3长度循环递增字符串
     *
     * @return 序列值
     */
    public static String getId(String type) {
        AtomicInteger atomicInt = COMM_SEQ;
        if (UPLOAD_SEQ_TYPE.equals(type)) {
            atomicInt = UPLOAD_SEQ;
        }
        return getId(atomicInt, 3);
    }

    /**
     * 通用接口序列号 yyMMddHHmmss + 一位机器标识 + length长度循环递增字符串
     *
     * @param atomicInt 序列数
     * @param length    数值长度
     * @return 序列值
     */
    public static String getId(AtomicInteger atomicInt, int length) {
        String result = DateUtils.dateTimeNow();
        result += MACHINE_CODE;
        result += getSeq(atomicInt, length);
        return result;
    }

    /**
     * 序列循环递增字符串[1, 10 的 (length)幂次方), 用0左补齐length位数
     *
     * @return 序列值
     */
    private synchronized static String getSeq(AtomicInteger atomicInt, int length) {
        int value = atomicInt.getAndIncrement();

        int maxSeq = (int) Math.pow(10, length);
        if (atomicInt.get() >= maxSeq) {
            atomicInt.set(1);
        }
        return StringUtils.padl(value, length);
    }
}
