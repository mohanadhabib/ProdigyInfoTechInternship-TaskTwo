package com.prodigyinfotech.tasktwo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int = 0,

    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "description")
    val description:String,

    @ColumnInfo(name = "isCompleted")
    var isCompleted:Boolean

)
