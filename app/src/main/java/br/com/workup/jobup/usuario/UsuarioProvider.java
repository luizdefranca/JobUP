package br.com.workup.jobup.usuario;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import br.com.workup.jobup.dao.UsuarioSQLHelper;

/**
 * Created by Renevalda Maria on 07/11/2016.
 */
public class UsuarioProvider extends ContentProvider {
    private static final String AUTHORITY = "br.com.workup.jobup.usuario";
    private static final String PATH = "usuarios";
    private static final int TIPO_GERAL = 1;
    private static final int TIPO_USUARIO_ESPECIFICO = 2;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY +"/"+ PATH);
    private UsuarioSQLHelper mHelper;
    private static final UriMatcher sUriMatcher;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, PATH, TIPO_GERAL);
        sUriMatcher.addURI(AUTHORITY, PATH + "/#", TIPO_USUARIO_ESPECIFICO);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB =  mHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case TIPO_GERAL:
                rowsDeleted = sqlDB.delete(UsuarioSQLHelper.TABELA_USUARIO,
                        selection, selectionArgs);
                break;
            case TIPO_USUARIO_ESPECIFICO:
                String id = uri.getLastPathSegment();
                rowsDeleted = sqlDB.delete(UsuarioSQLHelper.TABELA_USUARIO,
                        UsuarioSQLHelper.COLUNA_ID +"= ?",
                        new String[]{ id });
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        int uriType = sUriMatcher.match(uri);
        switch (uriType){
            case TIPO_GERAL :
                return "vnd.android.cursor.dir/br.com.workup.jobup.usuario";
            case TIPO_USUARIO_ESPECIFICO:
                return "vnd.android.cursor.item/br.com.workup.jobup.usuario";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mHelper.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case TIPO_GERAL:
                id = sqlDB.insertWithOnConflict(UsuarioSQLHelper.TABELA_USUARIO,
                         null, values, SQLiteDatabase.CONFLICT_REPLACE);
                break;
            default:
                throw new IllegalArgumentException("URI não suportada: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
    }

    @Override
    public boolean onCreate() {
        mHelper = new UsuarioSQLHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder =  new SQLiteQueryBuilder();
        queryBuilder.setTables(UsuarioSQLHelper.TABELA_USUARIO);
        Cursor cursor = null;

        switch (uriType) {
            case TIPO_GERAL:
                cursor = queryBuilder.query(db, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case TIPO_USUARIO_ESPECIFICO:
                queryBuilder.appendWhere(UsuarioSQLHelper.COLUNA_ID + "= ?");
                cursor = queryBuilder.query(db, projection, selection,
                        new String[]{ uri.getLastPathSegment() },
                        null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mHelper.getWritableDatabase();
        int linhasAfetadas = 0;
        switch (uriType) {
            case TIPO_GERAL:
                linhasAfetadas = sqlDB.update(UsuarioSQLHelper.TABELA_USUARIO,
                        values, selection, selectionArgs);
                break;
            case TIPO_USUARIO_ESPECIFICO:
                String id = uri.getLastPathSegment();
                linhasAfetadas = sqlDB.update(UsuarioSQLHelper.TABELA_USUARIO,
                        values, UsuarioSQLHelper.COLUNA_ID +"= ?",
                        new String[]{ id });
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return linhasAfetadas;
    }
}
