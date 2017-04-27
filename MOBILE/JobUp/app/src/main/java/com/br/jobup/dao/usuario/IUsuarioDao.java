package com.br.jobup.dao.usuario;

import com.br.jobup.dao.UsuarioDatabaseListener;
import com.br.jobup.models.Usuario;

import java.util.List;



/**
 * Created by luizramos on 13/04/17.
 */

public interface IUsuarioDao {

    public List<Usuario> getAllUsuarios();
    public Usuario getUsuarioByID(String id);

    public void deleteUsuario(String id, UsuarioDatabaseListener listener);

    void addUsuario(Usuario usuario, UsuarioDatabaseListener listener);

    public void updateUsuario(Usuario usuario, UsuarioDatabaseListener listener);

    public void close();
}
