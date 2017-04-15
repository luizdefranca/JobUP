package br.com.workup.jobup.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import br.com.workup.jobup.dao.UsuarioSQLHelper;
import br.com.workup.jobup.usuario.Usuario;
import br.com.workup.jobup.usuario.UsuarioProvider;

/**
 * Created by Renevalda Maria on 09/04/2017.
 */
public class UsuarioRepositorio {

    private Context ctx;
    public UsuarioRepositorio(Context ctx) {
        this.ctx = ctx;
    }
    private long inserir(Usuario usuario) {
        usuario.status = Usuario.Status.INSERIR;
        long id = inserirLocal(usuario, ctx.getContentResolver());
        return id;
    }
    private int atualizar(Usuario usuario) {
        usuario.status = Usuario.Status.ATUALIZAR;
        int linhasAfetadas = atualizarLocal(usuario, ctx.getContentResolver());
        return linhasAfetadas;
    }
    public void salvar(Usuario usuario) {
        if (usuario.id == 0) {
            inserir(usuario);
        } else {
            atualizar(usuario);
        }
    }
    public int excluir(Usuario usuario) {
        usuario.status = Usuario.Status.EXCLUIR;
        int linhasAfetadas = atualizarLocal(usuario, ctx.getContentResolver());
        return linhasAfetadas;
    }
    public CursorLoader buscar(Context ctx, String s) {
        String where = null;
        String[] whereArgs = null;
        if (s != null) {
            where = UsuarioSQLHelper.COLUNA_NOME +" LIKE ?";
            whereArgs = new String[]{ "%"+ s +"%" };
        }
        return new CursorLoader(
                ctx,
                UsuarioProvider.CONTENT_URI,
                null,
                where,
                whereArgs,
                UsuarioSQLHelper.COLUNA_NOME);
    }
    private ContentValues getValues(Usuario usuario) {
        ContentValues cv = new ContentValues();
        cv.put(UsuarioSQLHelper.COLUNA_NOME, usuario.nome);
        cv.put(UsuarioSQLHelper.COLUNA_EMAIL, usuario.email);
        cv.put(UsuarioSQLHelper.COLUNA_SENHA, usuario.senha);
        cv.put(UsuarioSQLHelper.COLUNA_ESTRELAS, usuario.estrelas);
        cv.put(UsuarioSQLHelper.COLUNA_STATUS, usuario.status.ordinal());
        if (usuario.idServidor != 0) {
            cv.put(UsuarioSQLHelper.COLUNA_ID_SERVIDOR, usuario.idServidor);
        }

        return cv;
    }
    public static Usuario usuarioFromCursor(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndex(
                        UsuarioSQLHelper.COLUNA_ID)
        );

        String nome = cursor.getString(
                cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_NOME)
        );

        String email = cursor.getString(
                cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_EMAIL)
        );
        String senha = cursor.getString(
                cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_SENHA)
        );
        float estrelas = cursor.getFloat(
                cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_ESTRELAS)
        );
        int status = cursor.getInt(
                cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_STATUS)
        );
        long idServidor = cursor.getLong(
                cursor.getColumnIndex(UsuarioSQLHelper.COLUNA_ID_SERVIDOR)
        );

        Usuario usuario = new Usuario(id, nome, email, senha, estrelas, idServidor, Usuario.Status.values()[status]);
        return usuario;
    }

    public long inserirLocal(Usuario usuario, ContentResolver cr) {
        Uri uri = cr.insert(
                UsuarioProvider.CONTENT_URI,
                getValues(usuario));
        long id = Long.parseLong(uri.getLastPathSegment());
        if (id != -1) {
            usuario.id = id;
        }
        return id;
    }
    public int atualizarLocal(Usuario usuario, ContentResolver cr) {
        Uri uri = Uri.withAppendedPath(
                UsuarioProvider.CONTENT_URI, String.valueOf(usuario.id));
        int linhasAfetadas = cr.update(
                uri, getValues(usuario), null, null);
        return linhasAfetadas;
    }
    public int excluirLocal(Usuario usuario, ContentResolver cr) {
        Uri uri = Uri.withAppendedPath(
                UsuarioProvider.CONTENT_URI, String.valueOf(usuario.id));
        int linhasAfetadas = cr.delete(uri, null, null);
        return linhasAfetadas;
    }
}

