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

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvAdapterViewHolder> {
    private List<ApiResponse.ItemMovies> mPopMoviesResponseList = new ArrayList<>();
    private OnClickListener mOnClickListener = null;
    private boolean mIsLoading = true;

    @Override
    public int getItemCount() {
        if (mIsLoading) {
            return mPopMoviesResponseList.size() - 1;
        } else {
            return mPopMoviesResponseList.size();
        }
    }

    class ItemViewHolder extends RvAdapter.RvAdapterViewHolder {
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RvAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
            View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
            return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterViewHolder holder, int position) {
//        holder.bind(mPopMoviesResponseList.get(position));
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(mPopMoviesResponseList.get(position));
        }
    }

    public void setData(List<ApiResponse.ItemMovies> itemMovies) {
        mPopMoviesResponseList = itemMovies;
        notifyDataSetChanged();
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }

    public void setListener(OnClickListener listener) {
        mOnClickListener = listener;
    }

    interface OnClickListener {
        void onClick(ApiResponse.ItemMovies item);
    }

    public class RvAdapterViewHolder extends RecyclerView.ViewHolder {
        public RvAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(ApiResponse.ItemMovies popMovies) {
            ImageView iv = itemView.findViewById(R.id.imagePopMovie);
            String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
            Picasso.get()
                    .load(BASE_IMAGE_URL + popMovies.image)
                    .into(iv);
            if (popMovies.date != null) {
                String revDate = ApiResponse.reverseDate(popMovies.date);
                ((TextView) itemView.findViewById(R.id.movieDateRelease)).setText(revDate);
            } else
                ((TextView) itemView.findViewById(R.id.movieDateRelease)).setText("нет данных");
            ((TextView) itemView.findViewById(R.id.namePopMovie)).setText(popMovies.title);
            if (popMovies.rating != null) {
                if (popMovies.rating.startsWith("0")) {
                    ((TextView) itemView.findViewById(R.id.movieRating)).setText("нет данных");
                } else
                    ((TextView) itemView.findViewById(R.id.movieRating)).setText(popMovies.rating);
                itemView.setOnClickListener(v -> mOnClickListener.onClick(popMovies));
            }
        }
    }
}