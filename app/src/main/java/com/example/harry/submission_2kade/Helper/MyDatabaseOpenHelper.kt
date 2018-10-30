package com.example.harry.submission_2kade.Helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.harry.submission_2kade.Model.EventDB
import com.example.harry.submission_2kade.Model.EventsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_match_detail.*
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Schedule.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(EventDB.TABLE_FAVORITE, true,
                EventDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                EventDB.DATE_EVENT to TEXT,
                EventDB.HOME_TEAM to TEXT,
                EventDB.AWAY_TEAM to TEXT,
                EventDB.HOME_SCORE to TEXT,
                EventDB.AWAY_SCORE to TEXT,
                EventDB.EVENT_ID to TEXT

        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(EventDB.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)