package com.app.artoonpractival.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

public class DBReservedSeats {

    public static final String TABLE_NAME = "booking";

    static final String USERID = "userID";
    static final String RESERVESEATS = "reservedSeat";
    static final String EMAIL = "email";



    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            " (" + USERID + " INTEGER  , "
            + RESERVESEATS + " INTEGER ''  , "
            + EMAIL + " TEXT DEFAULT '' )";

    private Context mContext;
    private SQLiteDatabase db;

    public DBReservedSeats(Context context) {
        mContext = context;
        db = DBHelper.getReadableDatabase(context);

    }


    public long addReservedSeats(String email, int Reserveseats, int userid) {
        try {


            ContentValues cv = new ContentValues();

            cv.put(USERID, userid);
            cv.put(RESERVESEATS, Reserveseats);
            cv.put(EMAIL, email);


            return db.insert(TABLE_NAME, null, cv);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }





    public List<BookedSeats> getallseats() {
        List<BookedSeats> eventLists = new ArrayList<>();
        try {

            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

            while (c.moveToNext()) {

                try {
                    BookedSeats p = new BookedSeats();

                    p.setUserId(Integer.valueOf(c.getString(c.getColumnIndex(USERID))));
                    p.setEmail(c.getString(c.getColumnIndex(EMAIL)));
                    p.setReservednumbers(Integer.parseInt(c.getString(c.getColumnIndex(RESERVESEATS))));

                    eventLists.add(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventLists;

    }

}
