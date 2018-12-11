package com.android.android_final

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

interface ContactDAO {
    @Query("SELECT * from contacts")
    fun getAll(): List<ContactModel>

    @Insert(onConflict = REPLACE)
    fun insert(contact: ContactModel)

    @Query("DELETE from contacts")
    fun deleteAll()

}