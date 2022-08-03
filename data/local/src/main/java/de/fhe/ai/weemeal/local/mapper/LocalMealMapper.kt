package de.fhe.ai.weemeal.local.mapper

import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.local.entity.MealEntity

/**
 * Mapper for MealEntity and Meal
 */
fun MealEntity.toDomain() = Meal(
    internalId = this.id,
    recipe = Recipe(),
    servings = this.servings,
    cookColor = this.cookColor,
    cookingDate = this.cookingDate,
    shoppingListCreatedAt = this.shoppingListCreatedAt,
)

/**
 * Mapper for Meal and MealEntity
 */
fun Meal.fromDomain() = MealEntity(
    id = this.internalId,
    recipeId = recipe.internalId,
    servings = this.servings,
    cookColor = this.cookColor,
    cookingDate = this.cookingDate,
    shoppingListCreatedAt = this.shoppingListCreatedAt,
)
