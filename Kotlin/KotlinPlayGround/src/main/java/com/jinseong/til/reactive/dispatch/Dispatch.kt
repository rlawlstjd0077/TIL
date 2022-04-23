package com.jinseong.til.reactive.dispatch

import java.awt.image.PixelGrabber
import java.util.function.Consumer



/**
 * @author Jay
 */
interface Post {
    fun postOn(sns: SNS)
}

class Picture: Post {
    override fun postOn(sns: SNS) {
        sns.post(this)
    }
}

class Text: Post {
    override fun postOn(sns: SNS) {
        sns.post(this)
    }
}

interface SNS {
    fun post(post: Text)
    fun post(post: Picture)
}

class FaceBook: SNS {
    override fun post(post: Text) {
        println("text-facebook")
    }

    override fun post(post: Picture) {
        println("picture-facebook")
    }
}

class Twitter: SNS {
    override fun post(post: Text) {
        println("text-twitter")
    }

    override fun post(post: Picture) {
        println("picture-twitter")
    }
}

fun main() {
    val posts = listOf(Text(), Picture())
    val sns = listOf(FaceBook(), Twitter())

    posts.forEach { post ->
        sns.forEach { sns ->
            post.postOn(sns)
        }
    }
}
