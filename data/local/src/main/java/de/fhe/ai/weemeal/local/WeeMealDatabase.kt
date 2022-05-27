package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.fhe.ai.weemeal.local.dao.IngredientEntityDao
import de.fhe.ai.weemeal.local.dao.MealEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeIngredientEntityDao
import de.fhe.ai.weemeal.local.entity.IngredientEntity
import de.fhe.ai.weemeal.local.entity.MealEntity
import de.fhe.ai.weemeal.local.entity.RecipeEntity
import de.fhe.ai.weemeal.local.entity.RecipeIngredientEntity

@Database(
    entities = [
        IngredientEntity::class,
        MealEntity::class,
        RecipeEntity::class,
        RecipeIngredientEntity::class,
    ],
    version = 1
)
abstract class WeeMealDatabase : RoomDatabase() {
    abstract fun ingredientEntityDao(): IngredientEntityDao
    abstract fun mealEntityDao(): MealEntityDao
    abstract fun recipeEntityDao(): RecipeEntityDao
    abstract fun recipeIngredientEntityDao(): RecipeIngredientEntityDao

    companion object {
        var db: WeeMealDatabase? = null

        private fun getDatabase(app: Context): WeeMealDatabase {
            if (db == null) {
                db = Room.databaseBuilder(app, WeeMealDatabase::class.java, "weemeal-database")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return db as WeeMealDatabase
        }

        fun getIngredientEntityDao(app: Context): IngredientEntityDao {
            return getDatabase(app).ingredientEntityDao()
        }

        fun getMealEntityDao(app: Context): MealEntityDao {
            return getDatabase(app).mealEntityDao()
        }

        fun getRecipeEntityDao(app: Context): RecipeEntityDao {
            return getDatabase(app).recipeEntityDao()
        }

        fun getRecipeIngredientEntityDao(app: Context): RecipeIngredientEntityDao {
            return getDatabase(app).recipeIngredientEntityDao()
        }
    }
}
