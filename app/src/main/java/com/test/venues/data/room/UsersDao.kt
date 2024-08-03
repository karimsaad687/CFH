package com.test.venues.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface UsersDao {
    @Query("SELECT * FROM Users")
    fun getAll(): List<User>

    @Query("SELECT * FROM Users WHERE email LIKE :email and password LIKE :password")
    fun findByEmailPassword(email: String, password: String): User?

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)
}