package com.android.android_final

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

interface GroupsDAO {
    @Query("SELECT * from groups")
    fun getAll(): List<ContactModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(group: ContactGroupsModel)

    @Query("DELETE from groups")
    fun deleteAll()
}