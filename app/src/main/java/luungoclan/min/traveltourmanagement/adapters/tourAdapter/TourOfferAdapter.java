package luungoclan.min.traveltourmanagement.adapters.tourAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ItemClickListener;
import luungoclan.min.traveltourmanagement.models.tourList.Tour;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.utils.Utils;
import luungoclan.min.traveltourmanagement.views.detailTour.DetailTourActivity;

public class TourOfferAdapter extends RecyclerView.Adapter<TourOfferAdapter.MyViewHolder> {
    private List<Tour> tourOfferList;
    private Context context;
    int[] images = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
            R.drawable.img_4, R.drawable.img_5, R.drawable.img_6,
            R.drawable.img_7, R.drawable.img_8, R.drawable.img_9};

    public TourOfferAdapter(List<Tour> tourOfferList, Context context) {
        this.tourOfferList = tourOfferList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour, parent, false);
        return new TourOfferAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Tour tourOffer = tourOfferList.get(position);
        Random rand = new Random();
        int num = rand.nextInt(9);
        Glide.with(context).
                load(images[num]).
                into(holder.imvOffer);

        holder.tvTitleOffer.setText(Utils.cuttingString(tourOffer.getName()));
        if (tourOffer.getDiscount() < 2) {
            holder.llInfor.setBackground(context.getResources().getDrawable(R.drawable.bg_label_basic));
            holder.tvTourDiscount.setVisibility(View.GONE);
        } else {
            holder.tvTourDiscount.setText("-" + tourOffer.getDiscount() + "%");
        }
        holder.tvTourCost.setText(tourOffer.getPriceAdults() + "VNĐ");
        holder.tvTourSlot.setText("Remain " + (tourOffer.getSlot() - tourOffer.getBooked()) + " slots");
        holder.btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Explore!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, DetailTourActivity.class);
                intent.putExtra(Common.ID_TOUR, tourOffer.getIdDetail());
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
        TextView tvTitleOffer, tvTourDiscount, tvTourCost, tvTourSlot;
        Button btnExplore;
        LinearLayout llInfor;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            imvOffer = itemView.findViewById(R.id.imv_tour);
            tvTitleOffer = itemView.findViewById(R.id.tv_tour_title);
            tvTourCost = itemView.findViewById(R.id.tv_tour_cost);
            tvTourDiscount = itemView.findViewById(R.id.tv_tour_discount);
            tvTourSlot = itemView.findViewById(R.id.tv_tour_slot);
            btnExplore = itemView.findViewById(R.id.btn_explore);
            llInfor = itemView.findViewById(R.id.ll_infor);
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
