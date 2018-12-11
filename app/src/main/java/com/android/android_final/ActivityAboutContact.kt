package com.android.android_final

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ActivityAboutContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_contact)

        val button_back = findViewById<ImageButton>(R.id.button_back)
        val textview_name = findViewById<TextView>(R.id.textview_name2)
        val textview_work = findViewById<TextView>(R.id.textview_work2)
        val textview_home = findViewById<TextView>(R.id.textview_home2)
        val textview_mobile = findViewById<TextView>(R.id.textview_mobile2)
        val textview_group = findViewById<TextView>(R.id.textview_group2)
        val image = findViewById<ImageView>(R.id.image_activity2)
        val choosed_id = intent.getStringExtra("choosed_contact")

        val database = DBHelper(this)
        val db = database.writableDatabase

        val contact : ContactModel = database.parceDBtoListcontacts2(choosed_id)

        textview_name.text = contact.name
        textview_work.text = contact.work_phone_number.toString()
        textview_home.text = contact.home_phone_number.toString()
        textview_mobile.text = contact.mobile_phone_number.toString()

        var string = database.getgroupname(choosed_id)
        textview_group.text = string

        when(contact.profile_image){
            "1" ->  image.setImageResource(R.drawable.category1)
            "2" ->  image.setImageResource(R.drawable.category2)
            "3" ->  image.setImageResource(R.drawable.category3)
        }

        button_back.setOnClickListener{
            val intent = Intent(this@ActivityAboutContact, MainActivity::class.java)
            startActivity(intent)
        }



    }
}
