package com.br.jobup.dao.usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.br.jobup.dao.DatabaseHelper;
import com.br.jobup.models.usuario.Usuario;
import com.br.jobup.util.Constantes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luizramos on 16/04/17.
 */



public class UsuarioDao implements IUsuarioDao {
    private final static String LOG_TAG = UsuarioDao.class.getSimpleName();
    private final Context mContext;
    private SQLiteDatabase database;
    private DatabaseHelper DbHelper;
    private boolean DEBUG = false;

    public UsuarioDao(Context context) {
        mContext = context;
        DbHelper = DatabaseHelper.newInstance(mContext);
        database = DbHelper.getWritableDatabase();
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        //initialize an empty list of usuarios
        List<Usuario> usuarios = new ArrayList<>();

        //sql command to select all Usuarios;
        String selectQuery = "SELECT * FROM " + Constantes.TABELA_USUARIO
            //    + " WHERE " + Constantes.USUARIO_ATIVO + " = FALSE;"
                ;

        //make sure the database is not empty
        if (database != null) {

            //get a cursor for all usuarios in the database
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    //get each usuario in the cursor
                    usuarios.add(Usuario.getUsuarioFromCursor(cursor));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }


        return usuarios;
    }

    @Override
    public Usuario getUsuarioByID(String id) {

        String query = "SELECT * FROM " + Constantes.TABELA_USUARIO + " WHERE " +
                Constantes.USUARIO_ID + " = '" + id + "'";

        //Get the cursor representing the Customer
        Cursor cursor = database.rawQuery(query, null);

        //Create a variable of data type Customer
        Usuario usuario;
        if (cursor.moveToFirst()){
            usuario = Usuario.getUsuarioFromCursor(cursor);
        }else {
            usuario = null;
        }

        cursor.close();

        //Return result: either a valid usuario or null
        return  usuario;


    }

    @Override
    public void deleteUsuario(String id) {
        // Ensure database exists.
        if (database != null) {
            int result = database.delete(Constantes.TABELA_USUARIO, Constantes.USUARIO_ID + " = " + id, null);



        }
    }

