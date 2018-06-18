package ru.lantimat.worksearch.vacancy;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import ru.lantimat.worksearch.FirestoreConst;
import ru.lantimat.worksearch.feeds.List.News;
import ru.lantimat.worksearch.feeds.List.NewsMVP;
import ru.lantimat.worksearch.feeds.full.FullNewsActivity;


/**
 * Created by lAntimat on 24.11.2017.
 */

public class VacancyPresenter implements VacancyMVP.Presenter {

    private Context context;
    private VacancyMVP.View view;
    private int page = 0;
    private boolean isLoading = false;
    private static boolean isOnRefresh = false;
    private ArrayList<Vacancy> ar = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public VacancyPresenter() {
    }

    @Override
    public void attachView(Context context, VacancyMVP.View view) {
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
            ar = new ArrayList<>();
            db.collection(FirestoreConst.VACANCY).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            for (DocumentSnapshot doc: documentSnapshots) {
                                Vacancy vacancy = doc.toObject(Vacancy.class);
                                vacancy.setId(doc.getId());
                                ar.add(vacancy);
                            }
                            view.showData(ar);
                            view.hideLoading();
                            isLoading = false;
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
        Intent intent = new Intent(context, FullVacancyActivity.class);
        intent.putExtra("id", ar.get(position).getId());
        view.startFeedActivity(intent);
    }
}
