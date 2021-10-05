package com.example.secondproject.Recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secondproject.Model.Samsung;
import com.example.secondproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {

    private static final String TAG = "RecyclerAdapter2";
    private Context mContext;
    private ArrayList<Samsung> samsungList;


    public RecyclerAdapter2(Context context, ArrayList<Samsung> samsungList){
        this.mContext = context;
        this.samsungList = samsungList;
    }

    @NonNull
    @Override
    public  RecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDescription.setText (samsungList.get(position).getName());
        holder.tvPrice.setText (samsungList.get(position).getPrice());
        Picasso.get().load (samsungList.get(position).getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return samsungList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvDescription;
        TextView tvPrice;

        public ViewHolder(View itemview) {
            super(itemview);
            imageView = itemView.findViewById(R.id.imageView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);

        }
    }
}




