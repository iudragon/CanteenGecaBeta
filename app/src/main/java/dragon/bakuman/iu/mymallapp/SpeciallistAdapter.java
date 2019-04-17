package dragon.bakuman.iu.mymallapp;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class SpeciallistAdapter extends RecyclerView.Adapter<SpeciallistAdapter.ViewHolder> {

    private List<SpeciallistModel> speciallistModelList;

    private Boolean speciallist;

    private int lastPosition = -1;

    private FirebaseUser currentUser;


    public SpeciallistAdapter(List<SpeciallistModel> speciallistModelList, Boolean speciallist) {
        this.speciallistModelList = speciallistModelList;
        this.speciallist = speciallist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        currentUser = FirebaseAuth.getInstance().getCurrentUser();


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.speciallist_item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        String productId = speciallistModelList.get(position).getProductId();
        String resource = speciallistModelList.get(position).getProductImage();
        String title = speciallistModelList.get(position).getProductTitle();
        long freeCoupons = speciallistModelList.get(position).getFreeCoupons();
        String rating = speciallistModelList.get(position).getRating();
        long totalRatings = speciallistModelList.get(position).getTotalRatings();
        String productPrice = speciallistModelList.get(position).getProductPrice();
        boolean paymentMethod = speciallistModelList.get(position).isCOD();

        viewHolder.setData(productId, resource, title, freeCoupons, rating, totalRatings, productPrice, paymentMethod, position);

        if (lastPosition < position) {

            Animation animation = AnimationUtils.loadAnimation(viewHolder.itemView.getContext(), R.anim.fade_in);
            viewHolder.itemView.setAnimation(animation);
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return speciallistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        //        private TextView freeCoupons;
//        private ImageView couponIcon;
        private TextView productPrice;
        //        private TextView paymentMethod;
        private TextView rating;
        //        private TextView totalRatings;
        private View priceCut;
        private ImageButton deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
//            freeCoupons = itemView.findViewById(R.id.free_coupon);
//            couponIcon = itemView.findViewById(R.id.coupon_icon);
//            rating = itemView.findViewById(R.id.tv_product_rating_miniview);
//            totalRatings = itemView.findViewById(R.id.total_ratings);
            productPrice = itemView.findViewById(R.id.product_price);
//            paymentMethod = itemView.findViewById(R.id.payment_method);
            deleteBtn = itemView.findViewById(R.id.delete_button);

        }

        private void setData(final String productId, String resource, String title, long freeCouponsNo, String averageRate, long totalRatingsNo, String price, boolean COD, final int index) {

            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.placeholdericonmini)).into(productImage);

            productTitle.setText(title);


            productPrice.setTextColor(itemView.getContext().getResources().getColor(R.color.colorBlack));
            productPrice.setText("Rs. " + price + "/-");

            if (speciallist) {
                if (currentUser == null) {
                    deleteBtn.setVisibility(View.GONE);
                } else if (currentUser != null) {
                    deleteBtn.setVisibility(View.VISIBLE);

                }


            } else {

                deleteBtn.setVisibility(View.GONE);
            }

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                String productId;

                @Override
                public void onClick(View v) {

                    if (!ProductDetailsActivity.running_speciallist_query) {

                        if (DBqueries.speciallistModelList.size() > 0) {
                            productId = DBqueries.speciallistModelList.get(index).getProductId();
                            // productId = wishlistModelList.get(index).getProductId();
                            Log.d("remove index:", productId + " " + index);

                            DBqueries.removeFromSpeciallist(productId, itemView.getContext(), true);
                        } else
                            Log.d("list error=", "" + speciallistModelList.size());


                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);

                    productDetailsIntent.putExtra("PRODUCT_ID", productId);

                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
    }


}







