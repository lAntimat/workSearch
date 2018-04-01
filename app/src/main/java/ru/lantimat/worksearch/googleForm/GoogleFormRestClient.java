package ru.lantimat.worksearch.googleForm;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

/**
 * Created by GabdrakhmanovII on 22.02.2018.
 */

public class GoogleFormRestClient {

    public static final String FORM_ID = "1FAIpQLSf2OYJXIDP6fg59qI1ZLwSKXEj8u20E3h8OKrtcOCdQj7rR8A";
    private static final String BASE_URL = "https://docs.google.com/forms/d/e/" + FORM_ID + "/";

    public static final String FULL_NAME = "entry.1127885767";
    public static final String BORN_DATE_YEAR = "entry.280065499_year";
    public static final String BORN_DATE_MONTH = "entry.280065499_month";
    public static final String BORN_DATE_DAY = "entry.280065499_day";
    public static final String FILL_DATE_YEAR = "entry.1328223884_year";
    public static final String FILL_DATE_MONTH = "entry.1328223884_month";
    public static final String FILL_DATE_DAY = "entry.1328223884_day";
    public static final String AGE = "entry.69483597";
    public static final String STATUS = "entry.921177075";
    public static final String LIFE_PLACE = "entry.1700946550";
    public static final String PHONE_NUMBER = "entry.653188440";
    public static final String EMAIL = "entry.1974285170";
    public static final String EDUCATION = "entry.1363735778";
    public static final String STUDY_PLACE = "entry.333026667";
    public static final String COMPUTER_USER = "entry.1107493084";
    public static final String WORK = "entry.555251460";
    public static final String WORK_TYPE = "entry.1441619368";
    public static final String VK = "entry.212484943";
    public static final String WORK_PREFERENCE = "entry.788165351";
    public static final String CONFIRM = "entry.1177307618";

        public static AsyncHttpClient client = new AsyncHttpClient();
        private static PersistentCookieStore myCookieStore;


    public static void initCookieStore(Context context) {
            myCookieStore = new PersistentCookieStore(context);
            client.setCookieStore(myCookieStore);
        }

        public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.setTimeout(20000);
            client.setEnableRedirects(true);
            client.setUserAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.167 Safari/537.36");
            client.get(getAbsoluteUrl(url), params, responseHandler);
        }

        public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.setTimeout(20000);
            client.setEnableRedirects(true);
            client.setUserAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.167 Safari/537.36");
            client.post(getAbsoluteUrl(url), params, responseHandler);
        }

        private static String getAbsoluteUrl(String relativeUrl) {
            return BASE_URL + relativeUrl;
        }


        public static void clearCookie() {
        myCookieStore.clear();
    }
        public static PersistentCookieStore getCookieStore() {
        return myCookieStore;
    }

    }
