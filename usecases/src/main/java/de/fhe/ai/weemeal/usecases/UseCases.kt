package de.fhe.ai.weemeal.usecases

import de.fhe.ai.weemeal.repository.RecipeRepository

class GetUsers(private val repository: de.fhe.ai.weemeal.repository.RecipeRepository) {
    operator fun invoke() = repository.getRecipes()
}
