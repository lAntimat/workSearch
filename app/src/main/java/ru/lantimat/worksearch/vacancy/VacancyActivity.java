
package ru.lantimat.worksearch.vacancy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import ru.lantimat.worksearch.MainActivity;
import ru.lantimat.worksearch.R;
import ru.lantimat.worksearch.feeds.List.FeedFragment;


public class VacancyActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_schedule);
        //setTheme(R.style.AppTheme);
        FrameLayout v = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_vacancy, v);
        getSupportActionBar().setTitle("Вакансии");
        //getSupportActionBar().setSubtitle("Новостной молодежный медипортал");

        result.setSelection(2, false);
        showFragment();
    }

    @Override
    protected void onStart() {
        result.setSelection(2, false);
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menu
        switch (item.getItemId()) {
            //When home is clicked
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFragment() {
        VacancyFragment fragment = new VacancyFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment)
                .commit();
    }
}