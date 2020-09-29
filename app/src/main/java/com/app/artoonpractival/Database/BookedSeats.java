package com.app.artoonpractival.Database;

public class BookedSeats {
    private int userId;
    private int reservednumbers;
    private String email;

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
