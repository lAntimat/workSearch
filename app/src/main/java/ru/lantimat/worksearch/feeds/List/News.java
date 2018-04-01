package ru.lantimat.worksearch.feeds.List;

import com.google.firebase.firestore.ServerTimestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by GabdrakhmanovII on 04.09.2017.
 */

public class News {
    public String id;
    public String title;
    public String subTitle;
    @ServerTimestamp
    public Date date;
    public String image;

    public News() {
    }

    public News(String title, String subTitle, Date date, String image) {
        this.title = title;
        this.subTitle = subTitle;
        this.date = date;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate() {
        SimpleDateFormat sf = new SimpleDateFormat("dd MMM HH:MM", new Locale("ru", "RU"));
        return sf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
