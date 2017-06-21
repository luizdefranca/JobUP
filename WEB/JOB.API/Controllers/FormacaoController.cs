using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
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
    /// API das formações dos profisionais
    /// </summary>
    public class FormacaoController : ApiController
    {
        private Contexto ctx = new Contexto();

        /// <summary>
        /// Lista as formações de um usuário e uma determinada especialidade
        /// </summary>
        /// <param name="idUsuario">id do usuário</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <returns></returns>
        [ResponseType(typeof(List<FORMACAO>))]
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade)
        {
            var result = ctx.Formacao.Where(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Recupera uma formação específica de um perfil profissional
        /// </summary>
        /// <param name="idUsuario">id do usuário</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="idFormacao">id da formação</param>
        /// <returns></returns>
        [ResponseType(typeof(FORMACAO))]
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade, int idFormacao)
        {
            var result = ctx.Formacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_FORMACAO == idFormacao);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Salva uma nova formação
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        [HttpPost]
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Post(FORMACAO obj)
        {
            try
            {
                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.Formacao.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception)
            {
                throw;
            }
        }

        /// <summary>
        /// Atualiza uma formação
        /// </summary>
        /// <param name="idUsuario">id do usuário</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="idFormacao">id da formação</param>
        /// <param name="obj"></param>
        /// <returns></returns>
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Put(Guid idUsuario, int idEspecialidade, int idFormacao, FORMACAO obj)
        {
            try
            {
                var item = ctx.Formacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_FORMACAO == idFormacao);

                item.AtualizaDados(obj.INSTITUICAO, obj.NOME_CURSO, obj.ANO_FORMACAO);
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
        /// Deleta uma formação
        /// </summary>
        /// <param name="idUsuario">id do usuário</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="idFormacao">id da formação</param>
        public void Delete(Guid idUsuario, int idEspecialidade, int idFormacao)
        {
            var item = ctx.Formacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_FORMACAO == idFormacao);

            ctx.Formacao.Remove(item);
            ctx.SaveChanges();
        }
    }
}