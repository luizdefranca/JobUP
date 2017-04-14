package com.br.jobup.configuration;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by luizramos on 12/04/17.
 */

public class StartApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(config);

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("job_up.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);


    }
}
