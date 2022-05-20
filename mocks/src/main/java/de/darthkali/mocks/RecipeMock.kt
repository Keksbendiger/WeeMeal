package de.darthkali.mocks

import de.darthkali.domain.models.recipe.Recipe

object RecipeMock {

    val recipe = Recipe(id = 1, name = "Pizza")

    val recipeList = listOf(
        Recipe(id = 1, name = "Pizza"),
        Recipe(id = 2, name = "Pasta"),
        Recipe(id = 3, name = "Döner"),
        Recipe(id = 4, name = "Salat")
    )
}
