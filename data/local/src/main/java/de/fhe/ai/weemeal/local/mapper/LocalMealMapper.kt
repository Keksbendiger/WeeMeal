package de.fhe.ai.weemeal.local.mapper

import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.local.entity.MealEntity

fun MealEntity.toDomain() = Meal(
    internalId = this.id,
    recipe = null,
    servings = this.servings,
    cookColor = this.cookColor,
    cookingDate = this.cookingDate,
    shoppingListCreatedAt = this.shoppingListCreatedAt,
)

fun Meal.fromDomain() = MealEntity(
    id = this.internalId,
    recipeId = recipe!!.internalId,
    servings = this.servings,
    cookColor = this.cookColor,
    cookingDate = this.cookingDate,
    shoppingListCreatedAt = this.shoppingListCreatedAt,
)
