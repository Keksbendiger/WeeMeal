package de.darthkali.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.darthkali.local.recipe.RecipeEntityDao
import de.darthkali.local.recipe.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeEntityDao(): RecipeEntityDao

    companion object {
        private fun getDatabase(app: Context): AppDatabase {
            return Room.databaseBuilder( app, AppDatabase::class.java, "app-db")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getRecipeEntityDao(app: Context): RecipeEntityDao {
            return getDatabase(app).recipeEntityDao()
        }
    }
}