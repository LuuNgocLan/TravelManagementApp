package luungoclan.min.traveltourmanagement.adapters.placeAdapter;

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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ItemClickListener;
import luungoclan.min.traveltourmanagement.models.places.Place;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.views.places.ListTourInPlaceActivity;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.SimpleViewHolder> {
    private Context context;
    private List<Place> placeList;
    private int lastPosition = -1;
    int[] images = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
            R.drawable.img_4, R.drawable.img_5, R.drawable.img_6,
            R.drawable.img_7, R.drawable.img_8, R.drawable.img_9};

    public PlaceAdapter(Context context, List<Place> placeList) {
        this.context = context;
        this.placeList = placeList;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_1, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        final Place place = placeList.get(position);

        Random rand = new Random();
        int num = rand.nextInt(9);
        String urlImage = Common.BASE_URL + place.getImage();
        if (urlImage != null) {
            Glide.with(context).
                    load(urlImage).
                    placeholder(images[num]).
                    error(R.drawable.img_default).
                    into(holder.imvPlace);
            holder.tvNamePlace.setText(place.getName());
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int adapterPosition, boolean b) {
                Intent intent = new Intent(context, ListTourInPlaceActivity.class);
                intent.putExtra(Common.ID_LOCATION, place.getId());
                intent.putExtra(Common.NAME_LOCATION, place.getName());
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imvPlace;
        TextView tvNamePlace;
        Button btnExplore;
        private ItemClickListener itemClickListener;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            imvPlace = itemView.findViewById(R.id.imv_place);
            tvNamePlace = itemView.findViewById(R.id.tv_namePlace);
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
