package ru.lantimat.worksearch.feeds.full;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import ru.lantimat.worksearch.FirestoreConst;
import ru.lantimat.worksearch.R;
import ru.lantimat.worksearch.feeds.List.News;

public class FullNewsActivity extends AppCompatActivity {

    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;
    CollapsingToolbarLayout collapsingToolbar;
    TextView textView;
    ProgressBar progressBar;
    ImageView imageView;
    FirebaseFirestore db;
    //Toolbar back button click
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_feed);
        db = FirebaseFirestore.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setStatusBarTranslucent(false);
        imageView = findViewById(R.id.imageView);
        //fab = findViewById(R.id.fab);
        //fab.hide();

        textView = findViewById(R.id.tvName);
        //htmlTextView = findViewById(R.id.html_text);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        db.collection(FirestoreConst.NEWS).document(getIntent().getStringExtra("id")).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            updateUI(documentSnapshot.toObject(News.class));
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

        // from View
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public void updateUI(News news) {
        toolbar.setTitle(news.getTitle());
        Picasso.get().load(news.getImage()).into(imageView);
        textView.setText(news.getSubTitle());
    }


}
