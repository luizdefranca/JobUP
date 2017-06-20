package com.br.jobup.activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.br.jobup.R;
import com.br.jobup.models.servico.Proposta;
import com.br.jobup.services.parsers.ParserProposta;
import com.br.jobup.validations.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PropostaActivity extends AppCompatActivity {

    private static final String[] opcoes = {"6(HORAS)", "12(HORAS)", "24(HORAS)", "36(HORAS)", "48(HORAS)",
            "1(SEMANA)","2(SEMANAS)", "3(SEMANAS)", "Tempo Indeterminado"};
    private static final String TAG = PropostaActivity.class.getSimpleName();

    ArrayAdapter<String> aOpcoes;
    Spinner mPrazo;
    private String idServico;
    private String idUsuario;
    private String dtProposta;
    private int duracaoServico;
    private int valorDuracaoServico;
    private EditText vlProposta;
    private EditText mJustificativa;
    private EditText dsTitulo;
    private String dsObservacoes;
    private Button mBtnEnviarProposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposta);

        vlProposta = (EditText) findViewById(R.id.valor_proposta);
        mJustificativa = (EditText) findViewById(R.id.proposta_justificativa);
        mBtnEnviarProposta = (Button) findViewById(R.id.enviar_proposta);
        dsTitulo = (EditText) findViewById(R.id.proposta_titulo);
        aOpcoes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcoes);
        mPrazo = (Spinner) findViewById(R.id.spnDuracaoServico);
        mPrazo.setAdapter(aOpcoes);

        mBtnEnviarProposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mTitulo = dsTitulo.getText().toString();
                String mVlProposta = vlProposta.getText().toString();
                String justificativa = mJustificativa.getText().toString();
                int tempoServico = mPrazo.getSelectedItemPosition();
                Proposta proposta =
                        new Proposta(idUsuario,
                                idServico,
                                dtProposta,
                                duracaoServico,
                                mTitulo,
                                mVlProposta,
                                justificativa,
                                tempoServico);

                String idServico = Util.getUUID();
                proposta.setIdServico(idServico);
                enviaProposta(proposta);
                startActivity(new Intent(PropostaActivity.this, MainActivity.class));
            }
        });

    }

    private void enviaProposta(Proposta proposta) {
        Log.e("LCFR " + TAG, "Entrada no método carregaCatalogoEspecialidade: " );

        final ParserProposta parser = new ParserProposta(idProfissional);
        parser.post(proposta).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PropostaActivity.this, "Proposta enviada com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PropostaActivity.this, "Falha ao enviar a Proposta!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "onFailure: ao enviar Proposta", t);
                t.printStackTrace();
                Toast.makeText(PropostaActivity.this, "Falha na conexão!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
