package com.billyluisneedham.domain.usecases

import com.billyluisneedham.data.UserRepository
import com.billyluisneedham.domain.dtos.UserCreateRequest
import com.billyluisneedham.domain.models.User

class CreateUserUseCase(private val repo: UserRepository) {
    suspend fun run(userReq: UserCreateRequest): Result<User?> {
        return repo.addUser(userReq)
    }
}