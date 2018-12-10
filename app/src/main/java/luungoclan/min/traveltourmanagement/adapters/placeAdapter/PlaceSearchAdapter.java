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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ItemClickListener;
import luungoclan.min.traveltourmanagement.models.places.Place;
import luungoclan.min.traveltourmanagement.models.places.SelectedPlace;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.views.places.ListTourInPlaceActivity;

public class PlaceSearchAdapter extends RecyclerView.Adapter<PlaceSearchAdapter.SimpleViewHolder> {
    private Context context;
    private List<Place> placeList;
    private OnItemSelectedListener listener;
    private boolean[] dataSelectedItem;

    public PlaceSearchAdapter(Context context, List<Place> placeList, OnItemSelectedListener listener) {
        this.context = context;
        this.placeList = placeList;
        this.listener = listener;
        this.dataSelectedItem = new boolean[placeList.size()];
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location_search, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, int position) {
        final Place place = placeList.get(position);
        holder.bind(place, listener, position);
        holder.setChecked(dataSelectedItem[position]);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamePlace;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            tvNamePlace = itemView.findViewById(R.id.tv_name_place);
        }

        public void setChecked(boolean value) {
            if (value) {
                tvNamePlace.setBackground(context.getResources().getDrawable(R.drawable.shape_button_round_selected));
            } else {
                tvNamePlace.setBackground(context.getResources().getDrawable(R.drawable.shape_button_round));
            }

        }

        public void bind(final Place place, final OnItemSelectedListener listener, final int position) {
            tvNamePlace.setText(place.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataSelectedItem = new boolean[placeList.size()];
                    dataSelectedItem[position] = true;
                    setChecked(dataSelectedItem[position]);
                    listener.onItemSelected(place);
                }
            });
        }
    }

    public interface OnItemSelectedListener {

        void onItemSelected(Place place);
    }


}
