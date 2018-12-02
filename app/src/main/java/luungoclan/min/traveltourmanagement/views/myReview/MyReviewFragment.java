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
public class MyReviewFragment extends Fragment implements IMyReviewView {

    @BindView(R.id.rv_listReview)
    RecyclerView rvReviews;

    @BindView(R.id.tv_msg_no_review)
    TextView tvNoReview;

    private MyReviewImpl myReviewPresenter;
    private ReviewOfUserAdapter adapter;
    private List<Review> mListReviews = new ArrayList<>();
    private IMyReviewImpl iMyReviewImpl;

    public MyReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_review, container, false);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
        adapter = new ReviewOfUserAdapter(mListReviews, getContext());
        rvReviews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvReviews.setAdapter(adapter);
    }

    public void getListReviewFromServer(String token) {
        if (token != null) {
            iMyReviewImpl = new MyReviewImpl(this);
            iMyReviewImpl.getMyReview(token);
        } else {
            mListReviews.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getMyDataReviewSuccess(List<Review> reviewList) {
        if (reviewList != null) {
            mListReviews.addAll(reviewList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getMyReviewFailure() {

    }

    @Override
    public void onShowProgressDialog(String msg) {

    }

    @Override
    public void onDismissProgressDialog() {

    }
}
