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
    /// API das formacoes dos profisionais
    /// </summary>
    public class FormacaoController : ApiController
    {
        private Contexto ctx = new Contexto();

        /// <summary>
        /// Lista as formacoes de um usuario e uma determinada especialidade
        /// </summary>
        /// <param name="idUsuario">id do usuario</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <returns></returns>
        [ResponseType(typeof(List<FORMACAO>))]
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade)
        {
            var result = ctx.Formacao.Where(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Recupera uma formacao especifica de um perfil profissional
        /// </summary>
        /// <param name="idUsuario">id do usuario</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="idFormacao">id da formacao</param>
        /// <returns></returns>
        [ResponseType(typeof(FORMACAO))]
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade, int idFormacao)
        {
            var result = ctx.Formacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_FORMACAO == idFormacao);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Salva uma nova formacao
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
        /// Atualiza uma formacao
        /// </summary>
        /// <param name="idUsuario">id do usuario</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="idFormacao">id da formacao</param>
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
        /// Deleta uma formacao
        /// </summary>
        /// <param name="idUsuario">id do usuario</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="idFormacao">id da formacao</param>
        public void Delete(Guid idUsuario, int idEspecialidade, int idFormacao)
        {
            var item = ctx.Formacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_FORMACAO == idFormacao);

            ctx.Formacao.Remove(item);
            ctx.SaveChanges();
        }
    }
}