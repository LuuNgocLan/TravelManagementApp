package luungoclan.min.traveltourmanagement.adapters.reviewAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ItemClickListener;
import luungoclan.min.traveltourmanagement.models.common.review.Review;
import luungoclan.min.traveltourmanagement.models.tourList.Tour;
import luungoclan.min.traveltourmanagement.utils.Common;
import luungoclan.min.traveltourmanagement.views.detailTour.DetailTourActivity;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    private List<Review> reviewList;
    private Context context;

    public ReviewAdapter(List<Review> reviewList, Context context) {
        this.reviewList = reviewList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_tour, parent, false);
        return new ReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Review review = reviewList.get(position);
        holder.tvNameReviewer.setText(review.getName());
        holder.tvDateReview.setText(review.getDateReview());
        holder.tvContentReview.setText(review.getContent());
        holder.rbTour.setRating(Integer.parseInt(review.getScore())/2);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imvAvatar;
        TextView tvNameReviewer, tvContentReview, tvDateReview, tvTimeReview;
        private RatingBar rbTour;

        public MyViewHolder(View itemView) {
            super(itemView);
            imvAvatar = itemView.findViewById(R.id.imv_avatar);
            tvNameReviewer = itemView.findViewById(R.id.tv_name_item_review);
            tvContentReview = itemView.findViewById(R.id.tv_content_item_review);
            tvDateReview = itemView.findViewById(R.id.tv_date_item_review);
            tvTimeReview = itemView.findViewById(R.id.tv_time_item_review);
            rbTour = itemView.findViewById(R.id.rb_tour);
        }

    }
}
