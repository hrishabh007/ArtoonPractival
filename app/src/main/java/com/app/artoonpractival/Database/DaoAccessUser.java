package com.app.artoonpractival.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoAccessUser {
    @Insert
    Long insertTask(BookedSeats note);


    @Query("SELECT * FROM BookedSeats")
    List<BookedSeats> fetchAllTasks();


    /*@Query("SELECT * FROM user WHERE id =:taskId")
    LiveData<user> getTask(int taskId);*/


}
