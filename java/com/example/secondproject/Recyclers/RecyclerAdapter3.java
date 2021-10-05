package com.example.secondproject.Recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secondproject.Model.Camera;
import com.example.secondproject.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class RecyclerAdapter3 extends RecyclerView.Adapter<RecyclerAdapter3.ViewHolder> {

    private static final String TAG = "RecyclerAdapter3";
    private Context mContext;
    private ArrayList<Camera> cameraList;


    public RecyclerAdapter3(Context context, ArrayList<Camera> cameraList){
        this.mContext = context;
        this.cameraList = cameraList;
    }

    @NonNull
    @Override
    public  RecyclerAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDescription.setText (cameraList.get(position).getName());
        holder.tvPrice.setText (cameraList.get(position).getPrice());
        Picasso.get().load (cameraList.get(position).getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return cameraList.size();
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




