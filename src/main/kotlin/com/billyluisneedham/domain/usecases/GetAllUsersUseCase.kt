package com.billyluisneedham.domain.usecases

import com.billyluisneedham.data.UserRepository
import com.billyluisneedham.domain.models.User

class GetAllUsersUseCase(private val repo: UserRepository) {
    fun run(): Result<List<User>> = repo.getAllUsers()

}