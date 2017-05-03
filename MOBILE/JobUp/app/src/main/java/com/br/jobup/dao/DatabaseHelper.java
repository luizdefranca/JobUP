package com.br.jobup.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.br.jobup.util.Constantes;
import com.br.jobup.validations.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Renevalda Maria on 09/04/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements Constantes {

    public static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    private static final String NOME_BANCO = "jobup";
    private static final int VERSAO_BANCO = 1;
    private static DatabaseHelper mDatabaseInstance = null;
    private Context mContext;

    public static DatabaseHelper newInstance(Context context){
        //first check to see if the database helper
        //member data is null
        //create a new one if it is null

        if (mDatabaseInstance == null){
            mDatabaseInstance = new DatabaseHelper(context.getApplicationContext());
        }

        //either way we have to always return an instance of
        //our database class each time this method is called
        return mDatabaseInstance;
    }

    public DatabaseHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CREATE_USUARIO);
            sqLiteDatabase.execSQL(CREATE_ESPECIALIDADE);
            sqLiteDatabase.execSQL(CREATE_PERFIL_PROFISSIONAL);
            sqLiteDatabase.execSQL(CREATE_AVALIACAO);
            sqLiteDatabase.execSQL(CREATE_FORMACAO);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("Erro no banto", "onCreate: " + e.toString());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // para as próximas versões
    }


    public boolean backup() {

        String backupFileName = "Backup_" + FileUtils.getDatetimeSuffix(System.currentTimeMillis());

        File backupFolder = new File(Environment.getExternalStoragePublicDirectory("ProntoShop"), "");
        if (!backupFolder.exists()) {
            backupFolder.mkdirs();
        }
        // Get database files.
        File backupDb = new File(backupFolder, backupFileName);
        File localDb = new File(String.valueOf(mContext.getDatabasePath(NOME_BANCO)));
        // Attempt to backup the database.
        try {

            // Create the backup database file if it doesn't already exist.
            backupDb.createNewFile();

            // Ensure both files exist.
            if (localDb.exists() && backupDb.exists()) {
                // Copy the local database file to the backup database file.
                FileUtils.copyFile(new FileInputStream(localDb), new FileOutputStream(backupDb));

                String backupPath = backupDb.getAbsolutePath();
                Log.d(LOG_TAG, "Backup Path is: " + backupPath);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }



    public boolean restoreDatabase(String backupPath) {

        File backupDatabase = new File(backupPath);
        File localDb = new File(String.valueOf(mContext.getDatabasePath(NOME_BANCO)));
        // Attempt to restore the database.
        try {
            // Ensure the database we're restoring exists.
            if (backupDatabase.exists()) {
                // Restore the new database.
                FileUtils.copyFile(new FileInputStream(backupDatabase), new FileOutputStream(localDb));
                // Access the copied database so SQLiteHelper will cache it and mark it as created.
                if (getWritableDatabase() != null) {
                    getWritableDatabase().close();
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }


}
