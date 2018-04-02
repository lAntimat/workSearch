package ru.lantimat.worksearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ru.lantimat.worksearch.feeds.List.FeedActivity;
import ru.lantimat.worksearch.feeds.List.News;
import ru.lantimat.worksearch.googleForm.GoogleFormActivity;
import ru.lantimat.worksearch.vacancy.Vacancy;
import ru.lantimat.worksearch.vacancy.VacancyActivity;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 123;


    public Toolbar toolbar;
    public Spinner spinner;
    public AppBarLayout appBarLayout;
    public Drawer result;
    public AccountHeader headerResult;
    public FrameLayout frameLayout;
    public FrameLayout frameLayout2;
    public CoordinatorLayout topLayout;
    Intent drawerIntent = null;
    boolean dontFinish = false;
    SecondaryDrawerItem sign_exit;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topLayout = findViewById(R.id.coordinator);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        appBarLayout = findViewById(R.id.appbar);
        frameLayout = (FrameLayout)findViewById(R.id.content_frame);
        frameLayout2 = (FrameLayout)findViewById(R.id.content_frame2);

        spinner = (Spinner) findViewById(R.id.spinner_nav);
        spinner.setVisibility(View.INVISIBLE);

        color = ContextCompat.getColor(getApplicationContext(), R.color.accent);

        getSupportActionBar().setTitle("");

        DrawerImageLoader.init(new DrawerImageLoader.IDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.get().load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Picasso.get().load(uri).placeholder(placeholder).fit().into(imageView);

            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.get().cancelRequest(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx) {
                return ContextCompat.getDrawable(ctx, R.mipmap.ic_launcher);
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                return ContextCompat.getDrawable(ctx, R.mipmap.ic_launcher);
            }
        });


        initAccountHeader();
        if(result==null){
            setupNavigationDrawer();
        }

        //addNews();
        //addVacancy();
    }//

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        //startActivity(new Intent(MainActivity.this, LoginActivity.class));
        //Log.d("OnPostResume", "WTF");
        super.onPostResume();
    }

    public void updateDrawer() {
        StringHolder stringHolder;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) stringHolder = new StringHolder(R.string.drawer_item_exit);
        else stringHolder = new StringHolder(R.string.drawer_item_sign_in);
        result.updateName(6, stringHolder);
    }

    public void updateAccount() {
        String fullName = "";
        String group = "";
        ProfileDrawerItem profileDrawerItem = null;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            profileDrawerItem = new ProfileDrawerItem()
                    .withName(user.getDisplayName())
                    .withEmail(user.getPhoneNumber())
                    .withIcon(R.mipmap.ic_launcher);

            headerResult.updateProfile(profileDrawerItem);
        }
    }


    @Override
    public void onBackPressed() {
        if(result!=null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else super.onBackPressed();
    }

    private void initAccountHeader() {


        String fullName = "";
        String group = "";
        ProfileDrawerItem profileDrawerItem = null;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            profileDrawerItem = new ProfileDrawerItem()
                    .withName(user.getDisplayName())
                    .withEmail(user.getPhoneNumber())
                    .withIcon(R.mipmap.ic_launcher);

            headerResult = new AccountHeaderBuilder()
                    .withActivity(this)
                    .withHeaderBackground(R.drawable.ic_launcher_background)
                    .addProfiles(profileDrawerItem)
            .build();
        } else {
            headerResult = new AccountHeaderBuilder()
                    .withActivity(this)
                    .withHeaderBackground(R.drawable.ic_launcher_background)
                    .build();
        }

    }

    public void firebaseAuth() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                //new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
                //new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                //new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                //new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()
        );

// Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme)
                        .build(),
                RC_SIGN_IN);
    }

    public void setupNavigationDrawer() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem news = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_news).withIcon(R.drawable.ic_launcher_foreground).withIconColor(color);
        PrimaryDrawerItem vacancy = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_vacancy).withIcon(R.drawable.ic_launcher_foreground).withIconColor(color);
        PrimaryDrawerItem googleForm = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.drawer_item_google_form).withIcon(R.drawable.ic_launcher_foreground).withIconColor(color);
        SecondaryDrawerItem sign_exit;
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) sign_exit = new SecondaryDrawerItem().withIdentifier(6).withName(R.string.drawer_item_exit).withIconColor(color);
        else sign_exit = new SecondaryDrawerItem().withIdentifier(6).withName(R.string.drawer_item_sign_in).withIconColor(color);

        PrimaryDrawerItem about = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.drawer_item_about).withIcon(R.drawable.ic_launcher_foreground).withIconColor(color);

        //create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        news,
                        vacancy,
                        googleForm,
                        new DividerDrawerItem(),
                        about,
                        sign_exit
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                drawerIntent = new Intent(MainActivity.this, FeedActivity.class);
                                break;
                            case 2:
                                drawerIntent = new Intent(MainActivity.this, VacancyActivity.class);
                                break;
                            case 3:
                                googleFormOpen();
                                break;
                            case 5:
                                new About().onCreateDialog(MainActivity.this).show();
                                break;
                            case 6:
                                if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
                                    FirebaseAuth.getInstance().signOut();
                                   firebaseAuth();
                                    finish();
                                }
                                else {
                                    firebaseAuth();
                                    dontFinish = true;
                                }
                                break;
                        }
                        result.closeDrawer();
                        return true;
                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        if(drawerIntent!=null) {
                            startActivity(drawerIntent);
                            overridePendingTransition(0, 0);
                            drawerIntent = null;
                            if(!dontFinish) finish();
                            dontFinish = false;
                        }
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .build();
    }

    public void googleFormOpen() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null) {
            startActivity(new Intent(this, GoogleFormActivity.class));
        } else {
            Toast.makeText(this, "Для продолжения необходимо авторизоваться!", Toast.LENGTH_LONG).show();
            firebaseAuth();
        }
    }

    public void addNews() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(FirestoreConst.NEWS).add(new News("Test1", "TestSubtite", new Date(), "https://www.w3schools.com/howto/img_fjords.jpg"));
        db.collection(FirestoreConst.NEWS).add(new News("Test2", "TestSubtite", new Date(), "https://www.w3schools.com/howto/img_fjords.jpg"));
        db.collection(FirestoreConst.NEWS).add(new News("Test3", "TestSubtite", new Date(), "https://www.w3schools.com/howto/img_fjords.jpg"));
        db.collection(FirestoreConst.NEWS).add(new News("Test4", "TestSubtite", new Date(), "https://www.w3schools.com/howto/img_fjords.jpg"));
    }

    public void addVacancy() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(FirestoreConst.VACANCY).add(new Vacancy("Test1", "10000", new Date(), "Диля inc", "Яр Чаллы", "Без опыта", "Полный день", "Мы предлагаем вам вообще огонь все будет хорошо, но денег не длите, хер вам", "+79600747198" ));
        db.collection(FirestoreConst.VACANCY).add(new Vacancy("Test2", "10000", new Date(), "Диля inc", "Яр Чаллы", "Без опыта", "Полный день", "Мы предлагаем вам вообще огонь все будет хорошо, но денег не длите, хер вам", "+79600747198" ));
        db.collection(FirestoreConst.VACANCY).add(new Vacancy("Test3", "10000", new Date(), "Диля inc", "Яр Чаллы", "Без опыта", "Полный день", "Мы предлагаем вам вообще огонь все будет хорошо, но денег не длите, хер вам", "+79600747198" ));
        db.collection(FirestoreConst.VACANCY).add(new Vacancy("Test4", "10000", new Date(), "Диля inc", "Яр Чаллы", "Без опыта", "Полный день", "Мы предлагаем вам вообще огонь все будет хорошо, но денег не длите, хер вам", "+79600747198" ));
        db.collection(FirestoreConst.VACANCY).add(new Vacancy("Test5", "10000", new Date(), "Диля inc", "Яр Чаллы", "Без опыта", "Полный день", "Мы предлагаем вам вообще огонь все будет хорошо, но денег не длите, хер вам", "+79600747198" ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        initAccountHeader();
        setupNavigationDrawer();
        super.onActivityResult(requestCode, resultCode, data);
    }
}


