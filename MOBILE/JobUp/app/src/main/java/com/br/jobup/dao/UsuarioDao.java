package com.br.jobup.dao;

import com.br.jobup.models.Usuario;
import com.br.jobup.util.Validations.Util;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by luizramos on 13/04/17.
 */

public class UsuarioDao implements IUsuarioDao {

    public static final String TAG = UsuarioDao.class.getName();


    @Override
    public List<Usuario> getAllUsuarios() {

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Usuario> usuariosRealm = realm.where(Usuario.class).findAll();
        List<Usuario> usuarios = realm.copyFromRealm(usuariosRealm);


        return usuarios;


    }


    @Override
    public Usuario getUsuarioByID(String id) {
        Realm realm = Realm.getDefaultInstance();

                RealmResults<Usuario> usuariosRealm = realm.where(Usuario.class).equalTo("idUsuario", id).findAll();
                Usuario usuarioResult = usuariosRealm.first();
                Usuario usuario = realm.copyFromRealm(usuarioResult);

        return usuario;
    }

    @Override
    public void deleteUsuario(final String id) {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              Usuario usuarioToBeDeleted = realm.where(Usuario.class).equalTo("idUsuario", id).findFirst();
                                              usuarioToBeDeleted.deleteFromRealm();

                                          }
                                      }, new Realm.Transaction.OnSuccess() {
                                          @Override
                                          public void onSuccess() {
                                              realm.close();
                                              //TODO COLOCAR MENSAGEM INFORMANDO QUE FOI DELETADO COM SUCESSO
                                          }
                                      }, new Realm.Transaction.OnError() {
                                          @Override
                                          public void onError(Throwable error) {
                                              realm.close();
                                              //TODO COLOCAR MENSAGEM INFORMANDO QUE HOUVE ERRO AO DELETAR USUARIO
                                          }
                                      }

        );


        realm.close();


    }

    @Override
    public void addUsuario(final Usuario usuario) {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              usuario.setIdUsuario(Util.getUUID());
                                              realm.copyToRealm(usuario);
                                          }
                                      }, new Realm.Transaction.OnSuccess() {
                                          @Override
                                          public void onSuccess() {
                                              realm.close();
                                              //TODO COLOCAR MENSAGEM INFORMANDO QUE O USUARIO FOI ADICIONADO COM SUCESSO
                                          }
                                      }, new Realm.Transaction.OnError() {
                                          @Override
                                          public void onError(Throwable error) {
                                              realm.close();
                                              //TODO COLOCAR MENSAGEM INFORMANDO QUE HOUVE ERRO AO ADICIONAR USUARIO
                                          }
                                      }
        );
    }

    @Override
    public void updateUsuario(final Usuario usuario) {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute( Realm realm) {
                                              usuario.setIdUsuario(Util.getUUID());
                                              realm.copyToRealmOrUpdate(usuario);
                                          }
                                      }, new Realm.Transaction.OnSuccess() {
                                          @Override
                                          public void onSuccess() {
                                                realm.close();
                                              //TODO COLOCAR MENSAGEM INFORMANDO QUE USUARIO FOI FOI ATUALIZADO COM SUCESSO
                                          }
                                      }, new Realm.Transaction.OnError() {
                                          @Override
                                          public void onError(Throwable error) {
                                                realm.close();
                                              //TODO COLOCAR MENSAGEM INFORMANDO QUE HOUVE ERRO AO ATUALIZAR USUARIO
                                          }

                                      }
        );



    }
}
