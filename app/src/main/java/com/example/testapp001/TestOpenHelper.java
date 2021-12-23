package com.example.testapp001;
//package your.package.name;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.testapp001.DBContract.DBEntry;

public class TestOpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    // テーブルの内容などを変更したら、この数字を変更する
    static final private int VERSION = 2;

    // データベース名
    static final private String DBNAME = "TestDB.db";

    public TestOpenHelper(Context context) {
        super(context, DBNAME, null,VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + DBEntry.TABLE_NAME + " (" +
                        DBEntry._ID + " INTEGER PRIMARY KEY," +
                        DBEntry.COLUMN_NAME_TITLE + " TEXT," +
                        DBEntry.COLUMN_NAME_UPPER + " INTEGER," +
                        DBEntry.COLUMN_NAME_LOWER + "INTEGER, " +
                        DBEntry.COLUMN_NAME_ALARM + "alarm)");

        // トリガーを作成
        db.execSQL(
                "CREATE TRIGGER trigger_samp_tbl_update AFTER UPDATE ON " + DBEntry.TABLE_NAME +
                        " BEGIN "+
                        " UPDATE " + DBEntry.TABLE_NAME + " SET up_date = DATETIME('now', 'localtime') WHERE rowid == NEW.rowid; "+
                        " END;"

        );
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + DBEntry.TABLE_NAME);
        onCreate(db);
    }

}