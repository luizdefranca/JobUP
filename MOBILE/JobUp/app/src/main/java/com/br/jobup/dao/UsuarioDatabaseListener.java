package com.br.jobup.dao;

/**
 * Created by luizramos on 13/04/17.
 */

public interface UsuarioDatabaseListener {

    public static final String TAG = UsuarioDatabaseListener.class.getName();


    void onDatabaseOperationFailed(String error);
    void onDatabaseOperationSucceded(String message);

    void onSQLOperationSucceded(String s);

    void onSQLOperationFailed(String s);
}
