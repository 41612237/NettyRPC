package com.tomgao.netty.rpc.provider;

import com.tomgao.netty.rpc.api.IRpcHelloService;

/**
 * @author tomgao
 * @Description
 * @Date 创建于 2021/11/17
 */
public class RpcHelloServiceImpl implements IRpcHelloService {
    public String hello(String name) {
        return "hello" + name;
    }
}
