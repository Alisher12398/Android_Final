package com.android.android_final

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "groups")
class ContactGroupsModel(
        @PrimaryKey(autoGenerate = true)
        var id_group : String = "",

        var group_name : String = ""

)