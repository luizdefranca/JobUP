package br.com.alura.testeconexaosql;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TESTE DA CONEXAO COM O BANCO DE DADOS

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Log.i("Android", " MySQL Connect Example.");

        new Thread(new Runnable() {
            @Override
            public void run() {

                Connection conn = null;
                try {
                    String driver = "net.sourceforge.jtds.jdbc.Driver";
                    Class.forName(driver).newInstance();
//            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//            Class.forName(driver);
//test = com.microsoft.sqlserver.jdbc.SQLServerDriver.class;
//            String connString = "jdbc:sqlserver://mundoup.database.windows.net:1433;" +
//                    "databaseName=mundoup;user=dhiogoacioli;password=senhaUP2017;";

                    String connString = "jdbc:jtds:sqlserver://mundoup.database.windows.net:1433;databaseName=mundoup;user=dhiogoacioli;password=senhaUP2017";
//                    Log.w("Connection", "open");

                    String username = "dhiogoacioli";
                    String password = "senhaUP2017";
                    conn = DriverManager.getConnection(connString);
                    Log.w("Connection", conn.toString());

                    Log.w("Connection", "open");
                    Statement stmt = conn.createStatement();
                    ResultSet reset = stmt.executeQuery("select * from jobup.USUARIO as u \n" +
                            "JOIN jobup.PERFIL_PROFISSIONAL pp ON u.ID_USUARIO = pp.ID_USUARIO\n" +
                            "JOIN jobup.ESPECIALIDADE as e ON pp.ID_ESPECIALIDADE=e.ID_ESPECIALIDADE");

//Print the data to the console
                    while (reset.next()) {
                        System.out.println(reset.getString("Nome") + " - " + reset.getString("Descricao"));
//              Log.w("Data",reset.getString(2));
                    }
                    conn.close();

                } catch (SQLException e) {
                    Log.e("Error connection", e.getMessage(), e);
                } catch (ClassNotFoundException e) {
                    Log.e("Error connection", e.getMessage(), e);

                } catch (InstantiationException e) {
                    Log.e("Erro connection", e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    Log.e("Erro Connection", e.getMessage(), e);
                }
            }
        }).start();


    }


    // FIM TESTE DE CONEXAO

}
