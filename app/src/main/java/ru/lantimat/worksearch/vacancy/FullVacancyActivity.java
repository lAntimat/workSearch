package ru.lantimat.worksearch.vacancy;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import ru.lantimat.worksearch.FirestoreConst;
import ru.lantimat.worksearch.R;
import ru.lantimat.worksearch.feeds.List.News;
import ru.lantimat.worksearch.googleForm.User;

public class FullVacancyActivity extends AppCompatActivity {

    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab;
    CollapsingToolbarLayout collapsingToolbar;
    TextView tvName, tvDate, tvContacts, tvCompany, tvSalary, tvCity, tvExp, tvWorkType, tvFullText;
    ProgressBar progressBar;
    ImageView imageView;
    View topContent;
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
        setContentView(R.layout.activity_full_vacancy);
        db = FirebaseFirestore.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setStatusBarTranslucent(false);
        imageView = findViewById(R.id.imageView);
        //fab = findViewById(R.id.fab);
        //fab.hide();

        tvName = findViewById(R.id.tvName);
        tvDate = findViewById(R.id.tvDate);
        tvContacts = findViewById(R.id.tvContacts);
        tvCompany = findViewById(R.id.tvCompany);
        tvCity = findViewById(R.id.tvCity);
        tvExp = findViewById(R.id.tvExp);
        tvSalary = findViewById(R.id.tvSalary);
        tvWorkType = findViewById(R.id.tvWorkType);
        tvFullText = findViewById(R.id.tvFullText);
        //htmlTextView = findViewById(R.id.html_text);
        topContent = findViewById(R.id.top_content);
        topContent.setVisibility(View.INVISIBLE);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        db.collection(FirestoreConst.VACANCY).document(getIntent().getStringExtra("id")).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            updateUI(documentSnapshot.toObject(Vacancy.class));
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

    public void updateUI(final Vacancy vacancy) {
        toolbar.setTitle("Просмотр вакансии");
        tvName.setText(vacancy.getName());
        tvCompany.setText(vacancy.getCompanyName());
        tvWorkType.setText(vacancy.getWorkType());
        tvSalary.setText(vacancy.getSalary());
        SimpleDateFormat sf = new SimpleDateFormat("dd MMM HH:MM", new Locale("ru", "RU"));
        tvDate.setText(sf.format(vacancy.getDate()));
        tvExp.setText(vacancy.getExperiency());
        tvCity.setText(vacancy.getCity());
        tvFullText.setText(vacancy.getFullText());
        topContent.setVisibility(View.VISIBLE);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            db.collection(FirestoreConst.USERS).document(user.getUid()).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                User user = documentSnapshot.toObject(User.class);
                                if (user.isGoogleFormSend())
                                    tvContacts.setText(vacancy.getNumberForCall());
                                else
                                    tvContacts.setText("Для просмотра контактов, нужно заполнить анкету");
                            } else
                                tvContacts.setText("Для просмотра контактов, нужно заполнить анкету");
                        }
                    });
        } else {
            tvContacts.setText("Для просмотра контактов, нужно авторизоваться и заполнить анкету!");
        }
    }


}
