package br.com.workup.jobup.usuario;

/**
 * Created by Renevalda Maria on 09/04/2017.
 */
import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class UsuarioIntentService extends IntentService {
    public static final String ACAO_SINCRONIZAR = "sincronizar";
    public static final String EXTRA_SUCESSO   = "sucesso";
    public UsuarioIntentService() {
        super("UsuarioIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            UsuarioHttp usuarioHttp = new UsuarioHttp(this);
            Intent it = new Intent(ACAO_SINCRONIZAR);
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
            try {
                usuarioHttp.sincronizar();
                it.putExtra(EXTRA_SUCESSO, true);
            } catch (Exception e) {
                it.putExtra(EXTRA_SUCESSO, false);
                e.printStackTrace();
            } finally {
                lbm.sendBroadcast(it);
            }
        }
    }
}