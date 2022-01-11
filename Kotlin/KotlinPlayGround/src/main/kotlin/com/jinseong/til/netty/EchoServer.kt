package com.jinseong.til.netty

import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.ByteBuf
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import java.nio.ByteBuffer
import java.nio.charset.Charset

fun main() {
    val bossGroup = NioEventLoopGroup(1)
    val worketGroup = NioEventLoopGroup()

    val b = ServerBootstrap()
    b.group(bossGroup, worketGroup)
        .channel(NioServerSocketChannel::class.java)
        .childHandler(object : ChannelInitializer<SocketChannel>() {
            override fun initChannel(ch: SocketChannel) {
                val pipeline = ch.pipeline()
                pipeline.addLast(EchoServerHandler())
            }
        })

    val f = b.bind(8888).sync()
    f.channel().closeFuture().sync()

    worketGroup.shutdownGracefully()
    bossGroup.shutdownGracefully()
}

class EchoServerHandler : ChannelInboundHandlerAdapter() {
    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.flush()
    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val message = (msg as ByteBuf).toString()
        println(message)
        ctx.write(msg)
    }
}
