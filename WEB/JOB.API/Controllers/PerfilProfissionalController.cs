using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
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
    /// API de perfis profissionais
    /// </summary>
    public class PerfilProfissionalController : ApiController
    {
        private Contexto ctx = new Contexto();

        /// <summary>
        /// Recupera todos os profissionais de uma determinada especialidade
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
                    .ToList();

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

                    var MEUS_SERVICOS = usuario.PROPOSTAS_SERVICO.Where(w => w.ACEITA.HasValue).Where(w => w.ACEITA.Value).Select(s => s.SERVICO);

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

        /// <summary>
        /// Recupera todas os perfis profissionais de um determinado profissional
        /// </summary>
        /// <param name="idUsuario"></param>
        /// <returns></returns>
        [ResponseType(typeof(List<PERFIL_PROFISSIONAL>))]
        public HttpResponseMessage Get(Guid idUsuario)
        {
            var result = ctx.PerfilProfissional.Include(i => i.ESPECIALIDADE).Where(w => w.ID_USUARIO == idUsuario).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Recupera dados de um determinado perfil profissional
        /// </summary>
        /// <param name="idUsuario">id do usuário profissional</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <returns></returns>
        [ResponseType(typeof(PERFIL_PROFISSIONAL))]
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade)
        {
            var result = ctx.PerfilProfissional.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// salva um novo perfil profissional
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        [HttpPost]
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Post(PERFIL_PROFISSIONAL obj)
        {
            try
            {
                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.PerfilProfissional.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        /// <summary>
        /// atualiza um perfil profissional
        /// </summary>
        /// <param name="idUsuario">id do usuário</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="obj"></param>
        /// <returns></returns>
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Put(Guid idUsuario, int idEspecialidade, PERFIL_PROFISSIONAL obj)
        {
            try
            {
                var item = ctx.PerfilProfissional.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade);

                item.AtualizaValores(obj.RESUMO_CURRICULO);
                ctx.Entry(item).State = EntityState.Modified;

                Validate(item);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.OK);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        /// <summary>
        /// deleta um perfil profissional
        /// </summary>
        /// <param name="idUsuario">id do usuário</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        [ResponseType(typeof(HttpStatusCode))]
        public void Delete(Guid idUsuario, int idEspecialidade)
        {
            var item = ctx.PerfilProfissional.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade);

            ctx.PerfilProfissional.Remove(item);
            ctx.SaveChanges();
        }
    }
}