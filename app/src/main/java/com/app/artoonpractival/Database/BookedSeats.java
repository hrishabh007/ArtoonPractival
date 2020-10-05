package com.app.artoonpractival.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class BookedSeats implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;

    private int reservednumbers;

    private String email;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReservednumbers() {
        return reservednumbers;
    }

    public void setReservednumbers(int reservednumbers) {
        this.reservednumbers = reservednumbers;
    }
}
