package com.jinseong.til.netty

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.nio.charset.Charset

fun main() {
    val nonBlockingServer = NonBlockingServer()
    nonBlockingServer.startEchoServer()
}

class NonBlockingServer {
    var keepDataTrack = hashMapOf<SocketChannel, MutableList<ByteArray>>()
    val buffer = ByteBuffer.allocate(2 * 1024)

    fun startEchoServer() {
        val selector = Selector.open()
        val serverSocketChannel = ServerSocketChannel.open()

        if (serverSocketChannel.isOpen && selector.isOpen) {
            serverSocketChannel.configureBlocking(false)
            serverSocketChannel.bind(InetSocketAddress(8888))

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)
            println("접속 대기중")

            while (true) {
                selector.select()
                for (key in selector.selectedKeys()) {

                    if (!key.isValid) {
                        continue
                    }

                    when {
                        !key.isValid -> continue
                        key.isAcceptable -> this.acceptOP(key, selector)
                        key.isReadable -> this.readOP(key)
                        key.isWritable -> this.writeOP(key)
                        else -> println("서버 소켓을 생성하지 못했습니다.")
                    }
                }
            }
        }
    }

    fun acceptOP(key: SelectionKey, selector: Selector) {
        val channel = key.channel() as ServerSocketChannel
        val socketChannel = channel.accept()
        socketChannel.configureBlocking(false)

        println("클라이언트 연결됨: " + socketChannel.remoteAddress)

        keepDataTrack.put(socketChannel, mutableListOf())
        socketChannel.register(selector, SelectionKey.OP_READ)
    }

    fun readOP(key: SelectionKey) {
        val socketChannel = key.channel() as SocketChannel
        buffer.clear()
        var numRead = -1

        numRead = socketChannel.read(buffer)

        if(numRead == -1) {
            keepDataTrack.remove(socketChannel)
            println("클라이언트 연결 종료: ${socketChannel.remoteAddress}")
            socketChannel.close()
            key.cancel()
            return
        }

        val data = ByteArray(numRead)
        System.arraycopy(buffer.array(), 0, data, 0, numRead)
        println(String(data, Charset.forName("UTF-8")) + "from ${socketChannel.remoteAddress}")
        doEchoJob(key, data)
    }

    fun writeOP(key: SelectionKey) {
        val socketChannel = key.channel() as SocketChannel

        val channelData = keepDataTrack.getValue(socketChannel)
        for (channelDatum in channelData) {
            socketChannel.write(ByteBuffer.wrap(channelDatum))
        }

        key.interestOps(SelectionKey.OP_READ)
    }

    fun doEchoJob(key: SelectionKey, data: ByteArray) {
        val socketChannel = key.channel() as SocketChannel
        val channelData = keepDataTrack[socketChannel]!!
        channelData += channelData
    }
}
