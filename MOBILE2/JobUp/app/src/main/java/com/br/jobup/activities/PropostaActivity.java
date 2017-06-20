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

    private static final String[] opcoes = {"O quanto antes possível", "nos próximos 30 dias",
            "Nos próximos três meses", "Não tenho certeza"};
    private static final String TAG = PropostaActivity.class.getSimpleName();

    ArrayAdapter<String> aOpcoes;
    Spinner mPrazo;
    private String idUsuario;
    private String idProfissional;
    private int idEspecialidade;
    private EditText mObsProposta;
    private EditText mServicoTitulo;
    private EditText mValorSugeridoProposta;
    private Button mBtnEnviarProposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposta);

        final Bundle bundle = getIntent()
                .getBundleExtra(CatalogoEspecialidadeActivity.DETALHE_CONTRATAR_SERVICO);
        idUsuario = bundle.getString(CatalogoEspecialidadeActivity.ID_USUARIO);
        idProfissional = bundle.getString(CatalogoEspecialidadeActivity.ID_PROFISSIONAL);
        idEspecialidade = bundle.getInt(CatalogoEspecialidadeActivity.ID_ESPECIALIDADE);


        mObsProposta = (EditText) findViewById(R.id.contratacao_servico_observacao);
        mValorSugeridoProposta = (EditText) findViewById(R.id.valor_sugerido);
        mBtnEnviarProposta = (Button) findViewById(R.id.contratacao_servico_contratar);
        mServicoTitulo = (EditText) findViewById(R.id.contratacao_servico_titulo);
        aOpcoes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcoes);
        mPrazo = (Spinner) findViewById(R.id.spnPrazo);
        mPrazo.setAdapter(aOpcoes);

        mBtnEnviarProposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = mServicoTitulo.getText().toString();
                String observacao = mObsProposta.getText().toString();
                double valorSugerido = (mValorSugeridoProposta.getText().toString() != "") ? Double.parseDouble(mValorSugeridoProposta.getText().toString()): 0.0;
                int tempoServico = mPrazo.getSelectedItemPosition();
                Proposta proposta =
                        new Proposta(idUsuario,
                                idProfissional,
                                idEspecialidade,
                                titulo,
                                e,
                                observacao,
                                tempoServico,
                                valorSugerido);

                String idServico = Util.getUUID();
                proposta.setIdServico(idServico);
                enviaContraProposta(proposta);
                startActivity(new Intent(PropostaActivity.this, MainActivity.class));
            }
        });
        mBtnEnviarContraProposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = mServicoTitulo.getText().toString();
                String observacao = mObsContraProposta.getText().toString();
                double valorSugerido = (mValorSugeridoContraProposta.getText().toString() != "") ? Double.parseDouble(mValorSugeridoContraProposta.getText().toString()): 0.0;
                int tempoServico = mPrazo.getSelectedItemPosition();
                Proposta proposta =
                        new Proposta(idUsuario,
                                idProfissional,
                                idEspecialidade,
                                titulo,
                                e,
                                observacao,
                                tempoServico,
                                valorSugerido);

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

    }*/
}
