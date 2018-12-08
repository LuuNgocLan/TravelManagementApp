package luungoclan.min.traveltourmanagement.adapters.placeAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ItemClickListener;
import luungoclan.min.traveltourmanagement.models.booking.Booking;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.SimpleViewHolder> {
    private Context context;
    private List<Booking> bookingList;
    private int lastPosition = -1;


    public BookingAdapter(Context context, List<Booking> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_booking, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int adapterPosition, boolean b) {

            }
        });
        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener itemClickListener;

        public SimpleViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
