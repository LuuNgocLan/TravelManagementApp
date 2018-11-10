package luungoclan.min.traveltourmanagement.adapters.tourAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ItemClickListener;
import luungoclan.min.traveltourmanagement.models.detailTour.DataTour;
import luungoclan.min.traveltourmanagement.views.detailTour.DetailTourActivity;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.MyViewHolder> {
    private List<DataTour> tourList;
    private Context context;

    public TourAdapter(List<DataTour> tourList, Context context) {
        this.tourList = tourList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour_vertical, parent, false);
        return new TourAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DataTour detail = tourList.get(position);
        holder.tvSlotRemain.setText(detail.getSlot()+"");
        holder.tvItemPrice.setText(detail.getPriceAdults() +"");
        holder.tvDayDeparture.setText(detail.getDateDepart());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Detail!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, DetailTourActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imvTour;
        TextView tvNameTour, tvSlotRemain, tvDayDeparture, tvItemPrice;
        Button btnDetail;
        RatingBar rbRating;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            imvTour = itemView.findViewById(R.id.imvTour);
            tvNameTour = itemView.findViewById(R.id.tvNameTour);
            rbRating = itemView.findViewById(R.id.rbRating);
            tvDayDeparture = itemView.findViewById(R.id.tvTimeDepart);
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
