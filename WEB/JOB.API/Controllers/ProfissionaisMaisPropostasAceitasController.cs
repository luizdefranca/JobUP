using AutoMapper;
using JOB.DATA;
using JOB.HELPERS.Validation;
using JOB.WEB.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API de profissionais com maiores quantidades de propostas aceitas
    /// </summary>
    public class ProfissionaisMaisPropostasAceitasController : ApiController
    {
        private Contexto ctx = new Contexto();

        /// <summary>
        /// Recupera uma lista com os profissionais filtrados por uma determinada especialidade
        /// </summary>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <returns></returns>
        [ResponseType(typeof(List<ProfissionalViewModel>))]
        public HttpResponseMessage Get(int idEspecialidade)
        {
            try
            {
                var lstDominio = ctx.PerfilProfissional
                    .Include(i => i.AVALIACOES)
                    .Include(i => i.USUARIO.PROPOSTAS_SERVICO)
                    .Where(f => f.ID_ESPECIALIDADE == idEspecialidade)
                    .Where(f => f.USUARIO.PROPOSTAS_SERVICO.Where(w => w.ACEITA.HasValue).Any(a => a.ACEITA.Value))
                    .OrderByDescending(o => o.USUARIO.PROPOSTAS_SERVICO.Count).ToList();

                var lstModel = Mapper.Map<List<ProfissionalViewModel>>(lstDominio);

                foreach (var model in lstModel)
                {
                    var usuario = ctx.Usuario
                        .Include(i => i.PERFIS_PROFISSIONAIS)
                        .Include(i => i.PROPOSTAS_SERVICO.Select(s => s.SERVICO))
                        .First(F => F.ID_USUARIO == model.ID_USUARIO);

                    model.NOME = usuario.NOME;
                    model.DT_NASCTO = usuario.DT_NASCIMENTO;

                    model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;

                    model.BAIRRO = usuario.BAIRRO;
                    model.CIDADE = usuario.CIDADE;
                    model.ESTADO = usuario.UF.ToString();
                    model.DT_INCLUSAO = usuario.DT_INCLUSAO;
                    model.DT_ORDENACAO = usuario.DT_ORDENACAO;

                    model.OUTROS_PERFIS = Mapper.Map<List<ProfissionalViewModel>>(usuario.PERFIS_PROFISSIONAIS.Where(w => w.ID_ESPECIALIDADE != idEspecialidade));
                    model.AVALIACOES = Mapper.Map<List<AvaliacaoViewModel>>(ctx.Avaliacao.Where(w => w.ID_USUARIO == model.ID_USUARIO & w.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).ToList());

                    var MEUS_SERVICOS = usuario.PROPOSTAS_SERVICO.Where(w => w.ACEITA == true).Select(s => s.SERVICO);

                    if (MEUS_SERVICOS != null) model.SERVICOS.AddRange(Mapper.Map<List<ServicoViewModel_api>>(MEUS_SERVICOS));

                    if (model.AVALIACOES.Any()) model.MEDIA_AVALIACOES_FEITAS = model.AVALIACOES.Select(s => (int)s.NOTA).Average(); else model.MEDIA_AVALIACOES_FEITAS = 0;

                    model.QTD_PROPOSTAS_ACEITAS = usuario.PROPOSTAS_SERVICO.Where(w => w.ACEITA.HasValue).Count(C => C.ACEITA.Value & C.USUARIO.PERFIS_PROFISSIONAIS.Select(S => S.ID_ESPECIALIDADE).Contains(model.ID_ESPECIALIDADE));

                    foreach (var item in model.OUTROS_PERFIS)
                    {
                        item.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == item.ID_ESPECIALIDADE).DESCRICAO;
                    }
                }

                return Request.CreateResponse(HttpStatusCode.OK, lstModel);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }
    }
}