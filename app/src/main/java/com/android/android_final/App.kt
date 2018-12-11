package com.android.android_final

import android.app.Application
import android.arch.persistence.room.Room



class App : Application() {

   /* //var instance: App

    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .build()
    }

    fun getInstance(): App {
        return instance
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

*/
}