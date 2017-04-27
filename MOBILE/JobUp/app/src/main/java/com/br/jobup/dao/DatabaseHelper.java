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
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("Erro no banto", "onCreate: " + e.toString());
        }

//        sqLiteDatabase.execSQL(
//                "BEGIN; \n"+
//                "CREATE TABLE USUARIO ( \n" +
//                        " ID_USUARIO Text NOT NULL PRIMARY KEY, \n" +
//                        "NOME Text NOT NULL,\n" +
//                        "CPF Text NOT NULL, \n" +
//                        "RG_UF Integer NOT NULL, \n" +
//                        " RG_NR Text NOT NULL, \n" +
//                        " DT_NASCIMENTO INTEGER NOT NULL, \n" +
//                        " DT_INCLUSAO DATETIME NOT NULL, \n" +
//                        " DT_ALTERACAO DateTime, \n" +
//                        " DT_APROVACAO DateTime, \n" +
//                        " DT_ORDENACAO DateTime, \n" +
//                        " APROVADO INTEGER DEFAULT FALSE, \n" +
//                        " ATIVO INTEGER NOT NULL DEFAULT TRUE, \n" +
//                        " ENDERECO_UF Text NOT NULL, \n" +
//                        " ENDERECO_CEP Text NOT NULL, \n" +
//                        " ENDERECO_LOGRADOURO Text NOT NULL, \n" +
//                        " ENDERECO_COMPLEMENTO Text, \n" +
//                        " ENDERECO_BAIRRO Text NOT NULL, \n" +
//                        " ENDERECO_CIDADE Text NOT NULL, \n" +
//                        " FIXO Text, CELULAR Text NOT NULL, \n" +
//                        " EMAIL Text, \n" +
//                        " CONSTRAINT unique__ID UNIQUE ( \"ID_USUARIO\" ), \n" +
//                        " CONSTRAINT unique_CPF UNIQUE ( \"CPF\" ) );\n" +
//                        "\n" +
//                        "\n" +
//                        "CREATE TABLE \"ESPECIALIDADE\"(\n" +
//                        "\"ID_ESPECIALIDADE\" Integer NOT NULL PRIMARY KEY,\n" +
//                        "\"DESCRICAO\" Text NOT NULL,\n" +
//                        "\" EXIGIR_COMPROVACAO\" Boolean DEFAULT 'FALSE',\n" +
//                        "CONSTRAINT \"unique_ID_ESPECIALIDADE\" UNIQUE ( \"ID_ESPECIALIDADE\" ) );\n" +
//                        "\n" +
//                        "\n" +
//                        "CREATE TABLE \"AVALIACAO\" ( \n" +
//                        "ID_USUARIO` TEXT NOT NULL, \n" +
//                        "`ID_ESPECIALIDADE` INTEGER NOT NULL,\n" +
//                        " `ID_CLIENTE` TEXT NOT NULL, \n" +
//                        " `DT_ULT_AVALIACAO` DATETIME, \n" +
//                        " `NOTA` INTEGER DEFAULT 0, \n" +
//                        " `COMENTARIO` TEXT NOT NULL, \n" +
//                        " PRIMARY KEY(`ID_USUARIO`,`ID_ESPECIALIDADE`,`ID_CLIENTE`) \n" +
//                        " FOREIGN KEY(`ID_USUARIO`, `ID_ESPECIALIDADE`) REFERENCES PERFIL_PROFISSIONAL(`ID_USUARIO`, `ID_ESPECIALIDADE`) );\n" +
//                        "\n" +
//                        "CREATE TABLE `PERFIL_PROFISSIONAL` ( \n" +
//                        "`ID_USUARIO` TEXT NOT NULL UNIQUE, \n" +
//                        "`ID_ESPECIALIDADE` INTEGER NOT NULL, \n" +
//                        "`DT_APROVACAO` INTEGER NOT NULL, \n" +
//                        "`APROVADO` BOOLEAN DEFAULT 0, \n" +
//                        "`RESUMO_CURRICULO` TEXT, \n" +
//                        "PRIMARY KEY(`ID_USUARIO`,`ID_ESPECIALIDADE`), \n" +
//                        "FOREIGN KEY(`ID_USUARIO`) REFERENCES USUARIO('ID_USUARIO'),\n" +
//                        "FOREIGN KEY(`ID_ESPECIALIDADE`) REFERENCES ESPECIALIDADE(`ID_ESPECIALIDADE`)) ;\n" +
//                        "\n" +
//                        "\n" +
//                        "CREATE TABLE `FORMACAO` (\n" +
//                        " `ID_USUARIO` TEXT NOT NULL UNIQUE, \n" +
//                        " `ID_ESPECIALIDADE` INTEGER NOT NULL, \n" +
//                        " `ID_FORMACAO` INTEGER NOT NULL, \n" +
//                        " `INSTITUICAO` TEXT NOT NULL, \n" +
//                        " `NOME_CURSO` TEXT NOT NULL, \n" +
//                        " `ANO_FORMACAO` NUMERIC NOT NULL, \n" +
//                        " `DATA_APROVACAO` DATE, \n" +
//                        " `APROVADO` INTEGER, \n" +
//                        " PRIMARY KEY(`ID_USUARIO`,`ID_ESPECIALIDADE`,`ID_FORMACAO`)\n" +
//                        " FOREIGN KEY(`ID_USUARIO`, `ID_ESPECIALIDADE`) REFERENCES PERFIL_PROFISSIONAL(`ID_USUARIO`, `ID_ESPECIALIDADE`) );\n" +
//                        "COMMIT;"
//        );
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
/*

    //NOMES DAS TABELAS
    public static final String TABELA_USUARIO = "USUARIO";
    public static final String TABELA_PERFIL_PROFISSIONAL = "PERFIL_PROFISSIONAL";
    public static final String TABELA_FORMACAO = "FORMACAO";
    private static final String TABELA_ESPECIALIDADE = "ESPECIALIDADE";
    private static final String TABELA_ENDERECO = "ENDERECO";
    private static final String TABELA_CONTATO = "CONTATO";
    private static final String TABELA_AVALIACAO = "AVALIACAO";




    //TABELA USUARIO
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_ESTRELAS = "estrelas";
    public static final String COLUNA_STATUS = "status";
    public static final String COLUNA_ID_SERVIDOR = "id_servidor";

    //TABELA PERFIL_PROFISSIONAL






    public static final String CREATE_USUARIO =
            "CREATE TABLE " + TABELA_USUARIO + " (\n" +
                    "\t\"ID_USUARIO\" Text NOT NULL,\n" +
                    "\t\"USUARIO_NOME\" Text NOT NULL,\n" +
                    "\t\"USUARIO_RG_UF\" Integer NOT NULL,\n" +
                    "\t\"USUARIO_RG_NR\" Text NOT NULL,\n" +
                    "\t\"USUARIO_DT_NASCIMENTO\" DateTime NOT NULL,\n" +
                    "\t\"USUARIO_DT_INCLUSAO\" DateTime NOT NULL,\n" +
                    "\t\"USUARIO_DT_ALTERACAO\" DateTime,\n" +
                    "\t\"USUARIO_DT_APROVACAO\" DateTime,\n" +
                    "\t\"USUARIO_DT_ORDENACAO\" DateTime NOT NULL,\n" +
                    "\t\"USUARIO_APROVADO\" Boolean NOT NULL DEFAULT FALSE,\n" +
                    "\t\"USUARIO_CPF\" Text NOT NULL,\n" +
                    "CONSTRAINT \"unique_ID_USUARIO\" UNIQUE ( \"ID_USUARIO\" ) );";

    public static final String CREATE_PERFIL_PROFISSIONAL =
            "CREATE TABLE " +TABELA_PERFIL_PROFISSIONAL + " (\n" +
                    "\t\"ID_USUARIO\" Text NOT NULL,\n" +
                    "\t\"ID_ESPECIALIDADE\" Integer NOT NULL,\n" +
                    "\t\"USUARIO_DT_APROVACAO\" DateTime,\n" +
                    "\t\"USUARIO_APROVADO\" Boolean NOT NULL DEFAULT FALSE,\n" +
                    "\t\"RESUMO_CURRICULO\" Text NOT NULL );";

    public static final String CREATE_FORMACAO =
            "CREATE TABLE " +TABELA_FORMACAO +" (\n" +
                    "\t\"ID_USUARIO\" Text NOT NULL,\n" +
                    "\t\"ID_ESPECIALIDADE\" Integer NOT NULL,\n" +
                    "\t\"ID_FORMACAO\" Integer NOT NULL,\n" +
                    "\t\"INSTITUICAO\" Text NOT NULL,\n" +
                    "\t\"NOME_CURSO\" Text NOT NULL,\n" +
                    "\t\"ANO_FORMACAO\" Integer NOT NULL,\n" +
                    "\t\"USUARIO_APROVADO\" Boolean NOT NULL DEFAULT FALSE,\n" +
                    "\t\"USUARIO_DT_APROVACAO\" DateTime );";


    public static final String CREATE_ESPECIALIDADE =
            "CREATE TABLE " +TABELA_ESPECIALIDADE +" (\n" +
                    "\t\"ID_ESPECIALIDADE\" Integer NOT NULL,\n" +
                    "\t\"DESCRICAO\" Text NOT NULL,\n" +
                    "\t\"EXIGIR_COMPROVACAO\" Boolean NOT NULL );";


    public static final String CREATE_ENDERECO =
            "CREATE TABLE " + TABELA_ENDERECO + " (\n" +
                    "\t\"ID_USUARIO\" Text NOT NULL,\n" +
                    "\t\"UF\" Integer NOT NULL,\n" +
                    "\t\"CEP\" Text NOT NULL,\n" +
                    "\t\"LOGRADOURO\" Text NOT NULL,\n" +
                    "\t\"COMPLEMENTO\" Text,\n" +
                    "\t\"BAIRRO\" Text NOT NULL,\n" +
                    "\t\"CIDADE\" Text NOT NULL,\n" +
                    "\t\"USUARIO_ID_USUARIO\" Text," +
                    "CONSTRAINT \"lnk_USUARIO_ENDERECO\" FOREIGN KEY ( \"ID_USUARIO\" ) REFERENCES \"USUARIO\"( \"ID_USUARIO\" )";



    public static final String CREATE_CONTATO =
            "CREATE TABLE "+TABELA_CONTATO+" (\n" +
                    "\t\"ID_USUARIO\" Text NOT NULL,\n" +
                    "\t\"USUARIO_FIXO\" Text,\n" +
                    "\t\"USUARIO_CELULAR\" Text NOT NULL,\n" +
                    "\t\"USUARIO_EMAIL\" Text NOT NULL,\n" +
                    "\t\"USUARIO_ID_USUARIO\" Text );";


    public static final String CREATE_AVALIACAO =
            "CREATE TABLE "+TABELA_AVALIACAO +" (\n" +
                    "\t\"ID_USUARIO\" Text NOT NULL,\n" +
                    "\t\"ID_ESPECIALIDADE\" Integer NOT NULL,\n" +
                    "\t\"ID_CLIENTE\" Text NOT NULL,\n" +
                    "\t\"DT_ULT_AVALIACAO\" DateTime NOT NULL,\n" +
                    "\t\"NOTA\" Integer NOT NULL,\n" +
                    "\t\"COMENTARIO\" Text );";
*/
