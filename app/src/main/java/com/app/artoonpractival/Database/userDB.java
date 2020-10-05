package com.app.artoonpractival.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;



@Database(entities = {BookedSeats.class}, version = 1, exportSchema = false)
public abstract class userDB extends RoomDatabase {
    public abstract DaoAccessUser daoAccessUser();

}
