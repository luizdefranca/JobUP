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
                cfg.CreateMap<USUARIO, UsuarioViewModel_VW>();
                cfg.CreateMap<PERFIL_PROFISSIONAL, ProfissionalViewModel>();
                cfg.CreateMap<SERVICO, ServicoViewModel_api>();
                cfg.CreateMap<SERVICO, ServicoViewModel_full>();
                cfg.CreateMap<PROPOSTA_SERVICO, PropostaViewModel>();
                cfg.CreateMap<OFERTA_SERVICO, OfertaViewModel>();
                cfg.CreateMap<ESPECIALIDADE, EspecialidadeViewModel>();
                cfg.CreateMap<AVALIACAO, AvaliacaoViewModel>();
                cfg.CreateMap<HISTORICO_MOEDA, HistoricoMoedaVM>();
                cfg.CreateMap<CHAT, ChatVM>();
                //cfg.CreateMap<SUB_ESPECIALIDADE, SubEspecialidadeViewModel>();
            });
        }
    }
}