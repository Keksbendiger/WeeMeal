package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.fhe.ai.weemeal.local.dao.IngredientEntityDao
import de.fhe.ai.weemeal.local.dao.MealEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeIngredientEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeTagEntityDao
import de.fhe.ai.weemeal.local.dao.TagEntityDao
import de.fhe.ai.weemeal.local.entity.IngredientEntity
import de.fhe.ai.weemeal.local.entity.MealEntity
import de.fhe.ai.weemeal.local.entity.RecipeEntity
import de.fhe.ai.weemeal.local.entity.RecipeIngredientEntity
import de.fhe.ai.weemeal.local.entity.RecipeTagEntity
import de.fhe.ai.weemeal.local.entity.TagEntity
import de.fhe.ai.weemeal.local.mapper.Converters

@Database(
    entities = [
        IngredientEntity::class,
        MealEntity::class,
        RecipeEntity::class,
        RecipeIngredientEntity::class,
        RecipeTagEntity::class,
        TagEntity::class,
    ],
    version = 3
)
@TypeConverters(Converters::class)
abstract class WeeMealDatabase : RoomDatabase() {
    abstract fun ingredientEntityDao(): IngredientEntityDao
    abstract fun mealEntityDao(): MealEntityDao
    abstract fun recipeEntityDao(): RecipeEntityDao
    abstract fun recipeIngredientEntityDao(): RecipeIngredientEntityDao
    abstract fun recipeTagEntityDao(): RecipeTagEntityDao
    abstract fun tagEntityDao(): TagEntityDao

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

        fun getRecipeTagEntityDao(app: Context): RecipeTagEntityDao {
            return getDatabase(app).recipeTagEntityDao()
        }

        fun getTagEntityDao(app: Context): TagEntityDao {
            return getDatabase(app).tagEntityDao()
        }
    }
}
