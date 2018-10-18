package luungoclan.min.traveltourmanagement.views.publicTour;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.tourAdapter.TourOfferAdapter;
import luungoclan.min.traveltourmanagement.models.fakes.TourOffer;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicTourFragment extends Fragment implements View.OnClickListener {

    private Button btnWhere, btnWhen, btnProceedToResult;
    private LinearLayout llSearchWhere, llSearchWhen;
    private List<TourOffer> tourOfferList;
    private RecyclerView rvTourOffer;
    private TourOfferAdapter tourOfferAdapter;
    private RecyclerView rvTopDestinations;
    public boolean isShowWhereSearch = true, isShowWhenSearch = true;

    public PublicTourFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_public_tour, container, false);
        init(view);
        setEvent();
        fakeData();
        return view;
    }

    private void setEvent() {
        btnWhen.setOnClickListener(this);
        btnWhere.setOnClickListener(this);
        btnProceedToResult.setOnClickListener(this);
    }

    private void fakeData() {
        tourOfferList.add(new TourOffer(1, "Winter beach escape 30% off"));
        tourOfferList.add(new TourOffer(1, "Winter beach escape 30% off"));
        tourOfferList.add(new TourOffer(1, "Winter beach escape 30% off"));
        tourOfferList.add(new TourOffer(1, "Winter beach escape 30% off"));
        tourOfferList.add(new TourOffer(1, "Winter beach escape 30% off"));
        tourOfferList.add(new TourOffer(1, "Winter beach escape 30% off"));
        tourOfferAdapter = new TourOfferAdapter(tourOfferList, getActivity());
        rvTourOffer.setAdapter(tourOfferAdapter);
        rvTopDestinations.setAdapter(tourOfferAdapter);
    }

    private void init(View view) {
        btnWhen = view.findViewById(R.id.btn_when);
        btnWhere = view.findViewById(R.id.btn_where);
        btnProceedToResult = view.findViewById(R.id.btn_proceedToResult);
        llSearchWhen = view.findViewById(R.id.ll_wheneSearch);
        llSearchWhere = view.findViewById(R.id.ll_whereSearch);
        rvTourOffer = view.findViewById(R.id.rv_latestOffers);
        rvTourOffer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvTopDestinations = view.findViewById(R.id.rv_topDestinations);
        rvTopDestinations.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        tourOfferList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_when:
                Toast.makeText(getContext(),isShowWhenSearch+"",Toast.LENGTH_SHORT).show();
                if (isShowWhenSearch) {
                    llSearchWhen.setVisibility(View.VISIBLE);
                } else {
                    llSearchWhen.setVisibility(View.GONE);
                }
                isShowWhenSearch = !isShowWhenSearch;
                break;
            case R.id.btn_where:
                Toast.makeText(getContext(),isShowWhereSearch+"",Toast.LENGTH_SHORT).show();
                if (isShowWhereSearch) {
                    llSearchWhere.setVisibility(View.VISIBLE);
                } else {
                    llSearchWhere.setVisibility(View.GONE);
                }
                isShowWhereSearch = !isShowWhereSearch;
                break;
            case R.id.btn_proceedToResult:
                Toast.makeText(getContext(),"Searching...",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
