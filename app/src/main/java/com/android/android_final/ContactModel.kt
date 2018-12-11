package com.android.android_final

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "contacts", foreignKeys = [ForeignKey(entity = ContactGroupsModel::class,
        parentColumns = ["id_group"], childColumns = ["id_group"])])
data class ContactModel(
        @PrimaryKey(autoGenerate = true)
        var id : String = "",

        var name: String = "",
        var mobile_phone_number: Int = 0,
        var home_phone_number: Int = 0,
        var work_phone_number: Int = 0,
        var profile_image : String = "",

        var id_group : String = ""

)
/*
{

    constructor(){}

    constructor(id : String, name :String, mobile_phone_number: Int , home_phone_number : Int,
                work_phone_number : Int, profile_image: String, id_group : String){
        this.id = id
        this.name = name
        this.mobile_phone_number = mobile_phone_number
        this.home_phone_number = home_phone_number
        this.work_phone_number = work_phone_number
        this.profile_image = profile_image
        this.id_group = id_group
    }

    @PrimaryKey
    var id : String = ""

    var name: String = ""
    var mobile_phone_number: Int = 0
    var home_phone_number: Int = 0
    var work_phone_number: Int = 0
    var profile_image : String = ""

    var id_group : String = ""

}*/
