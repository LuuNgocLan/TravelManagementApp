package luungoclan.min.traveltourmanagement.views.myReview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.reviewAdapter.ReviewOfUserAdapter;
import luungoclan.min.traveltourmanagement.models.reviewOfUser.Review;
import luungoclan.min.traveltourmanagement.presenters.myReview.IMyReviewImpl;
import luungoclan.min.traveltourmanagement.presenters.myReview.MyReviewImpl;
import luungoclan.min.traveltourmanagement.ui.BaseHeaderBar;
import luungoclan.min.traveltourmanagement.views.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyReviewFragment extends Fragment {

    @BindView(R.id.rv_listReview)
    RecyclerView rvReviews;

    @BindView(R.id.tv_msg_no_review)
    TextView tvNoReview;

    private ReviewOfUserAdapter adapter;
    private List<Review> mListReviews = new ArrayList<>();

    public MyReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_review, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        rvReviews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }

    public void getListReviewFromServer(List<Review> reviewList) {
        adapter = new ReviewOfUserAdapter(reviewList, getContext());
        rvReviews.setAdapter(adapter);
    }

}
