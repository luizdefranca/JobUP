package com.br.jobup.dao.usuario;

import com.br.jobup.models.usuario.Usuario;

import java.util.List;



/**
 * Created by luizramos on 13/04/17.
 */

public interface IUsuarioDao {

    public List<Usuario> getAllUsuarios();
    public Usuario getUsuarioByID(String id);

    public void deleteUsuario(String id);

    void addUsuario(Usuario usuario);

    public void updateUsuario(Usuario usuario);

    public void close();
}
