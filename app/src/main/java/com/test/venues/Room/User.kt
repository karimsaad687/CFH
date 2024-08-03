package com.test.venues.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true) val id:Int?=null,
    @ColumnInfo(name = "first") var first: String,
    @ColumnInfo(name = "last") var last: String,
    @ColumnInfo(name = "age") var age: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String
)