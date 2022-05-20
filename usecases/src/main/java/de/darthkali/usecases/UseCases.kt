package de.darthkali.usecases

import de.darthkali.repository.RecipeRepository

class GetUsers(private val repository: RecipeRepository) {
    operator fun invoke() = repository.getRecipes()
}
