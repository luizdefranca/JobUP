package br.com.workup.jobup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText home_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home_search = (EditText) findViewById(R.id.home_search);


        home_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == KeyEvent.ACTION_DOWN ) {

                    String searchTerm = home_search.getText().toString();

                    if(searchTerm != null && searchTerm.length() > 2){
                       // metodoQueRealizaABusca(searchTerm);
                        home_search.setText("");
                    }else{
                        Toast.makeText(MainActivity.this,
                                R.string.search_message,Toast.LENGTH_SHORT)
                                .show();
                    }

                    return true;
                }
                else {
                    return false;
                }
            }
        });
    }
}
