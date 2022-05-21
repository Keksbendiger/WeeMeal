package de.darthkali.mocks

import de.darthkali.domain.models.Recipe
import io.bloco.faker.Faker


object RecipeMock {
    private var faker: Faker = Faker()

    fun generateRecipe():Recipe{
        return Recipe(name = faker.food.dish())
    }

    fun generateList(amount: Int): List<Recipe> {
        assert(amount >= 0)
        val recipeList: MutableList<Recipe> = mutableListOf()

        for (i in 1..amount) {
            recipeList.add(generateRecipe())
        }
        return recipeList
    }
}
