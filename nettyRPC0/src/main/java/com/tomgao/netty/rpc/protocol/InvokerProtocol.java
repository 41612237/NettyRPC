package com.tomgao.netty.rpc.protocol;

import java.io.Serializable;

/**
 * @author tomgao
 * @Description
 * @Date 创建于 2021/11/17
 */
public class InvokerProtocol implements Serializable {

    // 类名
    private String className;

    // 函数名
    private String methodName;

    // 参数类型
    private Class<?>[] params;

    // 参数列表
    private Object[] values;
}
