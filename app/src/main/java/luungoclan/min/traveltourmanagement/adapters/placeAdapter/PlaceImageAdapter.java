package luungoclan.min.traveltourmanagement.adapters.placeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import luungoclan.min.traveltourmanagement.R;

public class PlaceImageAdapter extends BaseAdapter {
    private Context context;
    private String[] placeList;

    public PlaceImageAdapter(Context context, String[] placeList) {
        this.context = context;
        this.placeList = placeList;
    }

    @Override
    public int getCount() {
        return placeList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
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
                    Toast.makeText(context,"HEHE",Toast.LENGTH_SHORT).show();
                }
            });
            imvPlace.setOnHoverListener(new View.OnHoverListener() {
                @Override
                public boolean onHover(View v, MotionEvent event) {
                    btnExplore.setVisibility(View.VISIBLE);
                    return true;
                }
            });
            TextView tvNamePlace = gridView.findViewById(R.id.tv_namePlace);
            tvNamePlace.setText(placeList[position]);
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
