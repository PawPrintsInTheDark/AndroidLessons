package com.example.androidlessons

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context, factory: SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "PRODUCT_DATABASE"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "product_table"
        val KEY_ID = "id"
        val KEY_NAME = "name"
        val KEY_WEIGHT = "weight"
        val KEY_COST = "cost"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_WEIGHT + " TEXT, " +
                KEY_COST + " TEXT" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addProduct(product: Product) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, product.name)
        values.put(KEY_WEIGHT, product.weight)
        values.put(KEY_COST, product.cost)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun readProducts(): MutableList<Product> {
        val productList = mutableListOf<Product>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e:SQLException){
            db.execSQL(selectQuery)
            return productList
        }
        var productId : Int
        var productName : String
        var productWeight : String
        var productCost : String
        if (cursor.moveToFirst()) {
            do {
                cursor.moveToFirst()
                productId = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID))
                productName = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
                productWeight = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_WEIGHT))
                productCost = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_COST))
                val product = Product(productId ,productName, productWeight, productCost)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productList
    }

    fun removeALL() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }

    fun removeProduct(product: Product) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "id=" + product.id, null)
        db.close()
    }

    fun updateProduct(product: Product) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, product.id)
        values.put(KEY_NAME, product.name)
        values.put(KEY_WEIGHT, product.weight)
        values.put(KEY_COST, product.cost)
        db.update(TABLE_NAME, values, "id=" + product.id, null)
        db.close()
    }

}