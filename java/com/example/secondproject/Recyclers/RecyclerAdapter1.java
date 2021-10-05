package com.example.secondproject.Recyclers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.secondproject.Model.Apple;
import com.example.secondproject.ProductDetailsActivity;
import com.example.secondproject.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder> {

    private static final String TAG = "RecyclerAdapter1";
    private Context mContext;
    private ArrayList<Apple> appleList;
    private LayoutInflater layoutInflater;


    public RecyclerAdapter1(Context context, ArrayList<Apple> appleList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.appleList = appleList;
    }

    @NonNull
    @Override
    public RecyclerAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_items,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.tvDescription.setText(appleList.get(position).getName());
        viewHolder.tvPrice.setText(appleList.get(position).getPrice());
        Picasso.get().load(appleList.get(position).getUrl()).into(viewHolder.imageView);


    }


    @Override
    public int getItemCount() {
        return appleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView tvDescription;
        TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), ProductDetailsActivity.class);
                    i.putExtra("Name",getAdapterPosition());
                    v.getContext().startActivity(i);
                }
            });
            imageView = itemView.findViewById(R.id.imageView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }

    }
}




