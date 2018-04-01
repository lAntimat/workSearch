package ru.lantimat.worksearch;

/**
 * Created by lAntimat on 02.12.2017.
 */

public interface LoadingView {
    void showLoading();
    void hideLoading();
    void showError(String error);
}
