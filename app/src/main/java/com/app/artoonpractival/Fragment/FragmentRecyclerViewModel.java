package com.app.artoonpractival.Fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.artoonpractival.Database.BookedSeats;
import com.app.artoonpractival.Database.UserRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FragmentRecyclerViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private List<BookedSeats> bookedseats;

    public FragmentRecyclerViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
    }

    public List<BookedSeats> getAllbookedseats() throws ExecutionException, InterruptedException {

        bookedseats = userRepository.getAllUsers();
        return bookedseats;
    }

    public void addseats(BookedSeats book){
        userRepository.insertTask(book.getReservednumbers(),book.getEmail(),book.getUserId());
    }

}
