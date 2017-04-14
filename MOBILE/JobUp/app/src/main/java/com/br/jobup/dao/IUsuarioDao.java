package com.br.jobup.dao;

import com.br.jobup.models.Usuario;

import java.util.List;

/**
 * Created by luizramos on 13/04/17.
 */

public interface IUsuarioDao {

    public List<Usuario> getAllUsuarios();
    public Usuario getUsuarioByID(String id);
    public void deleteUsuario(String id);
    public void addUsuario(Usuario usuario);
    public void updateUsuario(Usuario usuario);
}
