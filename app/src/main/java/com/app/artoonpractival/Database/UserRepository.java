package com.app.artoonpractival.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {


    private String DB_NAME = "db_task";

    /*static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'user' ADD COLUMN 'last_name' TEXT");

        }
    };


    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'user' ADD COLUMN 'mobile' TEXT");

        }
    };*/


    private userDB noteDatabase;

    public UserRepository(Context context) {

        noteDatabase = Room.databaseBuilder(context, userDB.class, DB_NAME).build();
    }

   /* public void insertTask(String title,
                           String description) {

        //insertTask(title,description);
    }*/

    public void insertTask(int reservednumbers, String email, int userid) {

        BookedSeats note = new BookedSeats();
        note.setReservednumbers(reservednumbers);
        note.setEmail(email);
        note.setUserId(userid);


        insertTask(note);
    }

    private void insertTask(final BookedSeats note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccessUser().insertTask(note);

                return null;
            }
        }.execute();
    }

   /* public void updateTask(final user note) {
        // note.setModifiedAt(AppUtils.getCurrentDateTime());

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccessUser().updateTask(note);
                return null;
            }
        }.execute();
    }

    public void deleteTask(final int id) {
        final LiveData<user> task = getTask(id);
        if (task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    noteDatabase.daoAccessUser().deleteTask(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public void deleteTask(final user note) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                noteDatabase.daoAccessUser().deleteTask(note);
                return null;
            }
        }.execute();
    }*/

  /*  public LiveData<BookedSeats> getTask(int id) {
        return noteDatabase.daoAccessUser().getTask(id);
    }*/

  /*  public LiveData<List<BookedSeats>> getTasks() {

        return noteDatabase.daoAccessUser().fetchAllTasks();
    }
*/
  public List<BookedSeats> getAllUsers() throws ExecutionException, InterruptedException {
      return new GetUsersAsyncTask().execute().get();
  }


    private class GetUsersAsyncTask extends AsyncTask<Void, Void, List<BookedSeats>>
    {
        @Override
        protected List<BookedSeats> doInBackground(Void... url) {
            return  noteDatabase.daoAccessUser().fetchAllTasks();
        }
    }
}
