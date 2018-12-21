package luungoclan.min.traveltourmanagement.views.myBooking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.bookingAdapter.BookingAdapter;
import luungoclan.min.traveltourmanagement.models.booking.Booking;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookingFragment extends Fragment {

    @BindView(R.id.rv_mybooking)
    RecyclerView rvMyBooking;
    @BindView(R.id.tv_msg_no_booking)
    TextView tvMsgNoBooking;

    private BookingAdapter adapter;
    private List<Booking> bookingList = new ArrayList<>();

    public MyBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_booking, container, false);
        ButterKnife.bind(this, view);
        rvMyBooking.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    public void dataBooking(List<Booking> bookingList) {
        if (bookingList.size() > 0) {
            this.bookingList = bookingList;
            adapter = new BookingAdapter(getContext(), bookingList);
            rvMyBooking.setAdapter(adapter);
            tvMsgNoBooking.setVisibility(View.GONE);
        } else {
            rvMyBooking.setVisibility(View.GONE);
            tvMsgNoBooking.setVisibility(View.VISIBLE);
        }
    }
}
