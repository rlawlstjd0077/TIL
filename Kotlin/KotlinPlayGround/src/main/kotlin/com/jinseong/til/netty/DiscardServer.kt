package com.jinseong.til.netty

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInitializer
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel

class DiscardServer {
    fun main() {
        val bossGroup = NioEventLoopGroup(1)
        val worketGroup = NioEventLoopGroup()

        val b = ServerBootstrap()
        b.group(bossGroup, worketGroup)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(object : ChannelInitializer<SocketChannel>() {
                override fun initChannel(ch: SocketChannel) {
                    val pipeline = ch.pipeline()
                    pipeline.addLast(DiscardServerHandler())
                }
            })

        val f = b.bind(8888).sync()
        f.channel().closeFuture().sync()

        worketGroup.shutdownGracefully()
        bossGroup.shutdownGracefully()
    }
}

class DiscardServerHandler: SimpleChannelInboundHandler<Any>() {
    override fun channelRead0(ctx: ChannelHandlerContext, msg: Any) {
        println(msg)
    }
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }
}

fun main() {
    val discardServer = DiscardServer()
    discardServer.main()
}
