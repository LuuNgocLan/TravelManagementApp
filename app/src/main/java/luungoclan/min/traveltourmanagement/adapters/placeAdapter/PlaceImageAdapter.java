package luungoclan.min.traveltourmanagement.adapters.placeAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.models.places.Place;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.views.places.ListTourInPlaceActivity;

public class PlaceImageAdapter extends BaseAdapter {
    private Context context;
    private List<Place> placeList;

    public PlaceImageAdapter(Context context, List<Place> placeList) {
        this.context = context;
        this.placeList = placeList;
    }

    @Override
    public int getCount() {
        return placeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.grid_item_place, null);
            ImageView imvPlace = gridView.findViewById(R.id.imv_place);
            final Button btnExplore = gridView.findViewById(R.id.btn_explore);
            btnExplore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "HEHE", Toast.LENGTH_SHORT).show();
                }
            });
            imvPlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListTourInPlaceActivity.class);
                    intent.putExtra(Common.ID_LOCATION, placeList.get(position).getId());
                    intent.putExtra(Common.NAME_LOCATION, placeList.get(position).getName());
                    context.startActivity(intent);
                }
            });
            TextView tvNamePlace = gridView.findViewById(R.id.tv_namePlace);
            tvNamePlace.setText(placeList.get(position).getName());
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
