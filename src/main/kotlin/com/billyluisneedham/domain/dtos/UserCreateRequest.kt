package com.billyluisneedham.domain.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UserCreateRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
)
