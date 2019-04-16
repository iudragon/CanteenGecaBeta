//
//package dragon.bakuman.iu.mymallapp;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.res.ColorStateList;
//import android.support.annotation.NonNull;
//import android.view.View;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.Query;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class temp {
//    public static void loadWishlist(final Context context, final Dialog dialog, final boolean loadProductData, String from) {
//
//        wishlist.clear();
//        //   Toast.makeText(context, "coming from="+from, Toast.LENGTH_SHORT).show();
//        firebaseFirestore.collection("USERS").document("pyFEsRmGjJVWejrOFagS0yEzJ1x1").collection("USER_DATA").document("MY_WISHLIST").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful() && task!=null) {
//                            Toast.makeText(context, "size0="+task.getResult().get("list_size").toString(), Toast.LENGTH_SHORT).show();
//                    for (long x = 0; x < (long)task.getResult().get("list_size"); x++) {
//                         //  Toast.makeText(context, "in loop x="+x, Toast.LENGTH_SHORT).show();
//                        wishlist.add(task.getResult().get("product_ID_" + x).toString());
//
//                        if (DBqueries.wishlist.contains(ProductDetailsActivity.productID)) {
//
//                            ProductDetailsActivity.ALREADY_ADDED_TO_WISHLIST = true;
//
//                            if (ProductDetailsActivity.addToWishlistBtn != null) {
//
//                                ProductDetailsActivity.addToWishlistBtn.setSupportImageTintList(context.getResources().getColorStateList(R.color.colorAccent));
//
//                            }
//
//
//                        } else {
//
//                            if (ProductDetailsActivity.addToWishlistBtn != null) {
//
//
//                                ProductDetailsActivity.addToWishlistBtn.setSupportImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.colorPrimary)));
//
//                            }
//
//                            ProductDetailsActivity.ALREADY_ADDED_TO_WISHLIST = false;
//                        }
//
//                        if (loadProductData) {
//
//                            wishlistModelList.clear();
//
//                            final String productId = task.getResult().get("product_ID_" + x).toString();
//
//                            firebaseFirestore.collection("PRODUCTS").document(productId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    if (task.isSuccessful()) {
//
//                                        final DocumentSnapshot documentSnapshot = task.getResult();
//
//                                        FirebaseFirestore.getInstance().collection("PRODUCTS").document(productId).collection("QUANTITY").orderBy("time", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                                if (task.isSuccessful()) {
//
//                                                    if (task.getResult().getDocuments().size() < (long) documentSnapshot.get("stock_quantity")) {
//
//                                                        wishlistModelList.add(new WishlistModel(productId, documentSnapshot.get("product_image_1").toString(), documentSnapshot.get("product_title").toString(), (long) documentSnapshot.get("free_coupons"), documentSnapshot.get("average_rating").toString(), (long) documentSnapshot.get("total_ratings"), documentSnapshot.get("product_price").toString(), documentSnapshot.get("cutted_price").toString(), (boolean) documentSnapshot.get("COD"), true));
//
//
//                                                    } else {
//
//                                                        wishlistModelList.add(new WishlistModel(productId, documentSnapshot.get("product_image_1").toString(), documentSnapshot.get("product_title").toString(), (long) documentSnapshot.get("free_coupons"), documentSnapshot.get("average_rating").toString(), (long) documentSnapshot.get("total_ratings"), documentSnapshot.get("product_price").toString(), documentSnapshot.get("cutted_price").toString(), (boolean) documentSnapshot.get("COD"), false));
//
//
//                                                    }
//
//                                                    MyWishlistFragment.wishlistAdapter.notifyDataSetChanged();
//
//
//                                                } else {
//                                                    String error = task.getException().getMessage();
//                                                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                        });
//
//
//                                    } else {
//
//                                        String error = task.getException().getMessage();
//                                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                        }
//                    }
//
//                } else {
//                    String error = task.getException().getMessage();
//                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
//
//                }
//
//                dialog.dismiss();
//            }
//        });
//    }
//
//
//}
//
//
