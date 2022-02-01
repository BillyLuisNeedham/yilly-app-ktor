package com.billyluisneedham.domain.usecases

import com.billyluisneedham.data.UserRepository
import com.billyluisneedham.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllUsersUseCase(private val repo: UserRepository) {
    fun run(): Flow<Result<List<User>>> = flow {
        emit(repo.getAllUsers())
    }
}