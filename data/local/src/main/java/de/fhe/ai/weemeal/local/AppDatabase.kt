package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.fhe.ai.weemeal.local.recipe.RecipeEntity
import de.fhe.ai.weemeal.local.recipe.RecipeEntityDao

@Database(entities = [RecipeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeEntityDao(): RecipeEntityDao

    companion object {
        private fun getDatabase(app: Context): AppDatabase {
            return Room.databaseBuilder(app, AppDatabase::class.java, "app-db")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getRecipeEntityDao(app: Context): RecipeEntityDao {
            return getDatabase(app).recipeEntityDao()
        }
    }
}
