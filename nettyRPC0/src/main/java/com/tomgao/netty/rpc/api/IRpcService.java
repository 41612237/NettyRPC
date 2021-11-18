package com.tomgao.netty.rpc.api;

/**
 * @author tomgao
 * @Description
 * @Date 创建于 2021/11/17
 */
public interface IRpcService {

    int add(int a, int b);

    int sub(int a, int b);

    int mult(int a, int b);

    int div(int a, int b);
}
