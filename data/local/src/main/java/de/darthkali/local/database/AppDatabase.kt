package de.darthkali.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.darthkali.local.database.recipe.RecipeEntityDao
import de.darthkali.local.database.recipe.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userEntityDao(): RecipeEntityDao

    companion object {
        private fun getDatabase(app: Context): AppDatabase {
            return Room.databaseBuilder( app, AppDatabase::class.java, "app-db")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getUserEntityDao(app: Context): RecipeEntityDao {
            return getDatabase(app).userEntityDao()
        }
    }
}