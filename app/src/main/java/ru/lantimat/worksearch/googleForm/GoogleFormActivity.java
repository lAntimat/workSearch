package ru.lantimat.worksearch.googleForm;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Calendar;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import ru.lantimat.worksearch.FirestoreConst;

public class GoogleFormActivity extends AppIntro {

    public static final String TAG = "GoogleFormActivity";
    public String fullName = "";
    public Calendar bornDate;
    public Calendar fillDate;
    public String age = "";
    public String status = "";
    public String lifePlace = "";
    public String phoneNumber = "";
    public String computerStatus = "";
    public String workType = "";
    public String email = "";
    public String education = "";
    public String studyPlace = "";
    public String work = "";
    public String vk = "";
    public HashMap<String, String> mapPreference;
    private RequestParams params;

    public NameFragment nameFragment = new NameFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Note here that we DO NOT use setContentView();

        setFadeAnimation();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(nameFragment);
        addSlide(new SecondFragment());
        addSlide(new FiftFragment());
        addSlide(new ThirdFragment());
        addSlide(new FourFragment());
        //addSlide(thirdFragment);
        //addSlide(fourthFragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        //addSlide(AppIntroFragment.newInstance("title", "Desr", R.drawable.ic_launcher_background, ContextCompat.getColor(getApplicationContext(), R.color.amber_700)));

        // OPTIONAL METHODS
        // Override bar/separator color.
        //setBarColor(Color.parseColor("#3F51B5"));
        //setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);
        setDoneText("Отправить");

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        //setVibrate(true);
        //setVibrateIntensity(30);

        initGoogleForm();
        getGoogleForm();

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            finish();
        }
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        addParamsFromFragment();
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    private void initGoogleForm() {
        GoogleFormRestClient.initCookieStore(this);
    }

    public void getGoogleForm() {
        GoogleFormRestClient.get("viewform", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, "getGoogleForm on Success");
                String body = new String(responseBody);
                Document doc = Jsoup.parse(body);

                params = addParams(doc);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    private RequestParams addParams(Document doc) {
        String fvv = doc.select("input[name=fvv]").attr("value");
        String draftResponse = doc.select("input[name=draftResponse]").attr("value");
        String pageHistory = doc.select("input[name=pageHistory]").attr("value");
        String fbzx = doc.select("input[name=fbzx]").attr("value");

        RequestParams params = new RequestParams();
        //params.add("entry.788165351.other_option_response", "");
        //params.add("entry.788165351", "Вариант 1");
        GoogleFormRestClient.client.addHeader("referer", "https://docs.google.com/forms/d/e/" + GoogleFormRestClient.FORM_ID + "/viewform?fbzx=" + fbzx);

        params.add("fvv", fvv);
        params.add("draftResponse", draftResponse);
        params.add("pageHistory", pageHistory);
        params.add("fbzx", fbzx);

        return params;
    }

    private void addParamsFromFragment() {
        if(TextUtils.isEmpty(fullName)) {
            showToast("Введите ФИО");
            getPager().setCurrentItem(0);
            return;
        }

        if(bornDate==null) {
            showToast("Введите дату рождения");
            getPager().setCurrentItem(0);
            return;
        }

        if(TextUtils.isEmpty(status)) {
            showToast("Выберите кем вы являетесь на данный момент");
            getPager().setCurrentItem(1);
            return;
        }

        if(TextUtils.isEmpty(education)) {
            showToast("Выберите образование");
            getPager().setCurrentItem(1);
            return;
        }

        if(TextUtils.isEmpty(studyPlace)) {
            showToast("Введите наименование учебного заведения");
            getPager().setCurrentItem(1);
            return;
        }

        if(TextUtils.isEmpty(lifePlace)) {
            showToast("Выберите место жительства");
            getPager().setCurrentItem(2);
            return;
        }

        if(TextUtils.isEmpty(phoneNumber)) {
            showToast("Введите номер телефона");
            getPager().setCurrentItem(2);
            return;
        }

        if(TextUtils.isEmpty(email)) {
            showToast("Введите номер телефона");
            getPager().setCurrentItem(2);
            return;
        }

        if(TextUtils.isEmpty(vk)) {
            showToast("Введите номер телефона");
            getPager().setCurrentItem(2);
            return;
        }

        if(TextUtils.isEmpty(computerStatus)) {
            showToast("Выберите навыки работы с компьютером");
            getPager().setCurrentItem(2);
            return;
        }

        if(TextUtils.isEmpty(workType)) {
            showToast("Выберите тип занятости");
            getPager().setCurrentItem(2);
            return;
        }

        if(mapPreference==null) {
            showToast("Выберите сферу для работы");
            getPager().setCurrentItem(2);
            return;
        } else if(mapPreference.size()==0) {
                showToast("Выберите сферу для работы");
                getPager().setCurrentItem(2);
                return;
            }

        if(TextUtils.isEmpty(work)) {
            showToast("Выберите навыки работы с компьютером");
            getPager().setCurrentItem(2);
            return;
        }

        params.add(GoogleFormRestClient.FULL_NAME, fullName);
        params.add(GoogleFormRestClient.BORN_DATE_YEAR, String.valueOf(bornDate.get(Calendar.YEAR)));
        params.add(GoogleFormRestClient.BORN_DATE_MONTH, String.valueOf(bornDate.get(Calendar.MONTH)));
        params.add(GoogleFormRestClient.BORN_DATE_DAY, String.valueOf(bornDate.get(Calendar.DAY_OF_MONTH)));
        params.add(GoogleFormRestClient.AGE, age);
        Calendar calendar = Calendar.getInstance();
        params.add(GoogleFormRestClient.FILL_DATE_YEAR, String.valueOf(calendar.get(Calendar.YEAR)));
        params.add(GoogleFormRestClient.FILL_DATE_MONTH, String.valueOf(calendar.get(Calendar.MONTH)));
        params.add(GoogleFormRestClient.FILL_DATE_DAY, String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        params.add(GoogleFormRestClient.STATUS, status);
        params.add(GoogleFormRestClient.EDUCATION, education);
        params.add(GoogleFormRestClient.STUDY_PLACE, studyPlace);
        params.add(GoogleFormRestClient.LIFE_PLACE, lifePlace);
        params.add(GoogleFormRestClient.PHONE_NUMBER, phoneNumber);
        params.add(GoogleFormRestClient.EMAIL, email);
        params.add(GoogleFormRestClient.VK, vk);
        params.add(GoogleFormRestClient.COMPUTER_USER, computerStatus);
        for (int i = 0; i < mapPreference.size(); i++) {
            params.add(GoogleFormRestClient.WORK_PREFERENCE, mapPreference.get(i));
        }
        params.add(GoogleFormRestClient.WORK, work);
        params.add(GoogleFormRestClient.WORK_TYPE, workType);
        params.add(GoogleFormRestClient.CONFIRM, "Согласен");

        postData(params);

    }

    public void postData(RequestParams params) {
        GoogleFormRestClient.post("formResponse", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                String response = Jsoup.parse(str).select("div.freebirdFormviewerViewResponseConfirmationMessage").text();
                if(response!=null) {
                    if(response.equals("Ответ записан.")) {
                        addToFirebase();
                    }
                }
                Log.d(TAG, "postData onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    private void addToFirebase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            db.collection(FirestoreConst.USERS).document(user.getUid()).set(new User(true))
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    showToast("Ответ записан.");
                    finish();
                }
            });
        }
    }

    public void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}
