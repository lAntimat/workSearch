package ru.lantimat.worksearch.vacancy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ru.lantimat.worksearch.R;
import ru.lantimat.worksearch.feeds.List.News;


/**
 * Created by GabdrakhmanovII on 31.07.2017.
 */

public class VacancyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Vacancy> mList;

    public VacancyRecyclerAdapter(Context context, ArrayList<Vacancy> itemList) {
        this.context = context;
        this.mList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_vacancy, parent, false);
                return new NormalFeedViewHolder(view);
        /*view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_mark, parent, false);
        return new MarksRecyclerAdapter.ImageViewHolder(view);*/

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Date date = mList.get(position).getDate();
        SimpleDateFormat sf = new SimpleDateFormat("dd MMM HH:MM", new Locale("ru", "RU"));
        String name = mList.get(position).getName();
        String company = mList.get(position).getCompanyName();
        String salary = mList.get(position).getSalary();

                ((NormalFeedViewHolder) holder).name.setText(name);
                ((NormalFeedViewHolder) holder).companyName.setText(company);
                ((NormalFeedViewHolder) holder).date.setText(sf.format(date));
                ((NormalFeedViewHolder) holder).tvSalary.setText(salary);
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
        private TextView name;
        private TextView companyName;
        private TextView date;
        private TextView tvSalary;

        public NormalFeedViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            companyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
            date = (TextView) itemView.findViewById(R.id.tvDate);
            tvSalary = (TextView) itemView.findViewById(R.id.tvSalary);
        }
    }
}
