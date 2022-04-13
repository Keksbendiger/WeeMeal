package de.fhe.ai.weemeal.domain

class GetUsers(private val repository: Repository) {
    operator fun invoke() = repository.getUsers()
}