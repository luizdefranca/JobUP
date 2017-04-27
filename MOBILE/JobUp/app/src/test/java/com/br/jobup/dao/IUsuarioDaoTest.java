package com.br.jobup.dao;

import android.app.Application;
import android.content.Context;

import com.br.jobup.dao.usuario.IUsuarioDao;
import com.br.jobup.dao.usuario.UsuarioDao;
import com.br.jobup.models.Usuario;

import org.junit.Test;

import java.util.List;

/**
 * Created by luizramos on 19/04/17.
 */
public class IUsuarioDaoTest {




    @Test
    public void getAllUsuarios() throws Exception {

        Application application = new Application();
        Context context = application.getApplicationContext();


       IUsuarioDao dao = new UsuarioDao(context);
        List<Usuario> usuarios = dao.getAllUsuarios();
    }

    @Test
    public void getUsuarioByID() throws Exception {

    }

    @Test
    public void deleteUsuario() throws Exception {

    }

    @Test
    public void addUsuario() throws Exception {

    }

    @Test
    public void updateUsuario() throws Exception {

    }

}