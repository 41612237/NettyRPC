package com.tomgao.netty.rpc.provider;

import com.tomgao.netty.rpc.api.IRpcService;

/**
 * @author tomgao
 * @Description
 * @Date 创建于 2021/11/17
 */
public class RpcServiceImpl implements IRpcService {
    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mult(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }
}
