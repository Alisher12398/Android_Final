package com.android.android_final

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {



    fun inserttoDBgroups(){

        val database = DBHelper(this)
        val db = database.writableDatabase

        val cv = ContentValues()
        cv.put("id_groups", "1")
        cv.put("priority", "3")
        cv.put("name", "Университет")
        db.insert("groups", null, cv)

        val cv2 = ContentValues()
        cv2.put("id_groups", "2")
        cv.put("priority", "2")
        cv2.put("name", "Родственники")
        db.insert("groups", null, cv2)

        val cv3 = ContentValues()
        cv3.put("id_groups", "3")
        cv.put("priority", "1")
        cv3.put("name", "Семья")
        db.insert("groups", null, cv3)

        val cv4 = ContentValues()
        cv4.put("id_groups", "4")
        cv.put("priority", "4")
        cv4.put("name", "Офис")
        db.insert("groups", null, cv4)

    }

    fun checkestlivgroups(id : String) : Boolean{

        val database = DBHelper(this)
        val db = database.writableDatabase

        val sql = "SELECT id_groups FROM groups WHERE id_groups LIKE '$id';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }

    fun inserttoDBcontacts(){

        val database = DBHelper(this)
        val db = database.writableDatabase

        val cv = ContentValues()
        cv.put("id_contact", "1")
        cv.put("name", "Alisher")
        cv.put("mobile_phone_number", "87026285668")
        cv.put("home_phone_number", "87026285668")
        cv.put("work_phone_number", "87026285668")
        cv.put("profile_image", "1")
        cv.put("id_group", "1")

        db.insert("contacts", null, cv)

        val cv2 = ContentValues()
        cv2.put("id_contact", "2")
        cv2.put("name", "User2")
        cv2.put("mobile_phone_number", "87021232546")
        cv2.put("home_phone_number", "87021232546")
        cv2.put("work_phone_number", "87021232546")
        cv2.put("profile_image", "2")
        cv2.put("id_group", "2")

        db.insert("contacts", null, cv2)

    }

    fun checkestlivcontacts(id : String) : Boolean{

        val database = DBHelper(this)
        val db = database.writableDatabase

        val sql = "SELECT id_contact FROM contacts WHERE id_contact LIKE '$id';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.toolbar_icon_add){
            val intent = Intent(this, ActivityAddContact::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = DBHelper(this)
        val db = database.writableDatabase
        /*val db = AppDatabase.getAppDataBase(context = this)
        val contactDAO = db?.contactDAO()
        val groupsDAO = db?.groupsDAO()

        val testgroup= ContactGroupsModel(group_name =  "testgroup")
        groupsDAO?.insert(testgroup)*/
        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        val floating : FloatingActionButton = findViewById(R.id.floating_button_add)
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.toolbar_menu)


        if (!checkestlivgroups("1")){
            inserttoDBgroups()
        }

        if (!checkestlivcontacts("1")){
            inserttoDBcontacts()
        }



        val recycler : RecyclerView = findViewById(R.id.recycleview_main_activity)

        val categoryList = database.parceDBtoListcontacts()

        val adapter = AdapterMainActivityRecycle(categoryList)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        floating.setOnClickListener{
            val intent = Intent(this, ActivityAddContact::class.java)
            startActivity(intent)
        }

        recycler.addOnItemTouchListener(RecyclerTouchListener(this, recycler, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                val id = adapter.getId(position)
                val intent = Intent(this@MainActivity, ActivityAboutContact::class.java)
                intent.putExtra("choosed_contact", id)
                startActivity(intent)

            }

            override fun onLongClick(view: View?, position: Int) {
                Toast.makeText(this@MainActivity, "LongPress : $position", Toast.LENGTH_SHORT).show()
            }
        }))



    }

}
