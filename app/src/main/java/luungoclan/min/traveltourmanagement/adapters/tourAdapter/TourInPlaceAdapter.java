package luungoclan.min.traveltourmanagement.adapters.tourAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ItemClickListener;
import luungoclan.min.traveltourmanagement.models.tourList.Tour;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.views.detailTour.DetailTourActivity;

public class TourInPlaceAdapter extends RecyclerView.Adapter<TourInPlaceAdapter.MyViewHolder> {
    private List<Tour> tourList;
    private Context context;
    private int lastPosition = -1;

    public TourInPlaceAdapter(List<Tour> tourList, Context context) {
        this.tourList = tourList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour_vertical_1, parent, false);
        return new TourInPlaceAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Tour tour = tourList.get(position);
        holder.tvNameTour.setText(tour.getName());
        holder.tvSlotRemain.setText((tour.getSlot() - tour.getBooked()) + "");
        holder.tvItemPrice.setText(tour.getPriceAdults() + " VNĐ");
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Toast.makeText(context, "Detail!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailTourActivity.class);
                intent.putExtra(Common.ID_TOUR, tour.getId());
                context.startActivity(intent);
            }
        });
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, DetailTourActivity.class);
                intent.putExtra(Common.ID_TOUR, tour.getId());
                context.startActivity(intent);
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
    public int getItemCount() {
        return tourList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imvTour;
        TextView tvNameTour, tvSlotRemain, tvItemPrice;
        Button btnDetail;
        RatingBar rbRating;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            imvTour = itemView.findViewById(R.id.imvTour);
            tvNameTour = itemView.findViewById(R.id.tvNameTour);
            rbRating = itemView.findViewById(R.id.rbRating);
            tvSlotRemain = itemView.findViewById(R.id.tvRemainSlots);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            btnDetail = itemView.findViewById(R.id.btnDetail);
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
