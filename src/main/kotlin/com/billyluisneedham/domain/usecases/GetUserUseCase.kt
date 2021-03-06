package com.billyluisneedham.domain.usecases

import com.billyluisneedham.data.UserRepository
import com.billyluisneedham.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserByIdUseCase(private val repo: UserRepository) {

    fun run(id: String): Flow<Result<User?>> = flow {
        emit(repo.getUserById(id))

    }
}