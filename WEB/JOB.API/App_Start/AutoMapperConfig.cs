﻿using AutoMapper;
using JOB.DATA.Domain;
using JOB.WEB.Models;

namespace JOB.API.App_Start
{
    public class AutoMapperConfig
    {
        public static void Register()
        {
            Mapper.Initialize(cfg =>
            {
                cfg.CreateMap<USUARIO, UsuarioViewModel>();
                cfg.CreateMap<PERFIL_PROFISSIONAL, ProfissionalViewModel>();
                cfg.CreateMap<SERVICO, ServicoViewModel_api>();
                cfg.CreateMap<SERVICO, ServicoViewModel_full>();
                cfg.CreateMap<OFERTA_SERVICO, OfertaViewModel>();
            });
        }
    }
}