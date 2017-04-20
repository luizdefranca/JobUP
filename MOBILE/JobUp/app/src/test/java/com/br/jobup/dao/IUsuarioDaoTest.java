package com.br.jobup.dao;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.br.jobup.models.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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