package com.prodigyinfotech.tasktwo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoModel::class] , version = 1)
abstract class TodoDatabase : RoomDatabase(){

    abstract val dao: TodoDao

    companion object{

        private var database: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            if(database == null){
                database = Room.databaseBuilder(context, TodoDatabase::class.java,"Todo Database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database!!
        }

    }
}