package ru.lantimat.worksearch.feeds.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.lantimat.worksearch.R;


/**
 * Created by GabdrakhmanovII on 31.07.2017.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<News> mList;

    public NewsRecyclerAdapter(Context context, ArrayList<News> itemList) {
        this.context = context;
        this.mList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_normal_feed, parent, false);
                return new NormalFeedViewHolder(view);
        /*view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_mark, parent, false);
        return new MarksRecyclerAdapter.ImageViewHolder(view);*/

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String date = mList.get(position).getFormattedDate();
        String title = mList.get(position).getTitle();
        String subTitle = mList.get(position).getSubTitle();
        String image = mList.get(position).getImage();

                ((NormalFeedViewHolder) holder).title.setText(title);
                ((NormalFeedViewHolder) holder).subTitle.setText(subTitle);
                ((NormalFeedViewHolder) holder).date.setText(date);
                Picasso.get().load(image).into(((NormalFeedViewHolder) holder).mImg);
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return 0;
    }


    public static class NormalFeedViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView subTitle;
        private TextView date;
        private ImageView mImg;

        public NormalFeedViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvFeedTitle);
            subTitle = (TextView) itemView.findViewById(R.id.tvFeedSupportingText);
            date = (TextView) itemView.findViewById(R.id.tvFeedtextView);
            mImg = (ImageView) itemView.findViewById(R.id.ivFeedImage);
        }
    }
}
