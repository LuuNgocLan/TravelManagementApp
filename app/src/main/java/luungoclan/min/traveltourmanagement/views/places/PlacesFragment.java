package luungoclan.min.traveltourmanagement.views.places;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.Toast;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.placeAdapter.PlaceImageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFragment extends Fragment {

    private GridView gvPlaces;
    private PlaceImageAdapter placeImageAdapter;
    private String[] placeList = {"Nha trang", "Đà Nẵng","Quy Nhơn","Pháp","Anh","Mỹ","Canada","Thailand","Campuchia"};

    public PlacesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_places, container, false);
        init(view);
        setEvent();
        return view;
    }

    private void setEvent() {
//        gvPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(),"hihi",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void init(View view) {
        gvPlaces = view.findViewById(R.id.gv_places);
        gvPlaces.setAdapter(new PlaceImageAdapter(getContext(),placeList));
    }

}
