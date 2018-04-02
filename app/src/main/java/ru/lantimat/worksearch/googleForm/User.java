package ru.lantimat.worksearch.googleForm;

/**
 * Created by lAntimat on 02.04.2018.
 */

public class User {
    private boolean isGoogleFormSend;

    public User() {
    }

    public User(boolean isGoogleFormSend) {
        this.isGoogleFormSend = isGoogleFormSend;
    }

    public boolean isGoogleFormSend() {
        return isGoogleFormSend;
    }

    public void setGoogleFormSend(boolean googleFormSend) {
        isGoogleFormSend = googleFormSend;
    }
}
