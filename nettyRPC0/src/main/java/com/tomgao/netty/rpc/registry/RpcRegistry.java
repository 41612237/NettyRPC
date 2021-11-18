package com.tomgao.netty.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.rmi.registry.Registry;

/**
 * @author tomgao
 * @Description
 * @Date 创建于 2021/11/17
 */
public class RpcRegistry {

    private int port;

    private RpcRegistry(int port) {
        this.port = port;
    }

    public void start() {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 自定义协议解码器
                            /**
                             * 5个参数
                             * 分别为框架的最大长度, 长度属性的偏移量, 长度字段的长度, 要添加到长度属性值的补偿值, 从解码帧中取出的第一个字节数
                             */
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                                    // 自定义协议编码器
                                    .addLast(new LengthFieldPrepender(4))
                                    // 对象参数类型编码器
                                    .addLast("encoder", new ObjectEncoder())
                                    // 对象参数类型解码器
                                    .addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                    .addLast(new RegistryHandler());


                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = b.bind(port).sync();
            System.out.println("GP RPC Registry start listen at " + port);
        } catch (InterruptedException e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new RpcRegistry(8080).start();
    }
}
