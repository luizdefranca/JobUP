package com.br.jobup.activities;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

    private static final String TAG = PropostaActivity.class.getSimpleName();

    private int idProposta;
    private String idServico;
    private String idUsuario;
    private String dtProposta;
    private int duracaoServico;
    private EditText mDuracaoServico;
    private EditText mValorDuracaoServico;
    private EditText mDsObservacoes;
    private EditText mVlProposta;
    private EditText mJustificativa;
    private EditText mTitulo;
    private Button mBtnEnviarProposta;
    private Button mBtnRejeitarProposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposta);

        mVlProposta = (EditText) findViewById(R.id.valor_proposta);
        mDuracaoServico = (EditText) findViewById(R.id.duracao_servico);
        mValorDuracaoServico = (EditText) findViewById(R.id.valor_duracao_servico);
        mDsObservacoes = (EditText) findViewById(R.id.observacoes);
        mJustificativa = (EditText) findViewById(R.id.proposta_justificativa);
        mTitulo = (EditText) findViewById(R.id.proposta_titulo);

        mBtnRejeitarProposta = (Button) findViewById(R.id.rejeitar_proposta);

        mBtnEnviarProposta = (Button) findViewById(R.id.enviar_proposta);
        mBtnEnviarProposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int duracaoServico = (int) ((mDuracaoServico.getText().toString() != "") ? Double.parseDouble(mDuracaoServico.getText().toString()): 0.0);
                int valorDuracaoServico = (int) ((mValorDuracaoServico.getText().toString() != "") ? Double.parseDouble(mValorDuracaoServico.getText().toString()): 0.0);
                double vlProposta = (mVlProposta.getText().toString() != "") ? Double.parseDouble(mVlProposta.getText().toString()): 0.0;
                String justificativa = mJustificativa.getText().toString();
                String dsTitulo = mTitulo.getText().toString();
                String dsObservacoes = mDsObservacoes.getText().toString();


               //TODO Fazer construtor para a classe Proposta conforme abaixo
                Proposta proposta =
                        new Proposta(
                                idServico,
                                idUsuario,
                                dtProposta,
                                duracaoServico,
                                valorDuracaoServico,
                                vlProposta,
                                justificativa,
                                dsTitulo,
                                dsObservacoes);

                String idServico = Util.getUUID();
                proposta.setIdServico(idServico);
                enviaProposta(proposta);
                startActivity(new Intent(PropostaActivity.this, MainActivity.class));
            }
        });

    }


    public Button mBtnRejeitarProposta() {
        rejeitarProposta(idProposta);
        return null;
    }
    private void rejeitarProposta(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Rejeitar Proposta")
                .setMessage("Tem certeza que deseja Rejeitar essa Proposta?")
                .setPositiveButton("sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //adapter.notifyDataSetChanged();
                                //listaDeCursos.remove(position);
                            }
                        })
                .setNegativeButton("não", null)
                .show();
    }


    private void enviaProposta(Proposta proposta) {
        Log.e("LCFR " + TAG, "Entrada no método carregaCatalogoEspecialidade: " );

        final ParserProposta parser = new ParserProposta();
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
