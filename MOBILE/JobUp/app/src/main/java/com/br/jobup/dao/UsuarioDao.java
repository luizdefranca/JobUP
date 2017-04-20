package com.br.jobup.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.br.jobup.models.Usuario;
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
        String selectQuery = "SELECT * FROM " + Constantes.TABELA_USUARIO + " WHERE " + Constantes.ATIVO + " = true;";

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
                Constantes._ID + " = '" + id + "'";

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
    public void deleteUsuario(String id, UsuarioDatabaseListener listener) {
        // Ensure database exists.
        if (database != null) {
            int result = database.delete(Constantes.TABELA_USUARIO, Constantes._ID + " = " + id, null);

            if (result > 0) {
                listener.onSQLOperationSucceded("Customer Deleted");
            } else {
                listener.onSQLOperationFailed("Unable to Delete Customer");
            }

        }
    }

    @Override
    public void addUsuario(Usuario usuario, UsuarioDatabaseListener listener) {
        if (database != null){
            //prepare the transaction information that will be saved to the database
            ContentValues values = new ContentValues();
//            public static final String _ID = "ID";
//            public static final String NOME = "NOME";
//            public static final String CPF = "CPF";
//            public static final String RG_UF = "RG_UF";
//            public static final String RG_NR = "RG_NR";
//            public static final String DT_NASCIMENTO = "DT_NASCIMENTO";
//            public static final String DT_INCLUSAO = "DT_INCLUSAO";
//            public static final String DT_ALTERACAO = "DT_ALTERACAO";
//            public static final String DT_APROVACAO = "DT_APROVACAO";
//            public static final String DT_ORDENACAO = "DT_ORDENACAO";
//            public static final String APROVADO = "APROVADO";
//            public static final String ATIVO = "ATIVO";
//            public static final String ENDERECO_UF = "ENDERECO_UF";
//            public static final String ENDERECO_CEP = "ENDERECO_CEP";
//            public static final String _ENDERECO_LOGRADOURO = "ENDERECO_LOGRADOURO";
//            public static final String ENDERECO_COMPLEMENTO = "ENDERECO_COMPLEMENTO";
//            public static final String ENDERECO_BAIRRO = "ENDERECO_BAIRRO";
//            public static final String ENDERECO_CIDADE = "ENDERECO_CIDADE";
//            public static final String FIXO = "FIXO";
//            public static final String CELULAR = "CELULAR";
//            public static final String EMAIL = "EMAIL";

            values.put(Constantes._ID, usuario.getIdUsuario());
            values.put(Constantes.NOME, usuario.getNome());
            values.put(Constantes.CPF, usuario.getRg().getUf());
            values.put(Constantes.RG_UF, usuario.getRg().getUf());
            values.put(Constantes.RG_NR, usuario.getRg().getNr());
            values.put(Constantes.DT_NASCIMENTO, usuario.getDataNascimento().getTime());
            values.put(Constantes.DT_INCLUSAO, System.currentTimeMillis());
//            values.put(Constantes.DT_ALTERACAO, usuario.getDataAlteracao().getTime());
//            values.put(Constantes.DT_APROVACAO, usuario.getDataAprovacao().getTime());
//            values.put(Constantes.DT_ORDENACAO, usuario.getDataOrdenacao().getTime());
            values.put(Constantes.ENDERECO_UF, usuario.getEndereco().getUf());
            values.put(Constantes.ENDERECO_CEP, usuario.getEndereco().getCep());
            values.put(Constantes._ENDERECO_LOGRADOURO, usuario.getEndereco().getLogradouro());
            values.put(Constantes.ENDERECO_COMPLEMENTO, usuario.getEndereco().getComplemento());
            values.put(Constantes.ENDERECO_BAIRRO, usuario.getEndereco().getBairro());
            values.put(Constantes.ENDERECO_CIDADE, usuario.getEndereco().getCidade());
            values.put(Constantes.FIXO, usuario.getContato().getFixo().getNr());
            values.put(Constantes.CELULAR, usuario.getContato().getCelular().getNr());
            values.put(Constantes.EMAIL, usuario.getContato().getEmail().email);

            try {
                database.insertOrThrow(Constantes.TABELA_USUARIO, null, values);
                listener.onSQLOperationSucceded("Customer Added");
                if (DEBUG){
                    Log.d(LOG_TAG, "Customer Added");
                }
            } catch (SQLException e) {
                listener.onSQLOperationFailed(e.getCause() + " " + e.getMessage());
            }
        }
    }

    @Override
    public void updateUsuario(Usuario usuario, UsuarioDatabaseListener listener) {
        if (database != null){
            //prepare the transaction information that will be saved to the database
            ContentValues values = new ContentValues();
            values.put(Constantes._ID, usuario.getIdUsuario());
            values.put(Constantes.NOME, usuario.getNome());
            values.put(Constantes.CPF, usuario.getRg().getUf());
            values.put(Constantes.RG_UF, usuario.getRg().getUf());
            values.put(Constantes.RG_NR, usuario.getRg().getNr());
            values.put(Constantes.DT_NASCIMENTO, usuario.getDataNascimento().getTime());
            values.put(Constantes.DT_INCLUSAO, System.currentTimeMillis());
//            values.put(Constantes.DT_ALTERACAO, usuario.getDataAlteracao().getTime());
//            values.put(Constantes.DT_APROVACAO, usuario.getDataAprovacao().getTime());
//            values.put(Constantes.DT_ORDENACAO, usuario.getDataOrdenacao().getTime());
            values.put(Constantes.ENDERECO_UF, usuario.getEndereco().getUf());
            values.put(Constantes.ENDERECO_CEP, usuario.getEndereco().getCep());
            values.put(Constantes._ENDERECO_LOGRADOURO, usuario.getEndereco().getLogradouro());
            values.put(Constantes.ENDERECO_COMPLEMENTO, usuario.getEndereco().getComplemento());
            values.put(Constantes.ENDERECO_BAIRRO, usuario.getEndereco().getBairro());
            values.put(Constantes.ENDERECO_CIDADE, usuario.getEndereco().getCidade());
            values.put(Constantes.FIXO, usuario.getContato().getFixo().getNr());
            values.put(Constantes.CELULAR, usuario.getContato().getCelular().getNr());
            values.put(Constantes.EMAIL, usuario.getContato().getEmail().email);
            //Now update the this row with the information supplied
            int result =  database.update(Constantes.TABELA_USUARIO, values,
                    Constantes._ID + " = " + usuario.getIdUsuario(), null);
            if (result == 1){
                listener.onSQLOperationSucceded("Customer Updated");
            }else{
                listener.onSQLOperationFailed("Customer Update Failed");
            }
        }

    }
}
