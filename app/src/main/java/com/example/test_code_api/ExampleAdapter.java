package com.example.test_code_api;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_code_api.model.ExampleItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.exampleViewHolder> {
    private ArrayList<ExampleItem> mExamplelist;
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";

    private Context mContext;
    private OnItemClickListener mListener;

    public ExampleAdapter(ArrayList<ExampleItem> mExamplelist) {
        this.mExamplelist = mExamplelist;
    }

    public void setFilteredList(ArrayList<ExampleItem> filteredList) {
        this.mExamplelist = filteredList;
        notifyDataSetChanged();
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ExampleAdapter(Context context, ArrayList<ExampleItem> examplelist) {
        mContext = context;
        mExamplelist = examplelist;
    }


    @NonNull
    @Override
    public exampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new exampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull exampleViewHolder holder, int position) {
        ExampleItem currentItem = mExamplelist.get(position);

        String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getCreator();
        double likecount = currentItem.getLikeCount();
        String date = currentItem.getDate();

        String sharedPrefFav;
        String sharedPrefList;
        String sharedPrefRating;

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("notes:" + likecount);
        holder.mTextViewDate.setText("date:" + date);


        sharedPrefFav = mContext.getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString("favorite-" + holder.mTextViewCreator.getText().toString(), null);
        sharedPrefList = mContext.getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString("list-" + holder.mTextViewCreator.getText().toString(), null);
        sharedPrefRating = mContext.getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString("rating-" + holder.mTextViewCreator.getText().toString(), null);

        if (sharedPrefFav != null && sharedPrefFav.length() > 0) {
            holder.mFavoriteFilms.setChecked(true);
        }

        if (sharedPrefList != null && sharedPrefList.length() > 0) {
            holder.mListFilms.setChecked(true);
        }

        if (sharedPrefRating != null && sharedPrefRating.length() > 0) {
            holder.mRatingFilms.setRating(Float.parseFloat(sharedPrefRating));
        }

        holder.mFavoriteFilms.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                mContext.getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString("favorite-" + holder.mTextViewCreator.getText().toString(), "favorite")
                        .apply();
            } else {
                mContext.getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .remove("favorite-" + holder.mTextViewCreator.getText().toString())
                        .apply();
            }
        });
        holder.mListFilms.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                mContext.getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString("list-" + holder.mTextViewCreator.getText().toString(), "liste")
                        .apply();
            } else {
                mContext.getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .remove("list-" + holder.mTextViewCreator.getText().toString())
                        .apply();
            }
        });
        holder.mRatingFilms.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            mContext.getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putString("rating-" + holder.mTextViewCreator.getText().toString(), String.valueOf(v))
                    .apply();
        });

        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mExamplelist.size();
    }

    public class exampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;
        public TextView mTextViewDate;
        Switch mFavoriteFilms;
        Switch mListFilms;
        RatingBar mRatingFilms;

        public exampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);
            mTextViewDate = itemView.findViewById(R.id.text_view_date);
            mFavoriteFilms = itemView.findViewById(R.id.myFavorite);
            mListFilms = itemView.findViewById(R.id.myList);
            mRatingFilms = itemView.findViewById(R.id.myRating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);

                        }
                    }
                }
            });


        }
    }

}
