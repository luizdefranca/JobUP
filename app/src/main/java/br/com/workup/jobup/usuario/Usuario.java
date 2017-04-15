package br.com.workup.jobup.usuario;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Renevalda Maria on 09/04/2017.
 */
public class Usuario implements Serializable {
    public long id;
    public String nome;
    public String email;
    public String senha;
    public float estrelas;
    public Status status;
    public long idServidor;

    public Usuario (long id, String nome, String email, String senha, float estrelas, long idServidor, Status status){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.estrelas = estrelas;
        this.idServidor = idServidor;
        this.status = status;
        String id_UIID = UUID.randomUUID().toString();
    }
    public Usuario(String nome,String email, String senha, float estrelas){
        this(0, nome, email, senha, estrelas, 0, Status.INSERIR);
    }
    @Override
    public String toString() {
        return nome;
    }

    public enum Status {
        OK, INSERIR, ATUALIZAR, EXCLUIR
    }
}
