package br.com.workup.jobup.dao;

/**
 * Created by luizramos on 13/04/17.
 */

public interface UsuarioDatabaseListener {

    public static final String TAG = UsuarioDatabaseListener.class.getName();


    void onDatabaseOperationFailed(String error);
    void onDatabaseOperationSucceded(String message);

}