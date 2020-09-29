package com.app.artoonpractival.Fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.artoonpractival.CommonUtils.Prefs;
import com.app.artoonpractival.Database.BookedSeats;
import com.app.artoonpractival.Database.DBReservedSeats;
import com.app.artoonpractival.R;
import com.app.artoonpractival.Ui.RecyclerActivity;
import com.app.artoonpractival.SeatBooking.AbstractItem;
import com.app.artoonpractival.SeatBooking.AirplaneAdapter;
import com.app.artoonpractival.SeatBooking.BookedItem;
import com.app.artoonpractival.SeatBooking.CenterItem;
import com.app.artoonpractival.SeatBooking.EmptyItem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FragmentRecycler extends Fragment {

    private RecyclerActivity recyclerActivity;
    private static final int COLUMNS = 15;
    private static final int ROWS = 7;
    //    private static final int GAP = 2;
    private static final int TOP_FULL_COL = 3;
    private static final int BOT_FULL_COL = 1;
    private static final int[] GAPS = {4, 5, 9, 10};
    private static Integer[] BOOKED;
    // private static List<String> BOOKED;
    private TextView txtSeatSelected;
    private RecyclerView recyclerView;
    private View view;
    private AirplaneAdapter adapter;
    List<Integer> countseats = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerActivity = (RecyclerActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recycler, container, false);
        /*recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);*/

        txtSeatSelected = (TextView) view.findViewById(R.id.txt_seat_selected);

        List<BookedSeats> bookedSeats = new DBReservedSeats(recyclerActivity).getallseats();
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < bookedSeats.size(); i++) {
            values.add(bookedSeats.get(i).getReservednumbers());
        }

        BOOKED = new Integer[values.size()];
        BOOKED = values.toArray(BOOKED);
        refresh();

        txtSeatSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer[] itemsArray = new Integer[countseats.size()];
                itemsArray = countseats.toArray(itemsArray);

                for (Integer s : itemsArray) {
                    new DBReservedSeats(recyclerActivity).addReservedSeats(Prefs.getString("email", ""), s, Prefs.getInt("id", 1));
                }

                countseats.clear();
               // adapter.notifyDataSetChanged();
                recyclerActivity.recreate();
            }
        });
        return view;
    }

    private void refresh() {


        List<AbstractItem> items = new ArrayList<>();
        int t_counter = 0;
        for (int i = 0; i < COLUMNS * TOP_FULL_COL; i++) {
            if (contains(BOOKED, t_counter))
                items.add(new BookedItem(String.valueOf(++t_counter)));
            else
                items.add(new CenterItem(String.valueOf(++t_counter)));

        }

        boolean empty_flag = true;
        for (int i = 0; i < ROWS * COLUMNS; i++) {
            empty_flag = false;
            for (int Gap : GAPS) {

                if (i % COLUMNS == Gap) {
                    empty_flag = true;

                }


            }
            if (empty_flag) {

                items.add(new EmptyItem(String.valueOf(i)));
            } else {

                if (contains(BOOKED, t_counter))
                    items.add(new BookedItem(String.valueOf(++t_counter)));
                else
                    items.add(new CenterItem(String.valueOf(+ ++t_counter)));
            }


        }
        for (int i = 0; i < COLUMNS * BOT_FULL_COL; i++) {
            if (contains(BOOKED, t_counter))
                items.add(new BookedItem(String.valueOf(++t_counter)));
            else
                items.add(new CenterItem(String.valueOf(+ ++t_counter)));

        }


        GridLayoutManager manager = new GridLayoutManager(recyclerActivity, COLUMNS);
        recyclerView = (RecyclerView) view.findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        adapter = new AirplaneAdapter(recyclerActivity, items, onSeatSelected);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    public boolean contains(final Integer[] array, final int key) {
        Arrays.sort(array);
        return Arrays.binarySearch(array, key) >= 0;
    }


    private AirplaneAdapter.OnSeatSelected onSeatSelected = new AirplaneAdapter.OnSeatSelected() {
        @Override
        public void onSeatSelected(int count, String lbl, Boolean selected) {


            if (selected) {

                countseats.add(Integer.parseInt(lbl)+-1);
            }

            Toast.makeText(recyclerActivity, lbl, Toast.LENGTH_SHORT).show();
            txtSeatSelected.setText("Book " + count + " seats");

        }
    };



}

