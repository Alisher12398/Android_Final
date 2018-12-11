package com.android.android_final

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        val DATABASE_NAME = "10"
        val TABLE_NAME_1 = "products"
        val TABLE_NAME_2 = "cart"
        val TABLE_NAME_3 = "category"
        val TABLE_NAME_4 = "foods"
        val TABLE_NAME_5 = "recipes"

        val table_contacts= "contacts"
        val table_contacts_id_contact = "id_contact"
        val table_contacts_name = "name"
        val table_contacts_mobile_phone_number = "mobile_phone_number"
        val table_contacts_home_phone_number = "home_phone_number"
        val table_contacts_work_phone_number = "work_phone_number"
        val table_contacts_profile_image = "profile_image"
        val table_contacts_id_group = "id_group"

        val table_groups= "groups"
        val table_groups_id_group = "id_groups"
        val table_groups_name = "name"

        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql_create_table_contacts = (
                "CREATE TABLE " +
                        table_contacts + "(" +
                        table_contacts_id_contact + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        table_contacts_name + " VARCHAR, " +
                        table_contacts_mobile_phone_number + " INTEGER, " +
                        table_contacts_home_phone_number + " INTEGER, " +
                        table_contacts_work_phone_number + " INTEGER, " +
                        table_contacts_profile_image + " VARCHAR, " +
                        table_contacts_id_group + " VARCHAR " +
                        //" FOREIGN KEY ("+table_contacts_id_group+") REFERENCES "+ table_groups+"("+table_groups_id_group+") ON UPDATE CASCADE " +
                        ");"
                )
        db.execSQL(sql_create_table_contacts)


        val sql_create_table_groups = (
                "CREATE TABLE " +
                        table_groups + "(" +
                        table_groups_id_group + " VARCHAR PRIMARY KEY NOT NULL, " +
                        table_groups_name + " VARCHAR " +
                        ");"
                )
        db.execSQL(sql_create_table_groups)

    }


    fun parceDBtoListcontacts(): List<ContactModel> {
        val categoryList = ArrayList<ContactModel>()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM contacts"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val contact = ContactModel()
                    contact.id_contact = cursor.getString(cursor.getColumnIndex("id_contact"))
                    contact.name = cursor.getString(cursor.getColumnIndex("name"))
                    contact.mobile_phone_number = cursor.getInt(cursor.getColumnIndex("mobile_phone_number"))
                    contact.home_phone_number = cursor.getInt(cursor.getColumnIndex("home_phone_number"))
                    contact.work_phone_number = cursor.getInt(cursor.getColumnIndex("work_phone_number"))
                    contact.profile_image = cursor.getString(cursor.getColumnIndex("profile_image"))
                    contact.id_group = cursor.getString(cursor.getColumnIndex("id_group"))
                    categoryList.add(contact)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return categoryList
    }

   /* fun deletefromtable(name : String){
        val db = writableDatabase
        val sql = "DELETE FROM $name;"
        db.execSQL(sql)
    }*/

    /*fun getcategotyid(name : String) : Int{
        val db = writableDatabase
        val sql = "SELECT * FROM category WHERE name LIKE '$name';"
        val c : Cursor = db.rawQuery(sql, null)
        c.moveToFirst()
        var s : Int = 2

        s = c.getInt(c.getColumnIndex("id_category"))
        return s
    }

    fun checkestlivproducts(id : String) : Boolean{
        val db = writableDatabase
        val sql = "SELECT id_product FROM products WHERE id_product LIKE '$id';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }

    fun replacenameinproducts(id : String, name : String){
        val db = writableDatabase
        val sql = "UPDATE products SET name = '$name' WHERE id_product = '$id';"
        db.execSQL(sql)
    }

    fun checkproductidsindb(list : ArrayList<String>){
        val db = writableDatabase
        val sql = "SELECT id_product FROM products;"
        val cursor : Cursor = db.rawQuery(sql, null)

        val current_ids_in_db = ArrayList<String>()
        current_ids_in_db.clear()
        if (cursor.moveToFirst()) {
            do {
                current_ids_in_db.add(cursor.getString(cursor.getColumnIndex("id_product")))
            } while (cursor.moveToNext())
        }
        cursor.close()

        for (a in current_ids_in_db){
            if (!list.contains(a)){
                val sql = "DELETE FROM products WHERE id_product = '$a';"
                db.execSQL(sql)
            }
        }
    }



    fun checkestlivcategory(id : String) : Boolean{
        val db = writableDatabase
        val sql = "SELECT id_category FROM category WHERE id_category LIKE '$id';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }

    fun replacenameincategory(id : String, name : String){
        val db = writableDatabase
        val sql = "UPDATE category SET name = '$name' WHERE id_category = '$id';"
        db.execSQL(sql)
    }

    fun checkcategoryidsindb(list : ArrayList<String>){
        val db = writableDatabase
        val sql = "SELECT id_category FROM category;"
        val cursor : Cursor = db.rawQuery(sql, null)

        val current_ids_in_db = ArrayList<String>()
        current_ids_in_db.clear()
        if (cursor.moveToFirst()) {
            do {
                current_ids_in_db.add(cursor.getString(cursor.getColumnIndex("id_category")))
            } while (cursor.moveToNext())
        }
        cursor.close()

        for (a in current_ids_in_db){
            if (!list.contains(a)){
                val sql = "DELETE FROM category WHERE id_category = '$a';"
                db.execSQL(sql)
            }
        }
    }







    fun checkestlivfoods(id : String) : Boolean{
        val db = writableDatabase
        val sql = "SELECT id_food FROM foods WHERE id_food LIKE '$id';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }

    fun replacenameinfoods(id: String, category: String, name: String, description : String){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("id_category", category)
        cv.put("name", name)
        cv.put("description", description)
        //db.update("foods", cv, "id_food = ?", null )
    }

    fun checkfoodsidsindb(list : ArrayList<String>){
        val db = writableDatabase
        val sql = "SELECT id_category FROM category;"
        val cursor : Cursor = db.rawQuery(sql, null)

        val current_ids_in_db = ArrayList<String>()
        current_ids_in_db.clear()
        if (cursor.moveToFirst()) {
            do {
                current_ids_in_db.add(cursor.getString(cursor.getColumnIndex("id_category")))
            } while (cursor.moveToNext())
        }
        cursor.close()

        for (a in current_ids_in_db){
            if (!list.contains(a)){
                val sql = "DELETE FROM category WHERE id_category = '$a';"
                db.execSQL(sql)
            }
        }
    }


    fun parceDBtoListfoods(category : String): List<Food_model2> {
        val foodList = ArrayList<Food_model2>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM foods WHERE id_category='$category'"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val food = Food_model2()
                    food.id_food = cursor.getString(cursor.getColumnIndex("id_food"))
                    food.category = cursor.getString(cursor.getColumnIndex("id_category"))
                    food.name = cursor.getString(cursor.getColumnIndex("name"))
                    food.description = cursor.getString(cursor.getColumnIndex("description"))
                    foodList.add(food)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return foodList
    }


    fun parceDBtoListfavorites(uid: String): List<Food_model2> {
        val foodList = ArrayList<Food_model2>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM favorites WHERE id_user = '$uid';"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val food = Food_model2()
                    food.id_food = cursor.getString(cursor.getColumnIndex("id_food"))
                    food.category = cursor.getString(cursor.getColumnIndex("id_user"))

                    val selectQuery2 = "SELECT name FROM foods WHERE id_food = '${cursor.getString(cursor.getColumnIndex("id_food"))}';"
                    val cursor2 = db.rawQuery(selectQuery2, null)
                    cursor2.moveToFirst()

                    food.name=cursor2.getString(cursor2.getColumnIndex("name"))
                    foodList.add(food)
                } while (cursor.moveToNext())
            }
        }

        cursor.close()

        return foodList
    }



    fun parceDBtoListcategory(): List<Category_model> {
        val categoryList = ArrayList<Category_model>()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM category"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val food = Category_model()
                    food.id_category = cursor.getString(cursor.getColumnIndex("id_category"))
                    food.name = cursor.getString(cursor.getColumnIndex("name"))
                    categoryList.add(food)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return categoryList
    }

    fun parceDBtoListfood3(id : String): Food_model2 {

        val db = writableDatabase
        val selectQuery = "SELECT * FROM foods WHERE id_food='$id'"
        val cursor = db.rawQuery(selectQuery, null)

        cursor.moveToFirst()

        val id_food = cursor.getString(cursor.getColumnIndex("id_food"))
        val category = cursor.getString(cursor.getColumnIndex("id_category"))
        val name = cursor.getString(cursor.getColumnIndex("name"))
        val description = cursor.getString(cursor.getColumnIndex("description"))
        val food = Food_model2(id_food, category, name, description)

        cursor.close()
        return food
    }







    fun checkestlivfavorites(id_food : String, id_user : String) : Boolean{
        val db = writableDatabase
        val sql = "SELECT id_food FROM favorites WHERE id_food = '$id_food' AND id_user = '$id_user';"
        val c : Cursor = db.rawQuery(sql, null)

        if (c.count==1) return true
        if (c.count==0) return false
        return false
    }
*//*
    fun replacenameinfavorites(id_food : String, id_user : String){
        val db = writableDatabase
        val sql = "UPDATE category SET name = '$name' WHERE id_category = '$id';"
        db.execSQL(sql)
    }
    *//*


    //upgrading the database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS Persons"
        db.execSQL(sql)
        onCreate(db)
    }*/

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS Persons"
        db.execSQL(sql)
        onCreate(db)
    }
}