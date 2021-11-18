package com.tomgao.netty.rpc.registry;

import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tomgao
 * @Description
 * @Date 创建于 2021/11/17
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {

    // 保存所有可用服务
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<String, Object>();

    // 保存所有相关的服务类
    private List<String> classNames = new ArrayList<String>();

    public RegistryHandler(){
        // 完成递归扫描
//        scannerClass
    }
}
