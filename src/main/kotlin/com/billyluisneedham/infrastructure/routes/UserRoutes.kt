package com.billyluisneedham.infrastructure.routes

import com.billyluisneedham.data.UserRepository
import com.billyluisneedham.domain.dtos.UserCreateRequest
import com.billyluisneedham.domain.usecases.CreateUserUseCase
import com.billyluisneedham.domain.usecases.GetAllUsersUseCase
import com.billyluisneedham.domain.usecases.GetUserByIdUseCase
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take

// TODO refactor to use dependency injection
private val userRepo = UserRepository()
fun Route.userRouting() {
    route("/user") {

        get {
            GetAllUsersUseCase(userRepo).run()
                .onSuccess { users ->
                    println("users: $users")
                    call.respond(users)
                }
                .onFailure { throwable ->
                    handleServerError(
                        throwable = throwable,
                        call = call
                    )
                }
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )


            GetUserByIdUseCase(userRepo).run(id)
                .take(1).onEach { result ->
                    result.onSuccess { user ->
                        when (user == null) {
                            true -> call.respondText(
                                "user not found with id $id",
                                status = HttpStatusCode.NotFound
                            )
                            false -> call.respond(user)
                        }
                    }
                }.collect()
        }

        post {
            val userReq = call.receive<UserCreateRequest>()
            CreateUserUseCase(userRepo).run(userReq)
                .onSuccess {
                    call.respondText(
                        "User Created",
                        status = HttpStatusCode.Created
                    )
                }
                .onFailure {
                    handleServerError(
                        throwable = it,
                        call = call
                    )
                }
        }

    }
}

private suspend fun handleServerError(throwable: Throwable, call: ApplicationCall) {
    call.respondText(
        throwable.message ?: "Something went wrong",
        status = HttpStatusCode.InternalServerError
    )
}

