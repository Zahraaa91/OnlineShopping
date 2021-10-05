package com.example.secondproject.Recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secondproject.Model.Accessories;
import com.example.secondproject.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class RecyclerAdapter4 extends RecyclerView.Adapter<RecyclerAdapter4.ViewHolder> {

    private static final String TAG = "RecyclerAdapter4";
    private Context mContext;
    private ArrayList<Accessories> accessoriesList;


    public RecyclerAdapter4(Context context, ArrayList<Accessories> accessoriesList){
        this.mContext = context;
        this.accessoriesList = accessoriesList;
    }

    @NonNull
    @Override
    public  RecyclerAdapter4.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDescription.setText (accessoriesList.get(position).getName());
        holder.tvPrice.setText (accessoriesList.get(position).getPrice());
        Picasso.get().load (accessoriesList.get(position).getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return accessoriesList.size();
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




