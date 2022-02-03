package com.billyluisneedham.data

import com.billyluisneedham.domain.dtos.UserCreateRequest
import com.billyluisneedham.domain.models.User

// TODO make abstract when DI is implemented
class UserRepository {

    private var users = listOf<User>()

    fun addUser(user: UserCreateRequest): Result<User> {
        val newUser = User(
            id = "${users.size + 1}",
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email
        )
        users = users.plus(newUser)
        println("new user $newUser")
        return Result.success(newUser)
    }

    fun getAllUsers(): Result<List<User>> {
        println("getAllUsers fired and returning: $users")
    return Result.success(users)
    }

    fun getUserById(id: String): Result<User?> {
        val result = users.filter { user ->
            user.id == id
        }

        return Result.success(result[0])
    }

    fun deleteUserById(id: String): Result<Unit> {
        users = users.filter { user ->
            user.id != id
        }

        return Result.success(Unit)
    }

}