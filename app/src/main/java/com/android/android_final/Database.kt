package com.android.android_final

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database


@Database(entities = arrayOf(ContactModel::class, ContactGroupsModel::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDAO(): ContactDAO
    abstract fun groupsDAO(): GroupsDAO
}