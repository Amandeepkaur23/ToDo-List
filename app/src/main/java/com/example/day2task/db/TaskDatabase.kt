package com.example.day2task.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.day2task.model.TaskDetail

@Database(entities = [TaskDetail::class], version = 2)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).addMigrations(Migration1to2())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class Migration1to2: Migration(1, 2){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE TaskDb ADD COLUMN isCompleted INTEGER NOT NULL DEFAULT 0 ")
    }
}

//if you add another migration we have to make another class and add it into a addMigration()