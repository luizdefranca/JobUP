package br.com.workup.jobup.usuario;

/**
 * Created by Renevalda Maria on 09/04/2017.
 */
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.workup.jobup.dao.UsuarioRepositorio;
import br.com.workup.jobup.dao.UsuarioSQLHelper;

public class UsuarioHttp {
    public static final String SERVIDOR = "http://genesyspmpe.com";
    private static final String WEBSERVICE_URL = SERVIDOR +"/webserviceusu.php";
    private Context mContext;
    private UsuarioRepositorio mRepositorio;
    public UsuarioHttp(Context ctx) {
        mContext = ctx;
        mRepositorio = new UsuarioRepositorio(mContext);
    }
    public void sincronizar() throws Exception {
        enviarDadosPendentes();
        List<Usuario> usuarios = getUsuarios();
        ContentResolver cr = mContext.getContentResolver();
        for (Usuario usuario : usuarios) {
            usuario.status = Usuario.Status.OK;
            mRepositorio.inserirLocal(usuario, cr);
        }
    }
    private void enviarDadosPendentes() throws Exception{
        Cursor cursor = mContext.getContentResolver().query(
                UsuarioProvider.CONTENT_URI, null,
                UsuarioSQLHelper.COLUNA_STATUS +" != "+
                        Usuario.Status.OK.ordinal(), null,
                UsuarioSQLHelper.COLUNA_ID_SERVIDOR +" DESC");
        while (cursor.moveToNext()) {
            Usuario usuario = UsuarioRepositorio.usuarioFromCursor(cursor);
            if (usuario.status == Usuario.Status.INSERIR) {
                inserir(usuario);
            } else if (usuario.status == Usuario.Status.EXCLUIR) {
                excluir(usuario);
            } else if (usuario.status == Usuario.Status.ATUALIZAR) {
                if (usuario.idServidor == 0) {
                    inserir(usuario);
                } else {
                    atualizar(usuario);
                }
            }
        }
    }
    private void inserir(Usuario usuario) {
        try {
            if (enviarUsuario("POST", usuario)) {
                usuario.status = Usuario.Status.OK;
                mRepositorio.atualizarLocal(usuario, mContext.getContentResolver());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void atualizar(Usuario usuario) {
        try {
            if (enviarUsuario("PUT", usuario)) {
                usuario.status = Usuario.Status.OK;
                mRepositorio.atualizarLocal(usuario, mContext.getContentResolver());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void excluir(Usuario usuario) {
        boolean podeExcluir = true;
        if (usuario.idServidor != 0) {
            try {
                podeExcluir = enviarUsuario("DELETE", usuario);
            } catch (Exception e) {
                podeExcluir = false;
                e.printStackTrace();
            }
        }
        if (podeExcluir) {
            mRepositorio.excluirLocal(usuario, mContext.getContentResolver());
        }
    }
    private boolean enviarUsuario(String metodoHttp, Usuario usuario) throws Exception {
        boolean sucesso = false;
        boolean doOutput = !"DELETE".equals(metodoHttp);
        String url = WEBSERVICE_URL;
        if (!doOutput) {
            url += "/"+ usuario.idServidor;
        }
        HttpURLConnection conexao = abrirConexao(url, metodoHttp, doOutput);
        if (doOutput) {
            OutputStream os = conexao.getOutputStream();
            os.write(usuarioToJsonBytes(usuario));
            os.flush();
            os.close();
        }
        int responseCode = conexao.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();
            JSONObject json = new JSONObject(s);
            usuario.idServidor = json.getInt("id");
            sucesso = true;
        } else {
            sucesso = false;
        }
        conexao.disconnect();
        return sucesso;
    }
    private HttpURLConnection abrirConexao(String url,
                                           String metodo, boolean doOutput) throws Exception{
        URL urlCon = new URL(url);
        HttpURLConnection conexao = (HttpURLConnection) urlCon.openConnection();
        conexao.setReadTimeout(15000);
        conexao.setConnectTimeout(15000);
        conexao.setRequestMethod(metodo);
        conexao.setDoInput(true);
        conexao.setDoOutput(doOutput);
        if (doOutput) {
            conexao.addRequestProperty("Content-Type", "application/json");
        }
        conexao.connect();
        return conexao;
    }
    private List<Usuario> getUsuarios() throws Exception {
        HttpURLConnection conexao = abrirConexao(WEBSERVICE_URL, "GET", false);
        List<Usuario> list = new ArrayList<Usuario>();
        if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String jsonString = streamToString(conexao.getInputStream());
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                JSONObject usuarioJSON = json.getJSONObject(i);
                Usuario u = new Usuario(
                        0,
                        usuarioJSON.getString("nome"),
                        usuarioJSON.getString("email"),
                        usuarioJSON.getString("senha"),
                        (float)usuarioJSON.getDouble("estrelas"),
                        usuarioJSON.getInt("id"),
                        Usuario.Status.OK);
                list.add(u);
            }
        }
        return list;
    }
    private String streamToString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }
    private byte[] usuarioToJsonBytes(Usuario usuario) {
        try {
            JSONObject jsonPessoa = new JSONObject();
            jsonPessoa.put("id", usuario.idServidor);
            jsonPessoa.put("nome", usuario.idServidor);
            jsonPessoa.put("email", usuario.idServidor);
            jsonPessoa.put("senha", usuario.idServidor);
            jsonPessoa.put("estrelas", usuario.estrelas);
            String json = jsonPessoa.toString();
            return json.getBytes();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}