    @Override
    public void addUsuario(Usuario usuario) {
        if (database != null){
            //prepare the transaction information that will be saved to the database
            ContentValues values = new ContentValues();
//            public static final String USUARIO_ID = "ID";
//            public static final String USUARIO_NOME = "USUARIO_NOME";
//            public static final String USUARIO_CPF = "USUARIO_CPF";
//            public static final String USUARIO_RG_UF = "USUARIO_RG_UF";
//            public static final String USUARIO_RG_NR = "USUARIO_RG_NR";
//            public static final String USUARIO_DT_NASCIMENTO = "USUARIO_DT_NASCIMENTO";
//            public static final String USUARIO_DT_INCLUSAO = "USUARIO_DT_INCLUSAO";
//            public static final String USUARIO_DT_ALTERACAO = "USUARIO_DT_ALTERACAO";
//            public static final String USUARIO_DT_APROVACAO = "USUARIO_DT_APROVACAO";
//            public static final String USUARIO_DT_ORDENACAO = "USUARIO_DT_ORDENACAO";
//            public static final String USUARIO_APROVADO = "USUARIO_APROVADO";
//            public static final String USUARIO_ATIVO = "USUARIO_ATIVO";
//            public static final String USUARIO_ENDERECO_UF = "USUARIO_ENDERECO_UF";
//            public static final String USUARIO_ENDERECO_CEP = "USUARIO_ENDERECO_CEP";
//            public static final String USUARIO_ENDERECO_LOGRADOURO = "ENDERECO_LOGRADOURO";
//            public static final String USUARIO_ENDERECO_COMPLEMENTO = "USUARIO_ENDERECO_COMPLEMENTO";
//            public static final String USUARIO_ENDERECO_BAIRRO = "USUARIO_ENDERECO_BAIRRO";
//            public static final String USUARIO_ENDERECO_CIDADE = "USUARIO_ENDERECO_CIDADE";
//            public static final String USUARIO_FIXO = "USUARIO_FIXO";
//            public static final String USUARIO_CELULAR = "USUARIO_CELULAR";
//            public static final String USUARIO_EMAIL = "USUARIO_EMAIL";

            values.put(Constantes.USUARIO_ID, usuario.getIdUsuario());
            values.put(Constantes.USUARIO_NOME, usuario.getNome());
            values.put(Constantes.USUARIO_CPF, usuario.getCpf().getNr());
            values.put(Constantes.USUARIO_RG_UF, usuario.getRg().getUf());
            values.put(Constantes.USUARIO_RG_NR, usuario.getRg().getNr());
            values.put(Constantes.USUARIO_DT_NASCIMENTO, usuario.getDataNascimento().getTime());
            values.put(Constantes.USUARIO_DT_INCLUSAO, System.currentTimeMillis());
//            values.put(Constantes.USUARIO_DT_ALTERACAO, usuario.getDataAlteracao().getTime());
//            values.put(Constantes.USUARIO_DT_APROVACAO, usuario.getDataAprovacao().getTime());
//            values.put(Constantes.USUARIO_DT_ORDENACAO, usuario.getDataOrdenacao().getTime());
            values.put(Constantes.USUARIO_ENDERECO_UF, usuario.getUf());
            values.put(Constantes.USUARIO_ENDERECO_CEP, usuario.getCep());
            values.put(Constantes.USUARIO_ENDERECO_LOGRADOURO, usuario.getLogradouro());
            values.put(Constantes.USUARIO_ENDERECO_COMPLEMENTO, usuario.getComplemento());
            values.put(Constantes.USUARIO_ENDERECO_BAIRRO, usuario.getBairro());
            values.put(Constantes.USUARIO_ENDERECO_CIDADE, usuario.getCidade());
            values.put(Constantes.USUARIO_FIXO, usuario.getFixo().getNr());
            values.put(Constantes.USUARIO_CELULAR, usuario.getCelular().getNr());
            values.put(Constantes.USUARIO_EMAIL, usuario.getEmail().getEmail());
            try {
                long t = database.insert(Constantes.TABELA_USUARIO, null, values);
                // listener.onSQLOperationSucceded("Customer Added");
                if (DEBUG){
                    Log.d(LOG_TAG, "Usuario adicionado");
                }
            } catch (SQLException e) {
//                listener.onSQLOperationFailed(e.getCause() + " " + e.getMessage());
            }
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        if (database != null){
            //prepare the transaction information that will be saved to the database
            ContentValues values = new ContentValues();
            values.put(Constantes.USUARIO_ID, usuario.getIdUsuario());
            values.put(Constantes.USUARIO_NOME, usuario.getNome());
            values.put(Constantes.USUARIO_CPF, usuario.getRg().getUf());
            values.put(Constantes.USUARIO_RG_UF, usuario.getRg().getUf());
            values.put(Constantes.USUARIO_RG_NR, usuario.getRg().getNr());
            values.put(Constantes.USUARIO_DT_NASCIMENTO, usuario.getDataNascimento().getTime());
            values.put(Constantes.USUARIO_DT_INCLUSAO, System.currentTimeMillis());
//            values.put(Constantes.USUARIO_DT_ALTERACAO, usuario.getDataAlteracao().getTime());
//            values.put(Constantes.USUARIO_DT_APROVACAO, usuario.getDataAprovacao().getTime());
//            values.put(Constantes.USUARIO_DT_ORDENACAO, usuario.getDataOrdenacao().getTime());
            values.put(Constantes.USUARIO_ENDERECO_UF, usuario.getUf());
            values.put(Constantes.USUARIO_ENDERECO_CEP, usuario.getCep());
            values.put(Constantes.USUARIO_ENDERECO_LOGRADOURO, usuario.getLogradouro());
            values.put(Constantes.USUARIO_ENDERECO_COMPLEMENTO, usuario.getComplemento());
            values.put(Constantes.USUARIO_ENDERECO_BAIRRO, usuario.getBairro());
            values.put(Constantes.USUARIO_ENDERECO_CIDADE, usuario.getCidade());
            values.put(Constantes.USUARIO_FIXO, usuario.getFixo().getNr());
            values.put(Constantes.USUARIO_CELULAR, usuario.getCelular().getNr());
            values.put(Constantes.USUARIO_EMAIL, usuario.getEmail().getEmail());
            //Now update the this row with the information supplied
            int result =  database.update(Constantes.TABELA_USUARIO, values,
                    Constantes.USUARIO_ID + " = " + usuario.getIdUsuario(), null);

        }

    }

    @Override
    public void close(){
        DbHelper.close();
    }
}
