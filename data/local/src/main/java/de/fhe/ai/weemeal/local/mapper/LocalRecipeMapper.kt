package de.fhe.ai.weemeal.local.mapper

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.local.entity.RecipeEntity

/**
 * Mapper for RecipeEntity and Recipe
 */
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

/**
 * Mapper for Recipe and RecipeEntity
 */
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
