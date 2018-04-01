package ru.lantimat.worksearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import ru.lantimat.worksearch.googleForm.GoogleFormActivity;
import ru.lantimat.worksearch.googleForm.GoogleFormRestClient;

public class MainActivity2 extends AppCompatActivity {

    public static final String TAG = "MainActivity2";
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initGoogleForm();
        //getGoogleForm();


    }

     public void googleFormOpen(View view) {
         FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
         if(firebaseUser!=null) {
             startActivity(new Intent(this, GoogleFormActivity.class));
         } else {
             firebaseAuth();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //startActivity(new Intent(this, GoogleFormActivity.class));
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
            }
        }
    }

    private void initGoogleForm() {
        GoogleFormRestClient.initCookieStore(this);
    }

    public void getGoogleForm() {
        GoogleFormRestClient.get("viewform", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String body = new String(responseBody);
                Document doc = Jsoup.parse(body);

                postData(addParams(doc));
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
        params.add("entry.788165351", "Вариант 1");
        GoogleFormRestClient.client.addHeader("referer", "https://docs.google.com/forms/d/e/" + GoogleFormRestClient.FORM_ID + "/viewform?fbzx=" + fbzx);
        params.add("entry.81096689", "Потому");
        //params.add("entry.2022510004_sentinel:", "");
        params.add("entry.2022510004", "");
        params.add("entry.789087003", "Вариант 1");
        params.add("entry.751611962", "Вариант 1");
        params.add("entry.1708765172", "Вариант 1");
        params.add("fvv", fvv);
        params.add("draftResponse", draftResponse);
        params.add("pageHistory", pageHistory);
        params.add("fbzx", fbzx);

        return params;
    }

    public void postData(RequestParams params) {
        GoogleFormRestClient.post("formResponse", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                Log.d(TAG, "onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}
