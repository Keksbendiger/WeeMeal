package de.fhe.ai.weemeal.data

import de.fhe.ai.weemeal.domain.User

fun UserEntity.toDomain() = User(this.text, this.id)
fun User.fromDomain() = UserEntity(this.name, this.id)
