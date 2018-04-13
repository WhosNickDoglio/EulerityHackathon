package com.nicholasdoglio.eulerityhackathon.ui.base;

/**
 * @param <T>
 * @author Nicholas Doglio
 * Base for Presenters
 */
public interface BasePresenter<T> {

    /* Connects the Presenter to the View */
    void attach(T view);

    /* Disconnects the Presenter from the View */
    void detach();

    /* Clears out any disposables */
    void clearDisposables();
}
