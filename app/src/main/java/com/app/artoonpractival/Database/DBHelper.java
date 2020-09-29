package com.app.artoonpractival.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = '1';
    public static final String DB_NAME = "Booking";


    private static volatile DBHelper Instance = null;


    public static DBHelper getInstance(Context context) {
        DBHelper localInstance = Instance;
        if (localInstance == null) {
            synchronized (DBHelper.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new DBHelper(context);

                }
            }
        }
        return localInstance;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DBReservedSeats.CREATE_TABLE);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //UPGRADE TB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//       db.execSQL("DROP TABLE IF EXISTS"+Contants.TB_NAME);

    }


    public static SQLiteDatabase getReadableDatabase(Context context) {
        SQLiteDatabase db = getInstance(context).getReadableDatabase();
        return db;
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        SQLiteDatabase db = getInstance(context).getWritableDatabase();
        return db;
    }

    public static void deleteAllData(Context context) {
        try {
            SQLiteDatabase db = getInstance(context).getReadableDatabase();
            db.execSQL(DBReservedSeats.CREATE_TABLE);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
