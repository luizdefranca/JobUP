package com.br.jobup.services.parsers;
import com.br.jobup.models.servico.Proposta;
import com.br.jobup.services.RetroFitInicializador;

import java.util.List;
import retrofit2.Call;


public class ParserProposta {

    public static final String TAG = "LCFR/"+ParserProposta.class.getSimpleName();

    private String idProfissional = null;
    private Proposta proposta;

    public ParserProposta(String idProfissional){
        this.idProfissional = idProfissional;
    }
   public ParserProposta(){

   }


    public List<Proposta> getAllPorIdUsuario(){
        List<Proposta> propostas = null;

////        try {
////            propostas =  new RetroFitInicializador()
////                    .createPropostaAPI()
////                    .getPropostas(this.idUsuario)
////                    .execute()
////                    .body();
////        } catch (IOException e) {
////            e.printStackTrace();
////            Log.e(TAG, "getAllPorIdUsuario: ",e );
//        }
        return propostas;
    }
    public Call<Void> post(Proposta proposta) {
        final Call<Void> callPost = new RetroFitInicializador()
                .createPropostaAPI()
                .post(this.proposta);

        return null;
    }
}
