package com.br.jobup.services;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.br.jobup.models.Usuario;

import java.util.List;

/**
 * Created by luizramos on 29/04/17.
 */





    public class GetAllTask<Usuario> extends AsyncTaskLoader<List<Usuario>> {
        public final String TAG = GetAllTask.class.getSimpleName();
        List<Usuario> mUsuarios;


        public GetAllTask(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();

            if (mUsuarios == null){
                Log.e(TAG, "onStartLoading: forceLoad" );
                forceLoad();
            }else {
                Log.e(TAG, "onStartLoading: deliverResult");
                deliverResult(mUsuarios);
            }
        }

        @Override
        public List<Usuario> loadInBackground() {

            Parser parser = new Parser();

            mUsuarios = parser.getAll();
            return mUsuarios;
        }
    }




