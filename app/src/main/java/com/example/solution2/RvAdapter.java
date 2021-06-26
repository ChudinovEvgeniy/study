package com.example.solution2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvAdapterViewHolder> {
    private List<ApiResponse.ItemPopularMovies> mPopMoviesResponseList = new ArrayList<>();
    private OnClickListener mOnClickListener = null;

    @Override
    public int getItemCount() {
        return mPopMoviesResponseList.size();
    }

    @NonNull
    @Override
    public RvAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new RvAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterViewHolder holder, int position) {
        holder.bind(mPopMoviesResponseList.get(position));
    }

    public void setData(List<ApiResponse.ItemPopularMovies> popMovies) {
        mPopMoviesResponseList = popMovies;
        notifyDataSetChanged();
    }

    public void setListener(OnClickListener listener) {
        mOnClickListener = listener;
    }

    interface OnClickListener {
        void onClick(ApiResponse.ItemPopularMovies item);
    }

    public class RvAdapterViewHolder extends RecyclerView.ViewHolder {
        public RvAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(ApiResponse.ItemPopularMovies popMovies) {
            ImageView iv = itemView.findViewById(R.id.imagePopMovie);
            String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
            Picasso.get()
                    .load(BASE_IMAGE_URL + popMovies.image)
                    .into(iv);
            if (popMovies.date != null){
                String revDate = ApiResponse.reverseDate(popMovies.date);
                ((TextView) itemView.findViewById(R.id.movieDateRelease)).setText(revDate);
            }else
                ((TextView) itemView.findViewById(R.id.movieDateRelease)).setText("нет данных");
            ((TextView) itemView.findViewById(R.id.namePopMovie)).setText(popMovies.title);
            if (popMovies.rating.startsWith("0")){
                ((TextView) itemView.findViewById(R.id.movieRating)).setText("нет данных");
            }else
            ((TextView) itemView.findViewById(R.id.movieRating)).setText(popMovies.rating);
            itemView.setOnClickListener(v -> mOnClickListener.onClick(popMovies));
        }
    }
}

