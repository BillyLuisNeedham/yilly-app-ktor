package com.billyluisneedham

import com.billyluisneedham.infrastructure.plugins.configureMonitoring
import com.billyluisneedham.infrastructure.plugins.configureRouting
import com.billyluisneedham.infrastructure.plugins.configureSecurity
import com.billyluisneedham.infrastructure.plugins.configureSerialization
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureMonitoring()
    configureRouting()
    configureSerialization()
    configureSecurity()
}
