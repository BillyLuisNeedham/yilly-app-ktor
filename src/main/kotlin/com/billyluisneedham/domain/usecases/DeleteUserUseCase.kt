package com.billyluisneedham.domain.usecases

import com.billyluisneedham.data.UserRepository

class DeleteUserUseCase(private val repo: UserRepository) {
    suspend fun run(id: String): Result<Unit> =
        repo.deleteUserById(id)
}