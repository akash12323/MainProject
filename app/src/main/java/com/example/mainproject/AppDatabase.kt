package com.example.mainproject

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mainproject.model.Wishes
import com.example.mainproject.model.Wishesmovies

@Database(entities = [Wishes::class,Wishesmovies::class],version = 4)
abstract class AppDatabase :RoomDatabase(){
    abstract fun wishesdao():WishesDao
}