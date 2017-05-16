package com.br.jobup.http;

import com.br.jobup.models.usuario.Usuario;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

/**
 * Created by luizramos on 23/04/17.
 */




public class PushNotification {
    private MobileServiceClient mClient;
    private MobileServiceTable<Usuario> mUsuarioTable;

//    public void getConection(){
//        try {
//// Create the Mobile Service Client instance, using the provided
//// Mobile Service URL and key
//            mClient = new MobileServiceClient(
//                    "MobileServiceUrl",
//                    "AppKey",
//                    this)
//                   .withFilter(new ProgressFilter()
//                   );
//
//            // Get the Mobile Service Table instance to use
//            mUsuarioTable = mClient.getTable(Usuario.class);
//        } catch (MalformedURLException e) {
//            //createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
//        }
//    }
}
