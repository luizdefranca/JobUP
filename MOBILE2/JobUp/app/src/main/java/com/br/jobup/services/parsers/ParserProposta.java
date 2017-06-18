package com.br.jobup.services.parsers;
import com.br.jobup.models.servico.Proposta;
import java.util.List;
import retrofit2.Call;


public class ParserProposta {

    public static final String TAG = "LCFR/"+ParserProposta.class.getSimpleName();

    private final String idUsuario;

    public ParserProposta(String idUsuario){
        this.idUsuario = idUsuario;
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
//        final Call<Void> callPost = new RetroFitInicializador()
//                .createServicoPrivadoAPI()
//                .post(proposta);

//        return callPost;
        return null;
    }
}
