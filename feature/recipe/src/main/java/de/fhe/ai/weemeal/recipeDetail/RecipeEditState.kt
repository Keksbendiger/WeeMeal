package de.fhe.ai.weemeal.recipeDetail

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.mocks.RecipeMock

data class RecipeEditState(
    var recipe: Recipe = RecipeMock.generateSingleObject()
)