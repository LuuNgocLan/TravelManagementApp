package luungoclan.min.traveltourmanagement.adapters.reviewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.models.reviewOfUser.Review;

public class ReviewOfUserAdapter extends RecyclerView.Adapter<ReviewOfUserAdapter.MyViewHolder> {
    private List<Review> reviewList;
    private Context context;
    private int lastPosition = -1;

    public ReviewOfUserAdapter(List<Review> reviewList, Context context) {
        this.reviewList = reviewList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_of_user, parent, false);
        return new ReviewOfUserAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Review review = reviewList.get(position);
        holder.tvDateReview.setText(review.getDateReview());
        holder.tvContentReview.setText(review.getContent());
        holder.tvNameTour.setText(review.getNameTour());
        holder.rbTour.setRating(Integer.parseInt(review.getScore()));
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameTour, tvContentReview, tvDateReview;
        private RatingBar rbTour;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvContentReview = itemView.findViewById(R.id.tv_content_item_review);
            tvNameTour = itemView.findViewById(R.id.tv_name_tour);
            tvDateReview = itemView.findViewById(R.id.tv_date_item_review);
            rbTour = itemView.findViewById(R.id.rb_tour);
        }

    }
}
