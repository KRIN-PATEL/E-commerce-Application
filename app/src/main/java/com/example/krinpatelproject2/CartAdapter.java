package com.example.krinpatelproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private Context context;
    private Runnable updateTotalsCallback;

    public CartAdapter(List<CartItem> cartItems, Context context, Runnable updateTotalsCallback) {
        this.cartItems = cartItems;
        this.context = context;
        this.updateTotalsCallback = updateTotalsCallback;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        Product product = cartItem.getProduct();

        holder.cartProductName.setText(product.getProductName());
        holder.cartProductPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.cartProductQuantity.setText(String.valueOf(cartItem.getQuantity()));

        int resourceId = context.getResources().getIdentifier(product.getImageUrl(), "drawable", context.getPackageName());
        Glide.with(context).load(resourceId).into(holder.cartProductImage);

        holder.tvIncreaseQuantity.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            holder.cartProductQuantity.setText(String.valueOf(cartItem.getQuantity()));
            updateTotalsCallback.run();
        });

        holder.tvDecreaseQuantity.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                holder.cartProductQuantity.setText(String.valueOf(cartItem.getQuantity()));
                updateTotalsCallback.run();
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            removeItem(position);
            updateTotalsCallback.run();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void removeItem(int position) {
        cartItems.remove(position);
        notifyItemRemoved(position);
        CartManager.getInstance().removeItem(position);
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView cartProductImage, btnDelete;
        TextView cartProductName, cartProductPrice, cartProductQuantity;
        TextView tvIncreaseQuantity, tvDecreaseQuantity;

        CartViewHolder(View itemView) {
            super(itemView);
            cartProductImage = itemView.findViewById(R.id.cart_product_image);
            cartProductName = itemView.findViewById(R.id.cart_product_name);
            cartProductPrice = itemView.findViewById(R.id.cart_product_price);
            cartProductQuantity = itemView.findViewById(R.id.cart_product_quantity);
            tvIncreaseQuantity = itemView.findViewById(R.id.tv_increase_quantity);
            tvDecreaseQuantity = itemView.findViewById(R.id.tv_decrease_quantity);
            btnDelete = itemView.findViewById(R.id.btn_delete);  // Add this line
        }
    }
}
