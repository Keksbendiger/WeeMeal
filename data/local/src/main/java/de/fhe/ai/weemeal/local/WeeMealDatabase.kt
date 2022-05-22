package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.fhe.ai.weemeal.local.recipe.RecipeEntity
import de.fhe.ai.weemeal.local.recipe.RecipeEntityDao

@Database(entities = [RecipeEntity::class], version = 1)
abstract class WeeMealDatabase : RoomDatabase() {
    abstract fun recipeEntityDao(): RecipeEntityDao

    companion object {
        fun getDatabase(app: Context): WeeMealDatabase {
            return Room.databaseBuilder( app, WeeMealDatabase::class.java, "weemeal-database")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getRecipeEntityDao(database: WeeMealDatabase): RecipeEntityDao {
            return database.recipeEntityDao()
        }
    }
}

