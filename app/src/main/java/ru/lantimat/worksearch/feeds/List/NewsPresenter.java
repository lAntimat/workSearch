package ru.lantimat.worksearch.feeds.List;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import ru.lantimat.worksearch.FirestoreConst;
import ru.lantimat.worksearch.feeds.full.FullNewsActivity;


/**
 * Created by lAntimat on 24.11.2017.
 */

public class NewsPresenter implements NewsMVP.Presenter {

    private Context context;
    private NewsMVP.View view;
    private int page = 0;
    private boolean isLoading = false;
    private static boolean isOnRefresh = false;
    private ArrayList<News> ar = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public NewsPresenter() {
    }

    @Override
    public void attachView(Context context, NewsMVP.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void loadData() {
        if(!isLoading) {
            if(!isOnRefresh) view.showLoading();
            isLoading = true;

            db.collection(FirestoreConst.NEWS).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            for (DocumentSnapshot doc: documentSnapshots) {
                                News news = doc.toObject(News.class);
                                news.setId(doc.getId());
                                ar.add(news);
                            }
                            view.showData(ar);
                            view.hideLoading();
                        }
                    });
        }
    }

    @Override
    public void refreshData() {
        page = 0;
        isOnRefresh = true;
        loadData();
    }

    @Override
    public void recyclerClick(int position) {
        Intent intent = new Intent(context, FullNewsActivity.class);
        intent.putExtra("id", ar.get(position).getId());
        view.startFeedActivity(intent);
    }
}
