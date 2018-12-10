package luungoclan.min.traveltourmanagement.adapters.bookingAdapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import luungoclan.min.traveltourmanagement.R;
import luungoclan.min.traveltourmanagement.adapters.ItemClickListener;
import luungoclan.min.traveltourmanagement.api.ApiClient;
import luungoclan.min.traveltourmanagement.api.ApiInterface;
import luungoclan.min.traveltourmanagement.models.booking.Booking;
import luungoclan.min.traveltourmanagement.models.booking.BookingResponse;
import luungoclan.min.traveltourmanagement.models.booking.CancelBookingResponse;
import luungoclan.min.traveltourmanagement.utils.Common;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.SimpleViewHolder> {
    private Context context;
    private List<Booking> bookingList;
    private int lastPosition = -1;
    private ProgressDialog progressDialog;

    public BookingAdapter(Context context, List<Booking> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_booking, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, int position) {
        final Booking booking = bookingList.get(position);
        final Drawable drawable = context.getResources().getDrawable(R.drawable.button_style_normal);
        ColorFilter filter;
        holder.tvIdOrder.setText(booking.getId() + "");
        holder.tvDateOrder.setText(booking.getDateOrdered());
        holder.tvNameTour.setText(booking.getNameTour());
        holder.tvPrice.setText(booking.getTotal() + " VNĐ");

        switch (booking.getStatus()) {
            case 0:
                holder.tvStatus.setText("Đơn đã hủy!");
                filter = new LightingColorFilter(Color.GRAY, Color.GRAY);
                drawable.setColorFilter(filter);
                holder.btnCancel.setVisibility(View.GONE);
                break;
            case 1:
                holder.tvStatus.setText("Chưa duyệt!");
                filter = new LightingColorFilter(Color.GREEN, Color.GREEN);
                drawable.setColorFilter(filter);
                holder.btnCancel.setVisibility(View.VISIBLE);
                holder.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: gọi api hủy đơn

                    }
                });
                break;
            case 2:
                holder.tvStatus.setText("Đặt thành công!");
                filter = new LightingColorFilter(Color.YELLOW, Color.YELLOW);
                drawable.setColorFilter(filter);
                holder.btnCancel.setVisibility(View.GONE);
                break;
        }
        holder.tvStatus.setBackground(drawable);
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Đang xử lí...");
                progressDialog.show();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("id", booking.getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

                SharedPreferences sharedPreferences = context.getSharedPreferences(Common.PREF_REMEMBER_ME, MODE_PRIVATE);
                String token = sharedPreferences.getString(Common.TOKEN_SAVED, null);
                if (token != null) {
                    callApiCancelMyBooking(token, body);
                }

            }

            private void callApiCancelMyBooking(String token, RequestBody body) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<CancelBookingResponse> call = apiService.cancelMyBooking(token, body);
                call.enqueue(new Callback<CancelBookingResponse>() {
                    @Override
                    public void onResponse(Call<CancelBookingResponse> call, Response<CancelBookingResponse> response) {
                        if (response.code() >= 300) {
                            //TODO: FAILURE
                            Toast.makeText(context, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 200) {
                            //TODO:SUCCESS
                            holder.tvStatus.setText("Đơn đã hủy!");
                            ColorFilter filter = new LightingColorFilter(Color.GRAY, Color.GRAY);
                            drawable.setColorFilter(filter);
                            holder.btnCancel.setVisibility(View.GONE);
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<CancelBookingResponse> call, Throwable t) {
                        Toast.makeText(context, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                    }
                });
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
        return bookingList.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvIdOrder;
        TextView tvDateOrder;
        TextView tvNameTour;
        TextView tvStatus;
        TextView tvPrice;
        Button btnCancel;

        private ItemClickListener itemClickListener;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            tvIdOrder = itemView.findViewById(R.id.tvIdOrder);
            tvDateOrder = itemView.findViewById(R.id.tvDateOrder);
            tvNameTour = itemView.findViewById(R.id.tvNameTour);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnCancel = itemView.findViewById(R.id.btnCancel);
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
