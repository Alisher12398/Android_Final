package com.android.android_final

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActivityAddContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val button_addcontact = findViewById<Button>(R.id.button_addcontact)
        val textview_name = findViewById<EditText>(R.id.textview_name)
        val textview_work = findViewById<EditText>(R.id.textview_work)
        val textview_home = findViewById<EditText>(R.id.textview_home)
        val textview_mobile = findViewById<EditText>(R.id.textview_mobile)
        val textview_image = findViewById<EditText>(R.id.textview_image)
        val textview_group = findViewById<EditText>(R.id.textview_group)

        val database = DBHelper(this)
        val db = database.writableDatabase

        button_addcontact.setOnClickListener {

            if (textview_name.length() > 0 && textview_image.length() > 0) {
                val cv = ContentValues()

                cv.put("name", textview_name.text.toString())
                cv.put("work_phone_number", textview_work.text.toString())
                cv.put("home_phone_number", textview_home.text.toString())
                cv.put("mobile_phone_number", textview_mobile.text.toString())
                cv.put("profile_image", textview_image.text.toString())
                cv.put("id_group", textview_group.text.toString())


                /* when(image_addnews.text.toString()){
                    "football" -> {
                        cv.put("image_url", R.drawable.footbal)
                        cv.put("image_url_string", "football")
                    }
                    "amazon" -> {
                        cv.put("image_url", R.drawable.news1)
                        cv.put("image_url_string", "amazon")
                    }
                    "uber" -> {
                        cv.put("image_url", R.drawable.uber)
                        cv.put("image_url_string", "uber")
                    }
                    "wifi" -> {
                        cv.put("image_url", R.drawable.wifi)
                        cv.put("image_url_string", "wifi")
                    }
                }*/

                db.insert("contacts", null, cv)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }

        }
    }
}
