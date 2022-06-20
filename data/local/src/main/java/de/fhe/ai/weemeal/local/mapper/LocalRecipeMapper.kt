package de.fhe.ai.weemeal.local.mapper

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.local.entity.RecipeEntity

fun RecipeEntity.toDomain() = Recipe(
    internalId = this.id,
    name = this.name,
    defaultServings = this.defaultServings,
    timePreparation = this.timePreparation,
    timeActiveCooking = this.timeActiveCooking,
    timeOverall = this.timeOverall,
    instructions = this.instructions,
    image = this.image,
    defaultIngredients = listOf(),
    tags = listOf()
)

fun Recipe.fromDomain() = RecipeEntity(
    id = this.internalId,
    name = this.name,
    defaultServings = this.defaultServings,
    timePreparation = this.timePreparation,
    timeActiveCooking = this.timeActiveCooking,
    timeOverall = this.timeOverall,
    instructions = this.instructions,
    image = this.image,
)
