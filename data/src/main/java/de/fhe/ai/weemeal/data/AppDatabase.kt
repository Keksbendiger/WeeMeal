package de.fhe.ai.weemeal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userEntityDao(): UserEntityDao

    companion object {
        fun getDatabase(app: Context): AppDatabase {
            return Room.databaseBuilder( app, AppDatabase::class.java, "app-db")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getUserEntityDao(database: AppDatabase): UserEntityDao {
            return database.userEntityDao()
        }
    }
}

