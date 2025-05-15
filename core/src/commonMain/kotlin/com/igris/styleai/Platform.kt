package com.igris.styleai

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform