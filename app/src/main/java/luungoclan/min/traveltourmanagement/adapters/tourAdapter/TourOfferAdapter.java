package luungoclan.min.traveltourmanagement.adapters.tourAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ItemClickListener;
import luungoclan.min.traveltourmanagement.models.fakes.TourOffer;
import luungoclan.min.traveltourmanagement.views.detailTour.DetailTourActivity;

public class TourOfferAdapter extends RecyclerView.Adapter<TourOfferAdapter.MyViewHolder> {
    private List<TourOffer> tourOfferList;
    private Context context;

    public TourOfferAdapter(List<TourOffer> tourOfferList, Context context) {
        this.tourOfferList = tourOfferList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour,parent,false);
        return new TourOfferAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final TourOffer tourOffer = tourOfferList.get(position);
        holder.tvTitleOffer.setText(tourOffer.getTitleTour());
        holder.btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Explore!",Toast.LENGTH_SHORT).show();
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
        return tourOfferList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imvOffer;
        TextView tvTitleOffer;
        Button btnExplore;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            imvOffer = itemView.findViewById(R.id.imv_tourOffer);
            tvTitleOffer = itemView.findViewById(R.id.tv_titleOffer);
            btnExplore = itemView.findViewById(R.id.btn_explore);
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
