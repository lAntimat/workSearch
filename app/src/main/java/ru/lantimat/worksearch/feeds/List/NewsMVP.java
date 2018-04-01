package ru.lantimat.worksearch.feeds.List;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import ru.lantimat.worksearch.LoadingView;


/**
 * Created by lAntimat on 24.11.2017.
 */

public interface NewsMVP {

    interface View extends LoadingView {
        void showData(ArrayList<News> ar);
        void startFeedActivity(Intent intent);
    }

    interface Presenter {
        void attachView(Context context, View view);
        void detachView();
        void loadData();
        void refreshData();
        void recyclerClick(int position);
    }

}
