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

package com.alibaba.csp.sentinel.dashboard.domain;

import lombok.Getter;

/**
 * 统一结果封装
 *
 * @author leyou
 * @author Eric Zhao
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/9/3
 */
@Getter
public class Result<R> {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据
     */
    private R data;

    /**
     * 创建一个表示操作成功的结果对象
     *
     * @param data 操作成功时返回的数据
     * @param <R>  返回数据的类型
     * @return 包含成功标志和数据的结果对象
     */
    public static <R> Result<R> ofSuccess(R data) {
        return new Result<R>()
                .setSuccess(true)
                .setMsg("success")
                .setData(data);
    }

    /**
     * 创建一个表示操作成功的结果对象，自定义成功消息
     *
     * @param msg 操作成功时的自定义消息
     * @param <R> 期望的返回数据类型
     * @return 包含成功标志和自定义消息的结果对象
     */
    public static <R> Result<R> ofSuccessMsg(String msg) {
        return new Result<R>()
                .setSuccess(true)
                .setMsg(msg);
    }

    /**
     * 创建一个表示操作失败的结果对象
     *
     * @param code 错误码，用于标识错误的类型
     * @param msg  错误消息，描述发生的错误
     * @param <R>  期望的返回数据类型
     * @return 包含失败标志、错误码和错误消息的结果对象
     */
    public static <R> Result<R> ofFail(int code, String msg) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 创建一个表示因异常而操作失败的结果对象
     *
     * @param code      错误码，用于标识错误的类型
     * @param throwable 异常对象，表示发生的异常
     * @param <R>       期望的返回数据类型
     * @return 包含失败标志、错误码和异常信息的结果对象
     */
    public static <R> Result<R> ofThrowable(int code, Throwable throwable) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return result;
    }

    /**
     * 设置结果对象的成功状态
     *
     * @param success 表示操作是否成功的布尔值
     * @return 当前结果对象实例，支持链式调用
     */
    public Result<R> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 设置结果对象的错误码
     *
     * @param code 错误码，用于标识错误的类型
     * @return 当前结果对象实例，支持链式调用
     */
    public Result<R> setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 设置结果对象的消息
     *
     * @param msg 消息，可以是成功消息或错误消息
     * @return 当前结果对象实例，支持链式调用
     */
    public Result<R> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 设置结果对象返回的数据
     *
     * @param data 操作返回的数据
     * @return 当前结果对象实例，支持链式调用
     */
    public Result<R> setData(R data) {
        this.data = data;
        return this;
    }

    /**
     * 重写toString方法，便于打印结果对象
     *
     * @return 结果对象的字符串表示形式
     */
    @Override
    public String toString() {
        return "Result{" +
               "success=" + success +
               ", code=" + code +
               ", msg='" + msg + '\'' +
               ", data=" + data +
               '}';
    }

}
