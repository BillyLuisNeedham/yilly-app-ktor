package com.billyluisneedham

import io.ktor.application.*
import com.billyluisneedham.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureMonitoring()
    configureRouting()
    configureSerialization()
    configureSecurity()
}
