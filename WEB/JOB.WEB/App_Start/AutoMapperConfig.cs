using AutoMapper;
using JOB.DATA.Domain;
using JOB.WEB.Models;

namespace JOB.WEB.App_Start
{
    public class AutoMapperConfig
    {
        public static void Register()
        {
            Mapper.Initialize(cfg =>
            {
                cfg.CreateMap<USUARIO, UsuarioViewModel>();
                cfg.CreateMap<PERFIL_PROFISSIONAL, ProfissionalViewModel>();
            });
        }
    }
}